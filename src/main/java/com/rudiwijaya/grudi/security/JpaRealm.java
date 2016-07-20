package com.rudiwijaya.grudi.security;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.rudiwijaya.grudi.AppUtils;
import com.rudiwijaya.grudi.jpa.Person;

/**
 * @author rudi
 *
 */
public class JpaRealm extends AuthorizingRealm {
	
	private static final Logger log = LoggerFactory.getLogger(JpaRealm.class);

	private final EntityManager em;

	/**
	 * 
	 */
	public JpaRealm(final EntityManager em) {
		super();
		this.em = em;
		
		setName(AppUtils.APP_NAME);
		setCredentialsMatcher(new Rfc2307CredentialsMatcher());
		setAuthenticationTokenClass(AuthenticationToken.class);
		
		log.info("Realm '{}' was created with em '{}'", getName(), this.em);
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		final String userName = (String) principals.fromRealm(getName()).iterator().next();
		
		Preconditions.checkNotNull(em, "Em must not be null!!");
		
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		final Root<Person> root = cq.distinct(true).from(Person.class);
		
		cq.select(root);
		cq.where(cb.equal(root.get("username"), userName));
		@Nullable final Person loggedInUser = em.createQuery(cq).getSingleResult();
        if (loggedInUser != null) {
            final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            return info;
        } else {
            return null;
        }
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		final String host = token instanceof HostAuthenticationToken ? ((HostAuthenticationToken) token).getHost() : null;
		if (!getName().equals(host)) {
			log.debug("[{}] Host mismatch, expected '{}', token requests '{}'."
					+ " If you use multiple realms, one realm should match the host while others mismatch."
					+ " If all mismatch, you have a misconfiguration.", getName(), getName(), host);
			throw new UnknownAccountException("[" + getName() + "] Host mismatch, expected '" + getName() + "', token requests '" + host + "'."
					+ " If you use multiple realms, one realm should match the host while others mismatch."
					+ " If all mismatch, you have a misconfiguration.");
		}
		
		if (token instanceof UsernamePasswordToken) {
			// Principal can be either person ID, slug (username in user's perspective), or email
			final String tokenPrincipal = ((UsernamePasswordToken) token).getUsername();
			final String normalizedEmail = NameUtils.normalizeEmail(tokenPrincipal);
			
			Preconditions.checkNotNull(em, "Em must not be null!!");
			
			/**
			 * select *
from person p left join email e on (p.id = e.person_id)
where p.id = 'sysadmin'
or p.canonicalslug = 'sysadmin'
or e.email = 'sysadmin';
			 */
			final TypedQuery<Person> query = em.createQuery("SELECT p FROM " + Person.class.getName() + " p " 
					+ "WHERE p.username = :upUsername OR "
					+ "p.email = :upEmail", Person.class);
			
			query.setParameter("upUsername", tokenPrincipal);
			query.setParameter("upEmail", normalizedEmail);
			
//			rootPredicates.add(cb.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
			
			try {
				log.debug("[{}] findOne JpaDB for tokenPrincipal '{}' or email '{}' table person.",
						getName(), tokenPrincipal, normalizedEmail);
				final Person person = query.getSingleResult();
				log.debug("[{}] got result for person '{}': {}", getName(), tokenPrincipal, person.getId());
				final String userPrincipal = person.getUsername();
				final String userPassword = person.getPassword();
				return new SimpleAuthenticationInfo(userPrincipal, userPassword, getName());
			} catch (NoResultException e) {
				log.info("[{}] Cannot find user '{}' in table person using: tokenPrincipal '{}',  or email '{}'",
						getName(), tokenPrincipal, tokenPrincipal, normalizedEmail);
				throw new UnknownAccountException("[" + getName() + "] Cannot find user '" + tokenPrincipal + "'");
			}
		} else if (token instanceof AutologinToken) {
			log.debug("[{}] AuthenticationInfo for {} is using AutologinToken", getName(), token.getPrincipal());
			final String personId = (String) token.getPrincipal();
			return new SimpleAuthenticationInfo(personId, null, getName());
		} else {
			throw new AuthenticationException("[" + getName() + "] Unsupported AuthenticationToken: "
					+ token.getClass() + " using " + token.getPrincipal());
		}
	}

}

package com.rudiwijaya.grudi.jpa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * A representation of the model object '<em><b>TeamLine</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@Entity(name = "TeamLine")
public class TeamLine implements Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Basic(optional = false)
	@Type(type = "org.jadira.usertype.corejava.PersistentEnumAsPostgreSQLEnum", parameters = @Parameter(name = "enumClass", value = "com.rudiwijaya.grudi.jpa.TeamPersonKind"))
	private TeamPersonKind kind = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumns({ @JoinColumn(columnDefinition = "team_id", referencedColumnName = "id", table = "TeamLine") })
	private Team team = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Embedded()
	private PersonInfo person = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Version()
	private long versionNumber = 0;

	/**
	 * Returns the value of '<em><b>id</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>id</b></em>' feature
	 * @generated
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the '{@link TeamLine#getId() <em>id</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newId
	 *            the new value of the '{@link TeamLine#getId() id}' feature.
	 * @generated
	 */
	public void setId(Long newId) {
		id = newId;
	}

	/**
	 * Returns the value of '<em><b>kind</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>kind</b></em>' feature
	 * @generated
	 */
	public TeamPersonKind getKind() {
		return kind;
	}

	/**
	 * Sets the '{@link TeamLine#getKind() <em>kind</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newKind
	 *            the new value of the '{@link TeamLine#getKind() kind}'
	 *            feature.
	 * @generated
	 */
	public void setKind(TeamPersonKind newKind) {
		kind = newKind;
	}

	/**
	 * Returns the value of '<em><b>team</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>team</b></em>' feature
	 * @generated
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Sets the '{@link TeamLine#getTeam() <em>team</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newTeam
	 *            the new value of the '{@link TeamLine#getTeam() team}'
	 *            feature.
	 * @generated
	 */
	public void setTeam(Team newTeam) {
		if (team != newTeam) {
			if (team != null) {
				team.removeFromLines(this);
			}
			team = newTeam;
			if (team != null) {
				team.addToLines(this);
			}
		}
	}

	/**
	 * Returns the value of '<em><b>person</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>person</b></em>' feature
	 * @generated
	 */
	public PersonInfo getPerson() {
		return person;
	}

	/**
	 * Sets the '{@link TeamLine#getPerson() <em>person</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newPerson
	 *            the new value of the '{@link TeamLine#getPerson() person}'
	 *            feature.
	 * @generated
	 */
	public void setPerson(PersonInfo newPerson) {
		person = newPerson;
	}

	/**
	 * Returns the value of '<em><b>versionNumber</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>versionNumber</b></em>' feature
	 * @generated
	 */
	public long getVersionNumber() {
		return versionNumber;
	}

	/**
	 * Sets the '{@link TeamLine#getVersionNumber() <em>versionNumber</em>}'
	 * feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newVersionNumber
	 *            the new value of the '{@link TeamLine#getVersionNumber()
	 *            versionNumber}' feature.
	 * @generated
	 */
	public void setVersionNumber(long newVersionNumber) {
		versionNumber = newVersionNumber;
	}

	/**
	 * A toString method which prints the values of all EAttributes of this
	 * instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		return "TeamLine " + " [id: " + getId() + "]" + " [kind: " + getKind() + "]" + " [versionNumber: "
				+ getVersionNumber() + "]";
	}
}

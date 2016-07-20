package com.rudiwijaya.grudi.dao;

import javax.annotation.Nullable;

import com.rudiwijaya.grudi.data.PagingAndSortingRepository;
import com.rudiwijaya.grudi.jpa.Person;

/**
 * @author rudi
 *
 */
public interface JpaPersonRepository extends PagingAndSortingRepository<Person, Long> {
	
	@Nullable Person findOneByUsername(String username);
	
	boolean existsByUsername(String username);

}

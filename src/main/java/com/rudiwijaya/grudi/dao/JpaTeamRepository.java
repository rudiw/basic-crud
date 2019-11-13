package com.rudiwijaya.grudi.dao;

import com.rudiwijaya.grudi.data.PagingAndSortingRepository;
import com.rudiwijaya.grudi.jpa.Team;

/**
 * @author rudi
 *
 */
public interface JpaTeamRepository extends PagingAndSortingRepository<Team, Long> {
	
	boolean existsByName(String name);
	
	Team findOneWithLines(long id);

}

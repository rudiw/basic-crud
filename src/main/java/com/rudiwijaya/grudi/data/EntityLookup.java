package com.rudiwijaya.grudi.data;

import java.io.Serializable;

import javax.annotation.Nullable;

/**
 * A entity lookup function without cache.
 * @see CachingEntityLookup
 * @see RepositoryEntityLookup
 * @author ceefour
 */
public interface EntityLookup<T, ID extends Serializable> {

	/**
	 * Retrives an entity by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}
	 */
	@Nullable
	public <S extends T> S findOne(ID id);
	
}

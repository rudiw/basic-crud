package com.rudiwijaya.grudi.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * @author clutax
 *
 */
public abstract class PagingAndSortingRepositoryImpl<T, ID extends Serializable> implements PagingAndSortingRepository<T, ID> {
	
	@Override @Transactional
	public abstract <S extends T> Collection<S> add(Collection<S> entities);
	
	@Override @Transactional
	public <S extends T> S add(S entity) {
		return this.add(ImmutableList.of(entity)).iterator().next();
	}
	
	@Override @Transactional
	public abstract <S extends T> Collection<S> modify(Map<ID, S> entities);
	
	@Override @Transactional
	public <S extends T> S modify(ID id, S entity) {
		return this.modify(ImmutableMap.of(id, entity)).iterator().next();
	}
	
	@Override @Transactional
	public abstract long delete(Collection<? extends T> entities);
	
	@Override @Transactional
	public boolean delete(ID id) {
		this.deleteIds(ImmutableList.of(id));
		return true;
	}

	@Override @Transactional
	public boolean delete(T entity) {
		this.delete(ImmutableList.of(entity));
		return true;
	}

	@Override @Transactional
	public abstract long deleteIds(Collection<ID> ids);
	
	@Override @Transactional(readOnly=true)
	public abstract List<T> findAll();
	
	@Override @Transactional(readOnly=true)
	public abstract long count(Collection<ID> ids);
	
	@Override @Transactional(readOnly=true)
	public abstract long count();
	
	@Override @Transactional(readOnly=true)
	public abstract List<T> findAll(Collection<ID> ids);
	
	@Override @Transactional(readOnly=true)
	public abstract Page<T> findAll(Pageable pageable);
	
	@Override
	@Nullable @Transactional(readOnly=true)
	public abstract <S extends T> S findOne(ID id);

	@Override @Transactional(readOnly=true)
	public abstract List<T> findAll(Sort sort);
	
	@Override @Transactional(readOnly=true)
	public abstract List<T> findAll(Collection<ID> ids, Sort sort);
	
	@Override @Transactional(readOnly=true)
	public abstract boolean exists(ID id);

}

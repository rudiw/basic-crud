package com.rudiwijaya.grudi.dao;

import java.io.Serializable;

import com.rudiwijaya.grudi.data.PagingAndSortingRepository;

/**
 * @author clutax
 *
 * @param <T>
 * @param <ID>
 */
public interface JpaRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

}
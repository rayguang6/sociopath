package com.sociopath.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sociopath.model.entity.StatusUpdate;

@Repository
public interface StatusUpdateRepository extends PagingAndSortingRepository<StatusUpdate, Long> {
	StatusUpdate findFirstByOrderByAddedDesc();
}
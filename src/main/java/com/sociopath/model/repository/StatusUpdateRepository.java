package com.sociopath.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sociopath.model.entity.StatusUpdate;

public interface StatusUpdateRepository extends PagingAndSortingRepository<StatusUpdate, Long> {
	StatusUpdate findFirstByOrderByAddedDesc();
}
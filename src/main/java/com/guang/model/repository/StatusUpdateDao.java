package com.guang.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.guang.model.entity.StatusUpdate;

public interface StatusUpdateDao extends PagingAndSortingRepository<StatusUpdate, Long> {
	StatusUpdate findFirstByOrderByAddedDesc();
}
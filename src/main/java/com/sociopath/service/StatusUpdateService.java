package com.sociopath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sociopath.model.entity.StatusUpdate;
import com.sociopath.model.repository.StatusUpdateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StatusUpdateService {
	
	@Value("${status.pagesize}")
	private int pageSize;
	
	@Autowired
	private StatusUpdateRepository statusUpdateRepository;
	
	public void save(StatusUpdate statusUpdate) {
		statusUpdateRepository.save(statusUpdate);
	}
	
	public StatusUpdate getLatest() {
		return statusUpdateRepository.findFirstByOrderByAddedDesc();
	}
	
	public Page<StatusUpdate> getPage(int pageNumber) {
		PageRequest request = PageRequest.of(pageNumber-1, pageSize, Sort.Direction.DESC, "added");
		
		return statusUpdateRepository.findAll(request);
	}

	public void delete(Long id) {
		statusUpdateRepository.deleteById(id);
	}

	public StatusUpdate get(Long id) {
		return statusUpdateRepository.findById(id).get();
	}
}
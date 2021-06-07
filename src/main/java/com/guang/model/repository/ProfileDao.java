package com.guang.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.CrudRepository;

import com.guang.model.entity.Profile;
import com.guang.model.entity.SiteUser;

public interface ProfileDao extends Neo4jRepository<Profile, Long> {	
	Profile findByUser(SiteUser user);

	List<Profile> findByInterestsNameContainingIgnoreCase(String text);
	Page<Profile> findByInterestsNameContainingIgnoreCase(String text, Pageable request);
}
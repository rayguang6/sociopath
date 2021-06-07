package com.guang.model.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guang.model.entity.SiteUser;

@Repository
public interface UserDao extends Neo4jRepository<SiteUser, Long> {
	SiteUser findByEmail(String email);
	
}
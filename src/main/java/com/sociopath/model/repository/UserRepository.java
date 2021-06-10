package com.sociopath.model.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sociopath.model.entity.Users;

@Repository
public interface UserRepository extends Neo4jRepository<Users, Long> {
	Users findByUsername(String username);

	@Query("MATCH (n:Users) WHERE n.username = $username DELETE n")
	void deleteByUsername(String username);
	
	
}
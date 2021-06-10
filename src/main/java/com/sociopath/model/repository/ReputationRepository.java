package com.sociopath.model.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.sociopath.model.entity.Users;
import com.sociopath.model.entity.ReputationRelation;
import com.sociopath.model.entity.Student;

public interface ReputationRepository extends Neo4jRepository<ReputationRelation, Long> {	
	


	
	
}
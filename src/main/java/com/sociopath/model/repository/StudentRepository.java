package com.sociopath.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.sociopath.model.entity.Users;
import com.sociopath.model.entity.Student;

public interface StudentRepository extends Neo4jRepository<Student, Long> {	
	
	Student findByUser(Users user);
	Student findByUsername(String username);
	
	@Query("MATCH (n:Student) WHERE n.username = $username DELETE n")
	Student deleteByUsername(String username);
}
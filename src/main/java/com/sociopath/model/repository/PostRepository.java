package com.sociopath.model.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.sociopath.model.dto.FriendResult;
import com.sociopath.model.entity.Post;
import com.sociopath.model.entity.Users;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {
	
	@Query("MATCH (n:Post) RETURN n ")
	List<Post> getPosts();
}

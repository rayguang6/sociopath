//package com.sociopath.model.repository;
//
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//import org.springframework.data.neo4j.repository.Neo4jRepository;
//import org.springframework.data.neo4j.repository.query.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.sociopath.model.entity.Message;
//import com.sociopath.model.entity.Student;
//
//@Repository
//public interface MessageDao extends Neo4jRepository<Message, Long> {	
//	
//	@Query("MATCH (a:Student)-[:SENT_FROM]-(m:Message)-[:SENT_TO]-(b:Student) where id(a)= $toUser AND id(b)=$fromUser RETURN m")
//	Slice<Message> fetchConversation( Long toUser, Long fromUser, Pageable pageable);
//
//	Page<Message> findByToUserIdAndReadFalseOrderBySentDesc(Long toUserId, Pageable request);
//	
//	
//}
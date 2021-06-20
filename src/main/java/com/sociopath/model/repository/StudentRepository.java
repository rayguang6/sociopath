package com.sociopath.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sociopath.model.entity.Users;
import com.sociopath.model.dto.FriendResult;
import com.sociopath.model.dto.SearchResult;
import com.sociopath.model.entity.Post;
import com.sociopath.model.entity.ReputationRelation;
import com.sociopath.model.entity.Student;

@Repository
public interface StudentRepository extends Neo4jRepository<Student, Long> {	
	



	Student findByUser(Users user);
	
	Student findByUsername(String username);
	
	@Query("MATCH (n:Student) WHERE n.username = $username DELETE n")
	Student deleteByUsername(String username);
	
	@Query("MATCH  (a:Student {username:$student1}), (b:Student {username:$student2}) RETURN EXISTS( (a)-[:REPUTATIONS]->(b) )")
	boolean checkRepRelationExist(String student1, String student2);
	
	@Query("MATCH  (a:Student {username:$student1}), (b:Student {username:$student2}) RETURN EXISTS( (a)-[:FRIENDS]-(b) )")
	boolean checkFriendRelationExist(String student1, String student2);
	
	@Query("MATCH (a:Student) WHERE a.username= $student RETURN a.username")
	Student hasStudent(String student);
	
	@Query("MATCH(a:Student)-[r:REPUTATIONS]->(b:Student)WHERE a.username = $student1 AND b.username = $student2 SET r.point = r.point+ $point")
	Student updatePoint(String student1, String student2, int point);

	@Query("MATCH(a:Student),(b:Student)WHERE a.username = $student1 AND b.username = $student2 CREATE (a)-[r:REPUTATIONS{point:$point}]->(b)")
	Student createRep(String student1, String student2, int point);
	
	@Query("MATCH(a:Student)-[r:REPUTATIONS]->(b:Student) WHERE a.username =$source AND b.username =$destination RETURN r.point")
	Integer getRep(String source, String destination);

	@Query("MATCH(a:Student) WHERE a.username = $username SET a.indeg=a.indeg+1" )
	void increaseIndeg(String username);

	@Query("MATCH(a:Student) WHERE a.username = $username SET a.outdeg = a.outdeg+1" )
	void increaseOutdeg(String username);

	@Query("MATCH (a)-[r:FRIENDS]-(b) WHERE a.username=$username RETURN b")
	ArrayList<Student> getFriendsByUsername(String username);

	@Query("MATCH (a)-[r:FRIENDS]-(b) WHERE a.username=$username RETURN b.username")
	List<String> getFriendsName(String username);
	
	@Query("MATCH (a)-[r:FRIENDS]-(b) RETURN a,b")
	List<FriendResult> getAllFriends();

	@Query("MATCH(a:Student),(b:Student)WHERE a.username = $student1 AND b.username = $student2 CREATE (a)-[r:FRIENDS]->(b)")
	void createFriend(String student1, String student2);

	@Query("MATCH(a:Student) RETURN a.username")
	List<String> findAllUsername();

	
	
	
	
	
	///////////////////////////////////////////////////////////
	///////Below are for event 5 for temporary
	
	@Query("MATCH(a:VIP) RETURN COUNT(a)")
	int getTotalVipStudent();
	
	@Query("MATCH (a:VIP) RETURN id(a)")
	ArrayList<Integer> getVipId();

	@Query("MATCH (n:VIP) WHERE id(n) = $id RETURN n.username")
	String getVipUsernameById(int id);

	@Query("MATCH (n:VIP) WHERE id(n) = $id RETURN n")
	Student getStudentById(int id);

	@Query("MATCH (n:Student)-[r:FRIENDS]-(b) WHERE id(n)=$id RETURN id(b) ")
	ArrayList<Integer> getFriendsIdListById(int id);

//	@Query("MATCH(n:Student)<-[r:REPUTATIONS]-(b:Student) RETURN n.username, SUM (r.point) ORDER BY SUM (r.point)DESC")
//	Map<String,Integer> getReputationRanking();
	
	
	//get the sorted list of username first
	@Query("MATCH(n:Student)<-[r:REPUTATIONS]-(b:Student) ORDER BY SUM (r.point)DESC RETURN n.username")
	ArrayList<String> getReputationRanking();

	@Query("MATCH(n:Student)<-[r:REPUTATIONS]-(b:Student)WHERE n.username=$thisStudent RETURN SUM (r.point)")
	Integer getHisTotalPoint(String thisStudent);

	ArrayList<SearchResult> findByUsernameContainingIgnoreCase(String text);

//	@Query("MATCH (n:Student)-[r:REPUTATIONS]->() RETURN ")
//	List<Integer> getMyReputation(String username);

	
	@Query("MATCH(n:Student)-[r:REPUTATIONS]->(b:Student)WHERE n.username=$source AND b.username=$destination DELETE r ")
	void deleteRep(String source, String destination);

	@Query("MATCH(n:Student)-[r:REPUTATIONS]-(b:Student) WHERE n.username=$source AND b.username=$destination DELETE r ")
	void deleteBothRep(String source, String destination);

	@Query("MATCH(n:Student)-[r:REPUTATIONS]-(b:Student) DELETE r ")
	void deleteAllRep();

	@Query("MATCH(n:Student)-[r:FRIENDS]-(b:Student) WHERE n.username=$source AND b.username=$destination DELETE r ")
	void deleteBothFriend(String source, String destination);
	
	@Query("MATCH(n:Student)-[r:FRIENDS]-(b:Student) DELETE r ")
	void deleteAllFriend();

	//later need to condition must friend
	@Query("MATCH(a:Student)<-[r:REPUTATIONS]-(b:Student) WHERE a.username=$username AND r.point<0 RETURN b")
	List<Student> getFrenemy(String username);

	@Query("MATCH(a:Student)<-[r:REPUTATIONS]-(b:Student) WHERE a.username=$username AND r.point>=0 RETURN b")
	List<Student> getRealFriend(String username);
	
	@Query("MATCH (n:Student {username: $student}) CREATE (n) -[r:POSTED]-> (p: Post {text:$text, owner: n.username})")
	void createPost(String student, String text);

	
	

}
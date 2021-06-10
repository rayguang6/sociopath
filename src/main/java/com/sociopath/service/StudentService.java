package com.sociopath.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import com.sociopath.model.entity.ReputationRelation;
import com.sociopath.model.entity.Student;
import com.sociopath.model.entity.Users;
import com.sociopath.model.repository.ReputationRepository;
import com.sociopath.model.repository.StudentRepository;
import com.sociopath.model.repository.UserRepository;

@Service
public class StudentService <T extends Comparable<T>, N extends Comparable<N>>{

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ReputationRepository reputationRepository;
	
	//call the repo & to save studentProfile into database
	public void save(Student student) {
		studentRepository.save(student);
	}
	
	//The username in Student is linked to username in Users
	//We use the username in the Users Class to get the student profile
	public Student getStudent(String username) {
		return studentRepository.findByUsername(username);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public void godDeleteStudent(String username) {
		studentRepository.deleteByUsername(username);
	}
	
	
	// TODO !!! 等下要记得indeg outdeg ， 还有 那个rep list
	public void addRelation(String student1, String student2, int rep_point) {
		
		boolean existed = studentRepository.checkRepRelationExist(student1, student2);
		
		if(existed) {
			
			Student studentFrom = studentRepository.findByUsername(student1);
			Student studentTo = studentRepository.findByUsername(student2);
			
			List<ReputationRelation> reputationList = new ArrayList<>(); //这个是student的 field
			
			ReputationRelation relation = new ReputationRelation();   //这个是relation class
	        relation.setPoint(rep_point);
	        relation.setTargetStudent(studentTo);
	        
	        reputationList.add(relation);
	        studentFrom.setReputationList(reputationList);
	        
	        studentRepository.updatePoint(student1, student2, rep_point);
	        studentRepository.save(studentTo);
			studentRepository.save(studentFrom);
			
		}else {
			studentRepository.addRelation(student1, student2, rep_point);
		}
	}
	
	
	
	
	
	//////////////////////////////////////////////// BELOW are 青丝的code  
	//Add edge 就是简单的   repository.save  要用到的时候才直接弄 
	
	
	public boolean hasStudent(String username) {
		Student student = studentRepository.findByUsername(username);
		if(student!=null) {
			return true;
		}
		return false;
	}
	
	public boolean checkRepRelationExist(String source, String destination) {
		return studentRepository.checkRepRelationExist(source, destination);
	}
	
	
	public boolean addEdge(String source, String destination, int rep, boolean friend) {
		
		if (!hasStudent(source) || !hasStudent(destination)) {
            return false;
        }
		
		if(checkRepRelationExist(source,destination)) {
			return false;
		}
		
		//用username    find 那个student
		Student sourceStudent = studentRepository.findByUsername(source);
		Student destStudent = studentRepository.findByUsername(destination);
		
		studentRepository.addRelation(source, destination, rep); //基本上这里已经解决问题了

		List<ReputationRelation> reputationList = sourceStudent.getReputationList(); //这个是student的 field

		ReputationRelation relation = new ReputationRelation();   //这个是relation class
        relation.setPoint(rep);
        relation.setTargetStudent(destStudent);
        
        reputationList.add(relation);
        sourceStudent.setReputationList(reputationList);
        
        increaseIndeg(destination);
        increaseOutdeg(source);
        
        if(friend) sourceStudent.friendList.add(destination);
        
        reputationRepository.save(relation);
	       
		return true;
	}
	
	public boolean addUndirectedEdge(String source, String destination){
        return addEdge(source, destination, 0, true) && addEdge(destination, source, 0, true);
    }
	
	public void updateRep(String source, String destination, int weight, boolean friend) {
		
		studentRepository.updatePoint(source, destination, weight);
		
		Student<T, N> sourceV = studentRepository.findByUsername(source);
		
		if(friend) sourceV.friendList.add((T) destination);
		
	} 
	
	public int getRep(String source, String destination) { //get rep point between 2 nodes
        return studentRepository.getRep(source, destination);
    }
	
	public ArrayList<T> getFriends(String student) { //print friends of one
        if (!hasStudent(student)) {
            return null;
        }
        
        ArrayList<T> list = new ArrayList<T>();
        
        Student<T, N> temp = studentRepository.findByUsername(student);
        list = temp.friendList;
        
        
        return list;
    }

	
	public void increaseIndeg(String username) {
		 studentRepository.increaseIndeg(username);
	}
	
	public void increaseOutdeg(String username) {
		 studentRepository.increaseOutdeg(username);
	}

	
	
	
	
}

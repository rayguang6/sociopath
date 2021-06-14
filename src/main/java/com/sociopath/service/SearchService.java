package com.sociopath.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import com.sociopath.model.dto.FriendResult;
import com.sociopath.model.dto.SearchResult;
import com.sociopath.model.entity.CrushGraph;
import com.sociopath.model.entity.Friendship;
import com.sociopath.model.entity.FriendshipList;
import com.sociopath.model.entity.ReputationRelation;
import com.sociopath.model.entity.Student;
import com.sociopath.model.entity.Users;
import com.sociopath.model.repository.ReputationRepository;
import com.sociopath.model.repository.StudentRepository;
import com.sociopath.model.repository.UserRepository;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForImplementation;

/*
 * the indeg & outdeg is only for reputation relationship
 * 
 */



@Service
public class SearchService <T extends Comparable<T>, N extends Comparable<N>>{

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ReputationRepository reputationRepository;
	
	public ArrayList<SearchResult> search(String text){
		
		return studentRepository.findByUsernameContainingIgnoreCase(text);
	}
		
		
}

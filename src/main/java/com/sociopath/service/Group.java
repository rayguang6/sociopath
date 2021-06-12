package com.sociopath.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import com.sociopath.model.dto.FriendResult;
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
public class Group <T extends Comparable<T>, N extends Comparable<N>>{

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ReputationRepository reputationRepository;
	
	
	//////////////////////////////////////////////////////
	///////////////   BASIC FUNCTIONS   //////////////////
	//////////////////////////////////////////////////////
	
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
	
	public List<String> getAllUsername() {
		return studentRepository.findAllUsername();
	}

	public void deleteStudent(String username) {
		studentRepository.deleteByUsername(username);
	}
	
	public boolean hasStudent(String username) {
		Student student = studentRepository.findByUsername(username);
		if(student!=null) {
			return true;
		}
		return false;
	}
	
	public boolean isFriend(String username1, String username2) {
		return studentRepository.checkFriendRelationExist(username1,username2);
	}
	
	public void increaseIndeg(String username) {
		 studentRepository.increaseIndeg(username);
	}
	
	public void increaseOutdeg(String username) {
		 studentRepository.increaseOutdeg(username);
	}
	
	public boolean hasRep(String source, String destination) {
		return studentRepository.checkRepRelationExist(source, destination);
	}
	
//////////////////////////////////////////////////////
/////////////   BASIC FUNCTIONS END   ////////////////
//////////////////////////////////////////////////////
	
	
	
	
	//###################################################//
	//###########   SIMPLE OPERATIONS CRUD  ############//
	//##################################################//
	
	
	//这里没有Add edge ，因为就是简单的   repository.save  要用到的时候才直接弄 

	
	public boolean createRep(String source, String destination, int rep) {
		
		if (!hasStudent(source) || !hasStudent(destination)) {
            return false;
        }

		if(hasRep(source,destination)) {
			return false;
		}
		
		//用username    find 那个student
		Student sourceStudent = studentRepository.findByUsername(source);
		Student destStudent = studentRepository.findByUsername(destination);
		
		studentRepository.createRep(source, destination, rep); //基本上这里已经解决问题了
		increaseIndeg(destination);
		increaseOutdeg(source);
        
        
		List<ReputationRelation> reputationList = sourceStudent.getReputationList(); //这个是student的 field

		ReputationRelation relation = new ReputationRelation();   //这个是relation class
        relation.setPoint(rep);
        relation.setTargetStudent(destStudent);
        
        reputationList.add(relation);
        sourceStudent.setReputationList(reputationList);
   
       
        reputationRepository.save(relation);
	       
		return true;
	}
	
	
	//!!!!   这个是什么用处？？
	public boolean addUndirectedEdge(String source, String destination){
        return createRep(source, destination, 0) && createRep(destination, source, 0);
    }
	
	public void updateRep(String source, String destination, int weight) {
		studentRepository.updatePoint(source, destination, weight);
	} 
	
	public int getRep(String source, String destination) { //get rep point between 2 nodes
        return studentRepository.getRep(source, destination);
    }
	
	
//	public ArrayList<Student> getFriends(String student) { //print friends of one
//        if (!hasStudent(student)) {
//            return null;
//        }
//        
//        ArrayList<Student> list = new ArrayList<Student>();
//        
//        Student<T, N> temp = studentRepository.findByUsername(student);
//        list = temp.friendList;
//        
//        return list;
//    }

	
	// Create Friend Relation between 2 students
	public Student createFriend(String source, String destination) {
	
		studentRepository.createFriend(source,destination);   //usually this alrdy done the job using query
		
		Student student1 = studentRepository.findByUsername(source);
		Student student2 = studentRepository.findByUsername(destination);
		
		List<Student> friendshipList = student1.getFriendshipList();
		friendshipList.add(student2);
			
		studentRepository.createFriend(source,destination);
		
		studentRepository.save(student1);
		studentRepository.save(student2);
		
		return student1;
	}
	
	public List<Student> getFriendsByUsername(String username) {
		return studentRepository.getFriendsByUsername(username);
	}
	
	public List<String> getFriendsName(String username) {
		return studentRepository.getFriendsName(username);
	}
	
	
	
	public Map<String, List<String>> getAllFriendsUsername() {

		Map<String, List<String>> friendsNameMap = new HashMap<>();
		
		List<Student> listOfStudents = getAllStudents();
		
		for (int i=0; i<listOfStudents.size(); i++) {
			
			Student thisStudent = listOfStudents.get(i);
			
			String thisUsername = thisStudent.getUsername();
			
			List<String> namesOfFriends = getFriendsName(thisUsername);
			
			
			
			friendsNameMap.put(thisUsername, namesOfFriends);
			
		}
		
		return friendsNameMap;
	}

	//To get all friends of all students
	//Map Of Students
	public Map<Student, List<Student>> getAllFriends() {

		Map<Student, List<Student>> friendsMap = new HashMap<>();
		
		List<Student> listOfStudents = getAllStudents();
		
		for (int i=0; i<listOfStudents.size(); i++) {
			
			Student thisStudent = listOfStudents.get(i);
			
			String thisUsername = thisStudent.getUsername();
			
			List<Student> friendList = getFriendsByUsername(thisUsername);
			
			
			friendsMap.put(thisStudent, friendList);
			
		}
		
		return friendsMap;
	}
	
	//try using this to retrieve data for the meet crush
//	public ArrayList<ArrayList<Integer>> graphForCrush() {
//
//		ArrayList<ArrayList<Integer>> path = new ArrayList<>();
//		
//		List<Student> listOfStudents = getAllStudents();
//		
//		
//		for (int i=0; i<listOfStudents.size(); i++) {
//			
//			Student thisStudent = listOfStudents.get(i);
//			
//			String thisUsername = String.valueOf(thisStudent.getUsername());
//			
//			List<String> studentNameList= getFriendsByUsername(thisUsername);
//			
//			ArrayList<Integer> resultList = getIntegerArray(strArrayList);
//		}
//		
//		return path;
//	}
	
	
	
	
	
	
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
	/////////////////  GOD ACTIONS -> some method for convenience//////////////////////
	//////////////// To quickly manipulate database without DB ///////////////////////
	
	


		/*/////////////////////////////////////////////////////////////////////////////////////////////// |||
	|||	//////////////////BELOW ARE  Sociopath Assignment Event////////////////////////////////////////// vvv
	vvv	///////////////////////////////////////////////////////////////////////////////////////////////*/
		
		
	//EVENT1
	public boolean teachStranger(String mentee, String mentor, int rep_point) {
		/*if friend, return false (but actually we'll limit the input, which means student can only pick from non-friend mentor)
		 * 
		 */
		
		if (!hasStudent(mentee) || !hasStudent(mentor)) {
            return false;
        }
		
		if (isFriend(mentee,mentor)) {
            return false;
        }
		
		
		
		if(hasRep(mentee,mentor)) {
			updateRep(mentee,mentor,rep_point);
		}else {
			createRep(mentee, mentor, rep_point);
		}
		
		createFriend(mentee,mentor);
		return true;
	}
	
	//EVENT 2
	public boolean chitchat(String talker, String listener, String rumors, boolean good){ //feature 2
        
        if (!hasStudent(talker) || !hasStudent(listener) || !hasStudent(rumors)) {
            return false;
        }
        //basic requirement: talker should know both listener and rumors
        if (!isFriend(talker,listener) || !isFriend(talker,rumors)) {
            return false;
        }
        
        int weight = getRep(talker, rumors); 
        
        if (hasRep(listener, rumors)) { //如果他们已经有刻板印象 （也包括如果他们是朋友） //modidication of point
            if(good){
                 updateRep(listener, rumors, weight / 2);
                return true;
            }else{
                 updateRep(listener, rumors, weight * -1);
                 return true;
            }
        } else { //如果他们毫不相识 
            if (good) {
                return createRep(listener, rumors, weight / 2);
            } else {
                return createRep(listener, rumors, weight * -1);
            }
        }
    }
	
		
	public void haveLunch(String[] student, String me, int day){ // updated feature 3: PARALLEL FARMING
        Student<T, N>[] ary=new Student[student.length];
        Student<T, N> ME=new Student<>();
        //inseting students into a list
        
        Student meMyself = studentRepository.findByUsername(me);
        while(meMyself.lunchPeriod.size()<day){
        	meMyself.generateTime();
        }
        meMyself.calculateAverage(day);
        ME=meMyself;
        
        
        
        for(int i=0; i<student.length; i++){
        	
        	Student sourceV =  studentRepository.findByUsername(student[i]);
        	
        	while(sourceV.lunchPeriod.size()<day){
                sourceV.generateTime();
            }
            sourceV.calculateAverage(day);
            ary[i]=sourceV;

        }
        
      //sort accroding priority, less time used will be priotized, first observed has least time
        Arrays.sort(ary);
        //ArrayList indicates the schedule plan with who, start time and end time
        ArrayList<T> people = new ArrayList<>();
        //declare an array with minutes value of the observant
        int[] time_slot = new int[ME.average_lunchPeriod];
        //calculate my minute value of start time and end time
        int my_start_min = calcMin(ME.average_lunchStart);
        int my_end_min = calcMin(ME.end_time);
        //print my eating time
        System.out.println("My eating time : " + ME.average_lunchStart + " " + ME.average_lunchPeriod + " " + ME.end_time);
        //go for loop to check every individual time of eating 
        System.out.println("[people]starting time--period---end time");
        for (int i = 0; i < ary.length; i++) {
            //calculate their minute value of eating time
            int start_min = calcMin(ary[i].average_lunchStart);
            int end_min = calcMin(ary[i].end_time);
            //to display value for checking purpose only
            System.out.println("[" + ary[i].getUsername() + "]" + ary[i].average_lunchStart + " " + ary[i].average_lunchPeriod + " " + ary[i].end_time);
            //calculating slot in minutes value 
            //check is there intersection on observant and observer time of eating, if no, straight print result
            //his_start---mystart----his_end----myend 
            //mystart---his_start---myend---his_end 
            //his_start--mystart---myend---his_end
            if (end_min >= my_start_min && end_min <= my_end_min
                    || start_min >= my_start_min && start_min <= my_end_min
                    || start_min <= my_start_min && end_min >= my_end_min) {
                //compare with array, to check whether the table is full or not
                boolean full = false;
                for (int ss = 0; ss < ME.average_lunchPeriod; ss++) {
                    if (time_slot[ss] >= 3) {
                        full = true;
                        break;
                    }
                }
                //if the table is not full with 3 people yet, they can eat with them
                if (!full) {
                    people.add((T) ary[i].getUsername());
                    int temp = 0;
                    if (start_min >= my_start_min) {
                        temp = start_min - my_start_min;
                    }
                    for (int s = temp; s < end_min - my_start_min; s++) {
                        if (s >= time_slot.length) {
                            break;
                        }
                        time_slot[s] = time_slot[s] + 1;
                    }
                    for (int v = 0; v < time_slot.length; v++) {
                        if (v >= temp && v < end_min - my_start_min) {
                            System.out.print(ary[i].getUsername());
                        } else {
                            System.out.print(0);
                        }
                    }
                    System.out.println("");
                }
            }
        }
        //printing the lunch time plan
        System.out.println("Target Lunch Partner: ");
        System.out.println(people);
        //how much people he can have lunch with
        System.out.println("Total target: " + people.size());
    }
	
	
	//method to calculate the minute value of student lunch time
    public int calcMin(int time){
        int min=time%100;
        if(time/100==12){
            min=min+60;
        }else if(time/100==13){
            min=min+120;
        }else if(time/100==14){
            min=180;
        }
        return min;
    }
    
    
    //event 4
    public static List<String> arrangeBook(String books){
    	
        String[] bookArray = books.split(" ");
        List<String> resultList = new ArrayList<>();
        
        int size = bookArray.length;
        
        int[] line = new int[size];       //book array with different height
        
        for (int i = 0; i < bookArray.length; i++) {
            line[i]= Integer.parseInt(bookArray[i]);
        }

    	
        MyStack<Integer> stck=new MyStack<>();
        boolean action=true;
        int round=0;
        while(action){
            action=false;
            stck.push(line[0]);
            /*
            if book arrangement: 13 16 12 17 15, book on shelf: array; book left on shelf after arrangment:stack
            first round: 16 17 taken out, left: 13 12 15 (stack)
            second round: 15 taken out, left: 13 12 (stack)
            */
            for(int j=1; j<size; j++){
                if(line[j]>line[j-1]){
                    action=true; //if there is book higher than the previous one, book is taken out.
                }else{
                    stck.push(line[j]);//is the book is already lower than previous one, no need take out, put to the stack.
                }
            }
            if(action) round++; //if there is books picked out, mean that action had been done, thus round++
            else break; //if no action taken out, end the loop
            size=stck.getSize();
            for(int j=size-1; j>=0; j--){
                line[j]=stck.pop();
            } //restoring the book in stack into array for next round of arrangement
            System.out.print(round+" .  ");
            
            //###########################
            String resultString="";
            
            for(int j=0; j<size; j++){
                System.out.print(line[j]+" ");
                resultString += String.valueOf(line[j]+" "); //##########################
            }
            System.out.println("");
            
            resultList.add(resultString);
            //#################
            
        }
      
        return resultList;
    }

    public static ArrayList<FriendshipList> event6(int[] student1, int[] student2, int num){
    	
    	Friendship f = new Friendship(num+1);
    	
    	for(int i=0; i<num; i++){
            f.addEdge(student1[i], student2[i]);
        }
    	
    	ArrayList<FriendshipList> result= f.Pathlist(1);
    	Collections.sort(result);
    	
		return result;
    	
    }
	
		
		
}

package com.sociopath.model.entity;

//main project
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.sociopath.model.dto.FileInfo;
import com.sociopath.model.entity.Student;
import com.sociopath.model.repository.StudentRepository;

@Node(labels = { "Student" })
public class Student<T extends Comparable<T>, N extends Comparable<N>> implements Comparable<Student> {

	@Id
	@GeneratedValue
	private Long id;
	

//	@OneToOne(targetEntity = Users.class)
//	@JoinColumn(name = "user_id", nullable = false)
	private Users user;

	// Refer To Username in Users.class
	private String username;

	private String about;

	private String photoDirectory;

	private String photoName;

	private String photoExtension;

//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(name="profile_interests", 
//	joinColumns={ @JoinColumn(name="profile_id") },
//	inverseJoinColumns = { @JoinColumn(name="interest_id") } )
//	@OrderColumn(name="display_order")
//	private Set<Interest> interests;

	public T vertexInfo;
	int reputation;
	int divingrate;
	public int average_lunchStart;
	public int average_lunchPeriod;
	public int end_time;
	ArrayList<Integer> lunchStart = new ArrayList<>();
	public ArrayList<Integer> lunchPeriod = new ArrayList<>();
	int lastCheck ;
	public int outdeg;
	public int indeg;
	/// Edge<T,N> relativeRep; 暂时搬去下面
	Student<T, N> nextVertex;
	
	public ArrayList<Student> friendList = new ArrayList<>(); // this is to show friend list only

	// relationship
	@Relationship(type = "REPUTATIONS", direction = Relationship.Direction.OUTGOING)
	public List<ReputationRelation> reputationList = new ArrayList<>();;

	//Friend 
	@Relationship(type = "FRIENDS", direction = Relationship.Direction.OUTGOING)
	private List<Student> friendshipList = new ArrayList<>() ;

	// constructor

	public Student() {
//		vertexInfo = null;
//		nextVertex = null;
//		relativeRep = null;
		this.divingrate = (int) (Math.random() * 100);
		reputation = 10 - (divingrate / 10);// if diving rate high, reputation low
		if (reputation == 0)
			reputation = 1;
		
	}

	public Student(T vInfo, Student<T,N> next) {
	      vertexInfo = vInfo;
	      nextVertex = next;
	      reputationList = null;
	      divingrate = (int) (Math.random() * 100);
	      reputation = 10-(divingrate/10);//if diving rate high, reputation low
	      if(reputation==0) reputation=1;
	      lunchStart.add(setTime());
	      lunchPeriod.add((int) (Math.random() * 56)+ 5);
	   }

	public int setTime() {
		int min = (int) (Math.random() * 181);
		int temp = 1100;
		while (min >= 60) {
			temp = temp + 100 - 60;
			min = min - 60;
		}
		temp = temp + min;
		return temp;
	}

	public void generateTime() {
		lunchStart.add(setTime());
		lunchPeriod.add((int) (Math.random() * 54) + 6); // (6-59)
	}

	public void calculateAverage(int day) {
		// to calculate average lunch start time
		if (day != lastCheck) {
			int min = 0, hr = 0, resultmin, resulthr, temp;
			for (int i = 0; i < day; i++) {
				min = min + lunchStart.get(i) % 100;
			}
			for (int i = 0; i < day; i++) {
				hr = hr + lunchStart.get(i) / 100;
			}
			resulthr = hr / day;
			temp = hr % day;
			min = min + temp * 60;
			resultmin = min / day;
			while (resultmin >= 60) {
				resulthr += 1;
				resultmin -= 60;
			}
			average_lunchStart = resulthr * 100 + resultmin;

			// to calculate average lunch period
			int sumPeriod = 0;
			for (int j = 0; j < day; j++) {
				sumPeriod = sumPeriod + lunchPeriod.get(j);
			}
			average_lunchPeriod = sumPeriod / day;

			// to calculate average lunch end time
			int endMinute = average_lunchStart % 100 + average_lunchPeriod;
			if (endMinute >= 60) { // using if instead of while is because largest value of min will be 59 + 59 =
									// 118
				end_time = (average_lunchStart / 100 * 100) + 100 + (endMinute - 60);
			} else {
				end_time = average_lunchStart + average_lunchPeriod;
			}
			// update lastCheck
			lastCheck = day;
		}
	}

	public List<ReputationRelation> getReputationList() {
		return reputationList;
	}

	public void setReputationList(List<ReputationRelation> reputationList) {
		this.reputationList = reputationList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	/* Create a profile that is suitable for displaying via JSP */
//	public void safeCopyFrom(Student other) {
//		if (other.about != null) {
//			this.about = other.about;
//		}
//
//		if (other.interests != null) {
//			this.interests = other.interests;
//		}
//	}

	/* Create a profile that is suitable for saving */
	public void safeMergeFrom(Student webProfile) {
		if (webProfile.about != null) {
			this.about = webProfile.about;
		}
	}

	public String getPhotoDirectory() {
		return photoDirectory;
	}

	public void setPhotoDirectory(String photoDirectory) {
		this.photoDirectory = photoDirectory;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoExtension() {
		return photoExtension;
	}

	public void setPhotoExtension(String photoExtension) {
		this.photoExtension = photoExtension;
	}

	public void setPhotoDetails(FileInfo info) {
		photoDirectory = info.getSubDirectory();
		photoExtension = info.getExtension();
		photoName = info.getBasename();
	}

	public Path getPhoto(String baseDirectory) {
		if (photoName == null) {
			return null;
		}

		return Paths.get(baseDirectory, photoDirectory, photoName + "." + photoExtension);
	}
	
	

//	public Set<Interest> getInterests() {
//		return interests;
//	}
//
//	public void setInterests(Set<Interest> interests) {
//		this.interests = interests;
//	}
//
//	public void addInterest(Interest interest) {
//		interests.add(interest);
//	}
//
//	public void removeInterest(String interestName) {
//		interests.remove(new Interest(interestName));
//	}

//	@Override
//	public String toString() {
//		return "Student [id=" + id + ", user=" + user + ", about=" + about + ", photoDirectory=" + photoDirectory
//				+ ", photoName=" + photoName + ", photoExtension=" + photoExtension + ", interests=" + interests + "]";
//	}
	
	
	//Other Getter & Setter

	public int getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(int lastCheck) {
		this.lastCheck = lastCheck;
	}


	public T getVertexInfo() {
		return vertexInfo;
	}

	public void setVertexInfo(T vertexInfo) {
		this.vertexInfo = vertexInfo;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public int getDivingrate() {
		return divingrate;
	}

	public void setDivingrate(int divingrate) {
		this.divingrate = divingrate;
	}

	public int getAverage_lunchStart() {
		return average_lunchStart;
	}

	public void setAverage_lunchStart(int average_lunchStart) {
		this.average_lunchStart = average_lunchStart;
	}

	public int getAverage_lunchPeriod() {
		return average_lunchPeriod;
	}

	public void setAverage_lunchPeriod(int average_lunchPeriod) {
		this.average_lunchPeriod = average_lunchPeriod;
	}

	public int getEnd_time() {
		return end_time;
	}

	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}

	public ArrayList<Integer> getLunchStart() {
		return lunchStart;
	}

	public void setLunchStart(ArrayList<Integer> lunchStart) {
		this.lunchStart = lunchStart;
	}

	public ArrayList<Integer> getLunchPeriod() {
		return lunchPeriod;
	}

	public void setLunchPeriod(ArrayList<Integer> lunchPeriod) {
		this.lunchPeriod = lunchPeriod;
	}



	public int getOutdeg() {
		return outdeg;
	}

	public void setOutdeg(int outdeg) {
		this.outdeg = outdeg;
	}

	public int getIndeg() {
		return indeg;
	}

	public void setIndeg(int indeg) {
		this.indeg = indeg;
	}

	public Student<T, N> getNextVertex() {
		return nextVertex;
	}

	public void setNextVertex(Student<T, N> nextVertex) {
		this.nextVertex = nextVertex;
	}




	public ArrayList<Student> getFriendList() {
		return friendList;
	}

	public void setFriendList(ArrayList<Student> friendList) {
		this.friendList = friendList;
	}

	public List<Student> getFriendshipList() {
		return friendshipList;
	}

	public void setFriendshipList(List<Student> friendshipList) {
		this.friendshipList = friendshipList;
	}

	@Override
//	public String toString() {
//		return vertexInfo + "(Reputation : " + reputation + "| Diving rate : " + divingrate + "| Lunch Start Time : "
//				+ (lunchStart.toString()) + "| Lunch Period : " + lunchPeriod.toString() + ')';
//	}
	
	public String toString() {
		return vertexInfo + "(Reputation : " +reputationList+ ')';
	}
	
	

	@Override
	public int compareTo(Student o) {
		// priority: people with less time
		if (this.end_time > o.end_time) {
			return 1;
		} else if (this.end_time < o.end_time) {
			return -1;
		} else {
			return 0;
		}
	}

}
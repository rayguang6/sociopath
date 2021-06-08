package com.sociopath.model.entity;

//main project
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.sociopath.model.dto.FileInfo;
import com.sociopath.model.entity.Student;

@Node(labels = { "Student" })
public class Student <T extends Comparable<T>, N extends Comparable <N>> implements Comparable<Student>{

	@Id
	@GeneratedValue
	private Long id;

//	@OneToOne(targetEntity = Users.class)
//	@JoinColumn(name = "user_id", nullable = false)
	private Users user;
	
	//Refer To Username in Users.class
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

	private T vertexInfo;
	private int reputation;
	private int divingrate;
	private int average_lunchStart;
	private int average_lunchPeriod;
	private int end_time;
	private int[] lunchStart = new int[3];
	private int[] lunchPeriod = new int[3];
	private int outdeg;
	private int indeg;
	
//	private Edge<T, N> firstFriend;
//	private Student<T, N> nextVertex;
//	private ArrayList<T> friendList = new ArrayList<>(); // this is to show friend list only
//	private Random r = new Random();

	
	
	
	//constructor
	
	public Student() {
//	      vertexInfo=null;
//	      nextVertex = null;
//	      firstFriend = null;
		this.divingrate = (int)(Math.random() * 100);
		reputation = 10 - (divingrate / 10);// if diving rate high, reputation low
		if (reputation == 0)
			reputation = 1;
		for (int i = 0; i < lunchStart.length; i++) {
			lunchStart[i] = setTime();
		}
		for (int j = 0; j < lunchPeriod.length; j++) {
			lunchPeriod[j] = (int) (Math.random() * 55) + 5;
		}
	}
	


	public Student(T vInfo, Student<T, N> next) {
//	      vertexInfo = vInfo;
//	      nextVertex = next;
//	      firstFriend = null;
		divingrate = (int) (Math.random() * 100);
		reputation = 10 - (divingrate / 10);// if diving rate high, reputation low
		if (reputation == 0)
			reputation = 1;
		for (int i = 0; i < lunchStart.length; i++) {
			lunchStart[i] = setTime();
		}
		for (int j = 0; j < lunchPeriod.length; j++) {
			lunchPeriod[j] = (int) (Math.random() * 55) + 5;
		}
	}

	public int setTime() {
		int min = (int) (Math.random() * 181);
		int temp = 1100;
		while (min >= 60) {
			temp = temp + 100 ;
			min = min - 60;
		}
		temp = temp + min;
		return temp;
	}

	public void calculateAverage() {
		// to calculate average lunch start time
		if (average_lunchStart == 0) {
			int min = 0, hr = 0, resultmin, resulthr, temp;
			for (int i = 0; i < lunchStart.length; i++) {
				min = min + lunchStart[i] % 100;
			}
			for (int i = 0; i < lunchStart.length; i++) {
				hr = hr + lunchStart[i] / 100;
			}
			resulthr = hr / lunchStart.length;
			temp = hr % lunchStart.length;
			min = min + temp * 60;
			resultmin = min / lunchStart.length;
			while (resultmin >= 60) {
				resulthr += 1;
				resultmin -= 60;
			}
			end_time = resulthr * 100 + resultmin;
		}
		// to calculate average lunch period
		if (average_lunchPeriod == 0) {
			for (int j = 0; j < lunchPeriod.length; j++) {
				average_lunchPeriod = average_lunchPeriod + lunchPeriod[j];
			}
			average_lunchPeriod = average_lunchPeriod / (lunchPeriod.length);
		}
		// to calculate average lunch end time
		if (end_time == 0) {
			int endMinute = average_lunchStart % 100 + average_lunchPeriod;
			if (endMinute >= 60) {
				end_time = (average_lunchStart / 100 * 100) + 100 + (endMinute - 60);
			} else {
				end_time = average_lunchStart + average_lunchPeriod;
			}
		}
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
	
	
	public T getVertexInfo() {
		return vertexInfo;
	}



	public int getReputation() {
		return reputation;
	}



	public int getDivingrate() {
		return divingrate;
	}



	public int getAverage_lunchStart() {
		return average_lunchStart;
	}



	public int getAverage_lunchPeriod() {
		return average_lunchPeriod;
	}



	public int getEnd_time() {
		return end_time;
	}



	public int[] getLunchStart() {
		return lunchStart;
	}



	public int[] getLunchPeriod() {
		return lunchPeriod;
	}



	public int getOutdeg() {
		return outdeg;
	}



	public int getIndeg() {
		return indeg;
	}



		// to print array
		public String array_toString(int[] ary) {
			String str = "[ ";
			for (int i = 0; i < ary.length; i++) {
				str = str + ary[i] + " ";
			}
			return str + "]";
		}

		@Override
		public String toString() {
			return vertexInfo + "(Reputation : " + reputation + "| Diving rate : " + divingrate + "| Lunch Start Time : "
					+ array_toString(lunchStart) + "| Lunch Period : " + array_toString(lunchPeriod) + ')';
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
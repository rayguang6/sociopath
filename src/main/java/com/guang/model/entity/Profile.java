package com.guang.model.entity;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import com.guang.model.dto.FileInfo;


@Node(labels = {"profile"})
public class Profile {

	@Id
	@GeneratedValue
	private Long id;

	
	private SiteUser user;

	private String about;

	private String photoDirectory;

	private String photoName;

	private String photoExtension;
	
	private Set<Interest> interests;
	
	public Profile() {
		
	}
	
	public Profile(SiteUser user) {
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SiteUser getUser() {
		return user;
	}

	public void setUser(SiteUser user) {
		this.user = user;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	/* Create a profile that is suitable for displaying via JSP */
	public void safeCopyFrom(Profile other) {
		if (other.about != null) {
			this.about = other.about;
		}
		
		if(other.interests != null) {
			this.interests = other.interests;
		}
	}

	/* Create a profile that is suitable for saving */
	public void safeMergeFrom(Profile webProfile) {
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
		if(photoName == null) {
			return null;
		}
		
		return Paths.get(baseDirectory, photoDirectory, photoName + "." +  photoExtension);
	}

	public Set<Interest> getInterests() {
		return interests;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

	public void addInterest(Interest interest) {
		interests.add(interest);
	}

	public void removeInterest(String interestName) {
		interests.remove(new Interest(interestName));
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", user=" + user + ", about=" + about + ", photoDirectory=" + photoDirectory
				+ ", photoName=" + photoName + ", photoExtension=" + photoExtension + ", interests=" + interests + "]";
	}

	
}
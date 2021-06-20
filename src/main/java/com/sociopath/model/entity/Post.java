package com.sociopath.model.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node(labels = "Post")
public class Post {

	@Id
	@GeneratedValue
	private Long id;
	
	private String text;
	
	@Relationship(type = "POSTED", direction = Relationship.Direction.INCOMING)
	private Student owner;
	
	private String ownerName;

	
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Student getOwner() {
		return owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
	}

	

	public Post(Long id, String text, Student owner, String ownerName) {
		super();
		this.id = id;
		this.text = text;
		this.owner = owner;
		this.ownerName = ownerName;
	}

	public Post() {
		super();
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", text=" + text + ", owner=" + owner + "]";
	}
	
	
	
}

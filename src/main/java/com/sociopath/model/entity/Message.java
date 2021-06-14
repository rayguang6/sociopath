package com.sociopath.model.entity;


import java.util.Date;

import javax.persistence.PrePersist;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node(labels = { "messages" })
public class Message {
	
	@Id
	@GeneratedValue
	private Long id;

	@Relationship(type = "SENT_FROM", direction = Relationship.Direction.INCOMING)
	private Users fromUser;

	@Relationship(type = "SENT_TO", direction = Relationship.Direction.INCOMING)
	private Users toUser;

	String text;

	Boolean read;

	private Date sent;

	public Message() {

	}

	@PrePersist
	protected void onCreate() {
		if (sent == null) {
			sent = new Date();
		}
	}

	public Message(Users fromUser, Users toUser, String text) {
		super();
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.text = text;
		this.read = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getFromUser() {
		return fromUser;
	}

	public void setFromUser(Users fromUser) {
		this.fromUser = fromUser;
	}

	public Users getToUser() {
		return toUser;
	}

	public void setToUser(Users toUser) {
		this.toUser = toUser;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Date getSent() {
		return sent;
	}

	public void setSent(Date sent) {
		this.sent = sent;
	}

}
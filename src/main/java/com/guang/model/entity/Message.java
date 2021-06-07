package com.guang.model.entity;


import java.util.Date;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.TargetNode;




@Node(labels = {"messages"})
public class Message {
	@Id
	@GeneratedValue
	private Long id;

	@TargetNode
	private SiteUser fromUser;

	@TargetNode
	private SiteUser toUser;

	String text;

	Boolean read;

	private Date sent;

	public Message() {

	}


	public Message(SiteUser fromUser, SiteUser toUser, String text) {
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

	public SiteUser getFromUser() {
		return fromUser;
	}

	public void setFromUser(SiteUser fromUser) {
		this.fromUser = fromUser;
	}

	public SiteUser getToUser() {
		return toUser;
	}

	public void setToUser(SiteUser toUser) {
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
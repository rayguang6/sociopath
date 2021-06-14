package com.sociopath.model.entity;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.DateLong;
import org.springframework.data.neo4j.core.support.DateString;
import org.springframework.format.annotation.DateTimeFormat;


@Node(labels = "status_update")
public class StatusUpdate {

	@Id
	@GeneratedValue
	private Long id;

	private String text;

	@Property(name="added" )
	@CreatedDate
	@DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
	private Date added;

	public StatusUpdate() {
		
	}

	public StatusUpdate(String text) {
		this.text = text;
	}

	public StatusUpdate(String text, Date added) {
		this.text = text;
		this.added = added;
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

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((added == null) ? 0 : added.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusUpdate other = (StatusUpdate) obj;
		if (added == null) {
			if (other.added != null)
				return false;
		} else if (!added.equals(other.added))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusUpdate [id=" + id + ", text=" + text + ", added=" + added + "]";
	}
	
	

}

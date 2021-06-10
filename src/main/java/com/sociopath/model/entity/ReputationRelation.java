package com.sociopath.model.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class ReputationRelation {
	
	@Id
    @GeneratedValue
    private Long id;

	@Property(name = "point")
    private int point;

    @TargetNode
    private Student targetStudent;

	public ReputationRelation(Long id, int point, Student targetStudent) {
		super();
		this.id = id;
		this.point = point;
		this.targetStudent = targetStudent;
	}

	public ReputationRelation() {
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Student getTargetStudent() {
		return targetStudent;
	}

	public void setTargetStudent(Student targetStudent) {
		this.targetStudent = targetStudent;
	}

	@Override
	public String toString() {
		return "[ -(" + point + ")->" + targetStudent.getUsername() + "]";
	}
	
	
    
    
    
    
}

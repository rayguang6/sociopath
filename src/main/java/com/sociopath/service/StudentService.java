package com.sociopath.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sociopath.model.entity.Student;
import com.sociopath.model.entity.Users;
import com.sociopath.model.repository.StudentRepository;
import com.sociopath.model.repository.UserRepository;

@Service
public class StudentService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
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

	public void godDeleteStudent(String username) {
		studentRepository.deleteByUsername(username);
	}

	
	
	
}

package com.sociopath.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sociopath.model.dto.SpringUser;
import com.sociopath.model.entity.Users;
import com.sociopath.model.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void register(Users user) {
		user.setRole("ROLE_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userRepository.findByUsername(username);
		
		if(user == null) {
			return null;
		}
		
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
		
		String password = user.getPassword();
		
		return new SpringUser(username, password, auth);
	}

	
	public Users get(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<Users> get(Long id) {
		return userRepository.findById(id);
	}

	public String getUserName(Long chatWithUserID) {
		Optional<Users> userOptional = userRepository.findById(chatWithUserID);
		Users user = userOptional.get();
		
		return user.getUsername();
	}
	
	public Users getStudent(String username) {
		return userRepository.findByUsername(username);
	}

	public void godDeleteStudent(String username) {
		userRepository.deleteByUsername(username);
	}
}
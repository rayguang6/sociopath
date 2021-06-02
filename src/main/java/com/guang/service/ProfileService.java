package com.guang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guang.model.Profile;
import com.guang.model.ProfileDao;
import com.guang.model.SiteUser;

@Service
public class ProfileService {
	
	@Autowired
	ProfileDao profileDao;
	
	public void save(Profile profile) {
		profileDao.save(profile);
	}
	
	
	public Profile getUserProfile(SiteUser user) {
		return profileDao.findByUser(user);
	}
	
	
	
	
}

package com.ga.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ga.entity.UserProfile;

public class UserProfileServiceTest {
	
	private UserProfileServiceImpl userProfileService;
	
	private UserProfile userProfile;
	
	@Before
    public void initializeUserProfileService() {
       userProfileService = new UserProfileServiceImpl(new UserProfileDaoStub());
    }
	
	@Before
	public void initializeUserProfile() {
		userProfile = new UserProfile();
        
		userProfile.setProfileId(1L);
		userProfile.setEmail("batman@superhero.com");
        userProfile.setAddress("Gotham City");
        userProfile.setMobile("111-111-1111");
	}
	
	@Test
    public void createUserProfile_AddsProfile_Success() {
    
       UserProfile newProfile = userProfileService.createUserProfile("batman", userProfile);
    
       Assert.assertNotNull(newProfile);
       Assert.assertEquals(newProfile.getEmail(), userProfile.getEmail());
    }
	
	@Test
    public void getUserProfile_AddsProfile_Success() {
		
       UserProfile newProfile = userProfileService.getUserProfile("batman");
    
       Assert.assertNotNull(newProfile);
       Assert.assertEquals(newProfile.getEmail(), userProfile.getEmail());
    }

}

package com.ga.controller;

import org.junit.Before;
import org.junit.Test;

import com.ga.entity.UserProfile;
import com.ga.service.UserProfileServiceStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserProfileControllerTest {
	
	private UserProfileController userProfileController;
	
	private UserProfile userProfile;
	
	@Before
    public void initializeUserProfileController() {
        userProfileController = new UserProfileController();
        userProfileController.setUserProfileService(new UserProfileServiceStub());
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
    public void createUserProfile_SaveUserProfile_Success() {
        
        UserProfile newProfile = userProfileController.createUserProfile("batman", userProfile);
        
        assertNotNull(newProfile);
        assertEquals(newProfile.getEmail(), userProfile.getEmail());
    }
	
	@Test
    public void getUserProfile_UserProfile_Success() {
        
        UserProfile newProfile = userProfileController.getUserProfile("batman");
        
        assertNotNull(newProfile);
        assertEquals(newProfile.getEmail(), userProfile.getEmail());
    }

}

package com.ga.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.entity.UserRole;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest { 
	
	@InjectMocks
    private UserServiceImpl userService;
    
    @InjectMocks
    private User user;
    
    @InjectMocks
    private UserRole userRole;
    
	@Mock
    UserDao userDao;
    
    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;
    
    @Before
    public void initMocks() {
      MockitoAnnotations.initMocks(this);
    }
    
    @Before
    public void initializeDummyUser() {
    	userRole.setName("ROLE_ADMIN");
    	
        user.setUserId(1L);
        user.setUsername("batman");
        user.setPassword("robin");
        user.setUserRole(userRole);
    }
    
    @Test
    public void updateUser_User_Success() {
        when(userDao.updateUser(any(), anyLong())).thenReturn(user);

        User tempUser = userService.updateUser(user, user.getUserId());

        assertEquals(tempUser.getUsername(), user.getUsername());
    }
    
    @Test
    public void signup_ReturnsJwt_Success() {
        String expectedToken = "12345";
        
        when(userDao.signup(any())).thenReturn(user);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");
        
        String actualToken = userService.signup(user);
        
        assertEquals(actualToken, expectedToken);
    }
    
    @Test
    public void signup_UserNotFound_Error() {
    	
    	User tempUser = user;
    	tempUser.setUserId(null);

        when(userDao.signup(any())).thenReturn(tempUser);

        String token = userService.signup(user);

        assertEquals(token, null);
    }
    
    @Test
    public void login_ReturnsJwt_Success() {
        String expectedToken = "12345";
        
        when(userDao.login(any())).thenReturn(user);
        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");
        
        String actualToken = userService.login(user);
        
        assertEquals(actualToken, expectedToken);
    }
    
    @Test
    public void login_UserNotFound_Error() {
    	
    	User tempUser = user;
    	tempUser.setUserId(null);

        when(userDao.login(any())).thenReturn(null);

        String token = userService.login(user);

        assertEquals(token, null);
    }
}

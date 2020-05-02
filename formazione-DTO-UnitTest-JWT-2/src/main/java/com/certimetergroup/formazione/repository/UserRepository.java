package com.certimetergroup.formazione.repository;

import java.util.ArrayList;

import com.certimetergroup.formazione.databasefake.DatabaseFake;
import com.certimetergroup.formazione.utilities.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.certimetergroup.formazione.model.User;

@Repository
public class UserRepository {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DatabaseFake userMapper;
	
	
	
	public ArrayList<User> getUsers() {
		return userMapper.queryGetUsers();
	}

	public User getUserByUsername(String username) {	return userMapper.queryGetUserByUsername(username);		}

	public int insertNewUser(User user) {
		String passwordBCryptEncode = passwordEncoder.encode(user.getPassword());	//StringUtility.generateBCryptEncode( user.getPassword() );
		user.setPassword( passwordBCryptEncode );
		return userMapper.queryInsertNewUser(user);
	}

}

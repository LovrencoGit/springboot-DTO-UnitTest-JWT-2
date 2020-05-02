package com.certimetergroup.formazione.databasefake;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.certimetergroup.formazione.model.User;

@Component
public class DatabaseFake {

	final  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private ArrayList<User> tableUsers = new ArrayList<User>();
	
	
	@PostConstruct
	public void init() {
		User loris = new User();
		loris.setIdUser(12);
		loris.setFirstname("Loris");
		loris.setLastname("Cernich");
		loris.setBirthday( LocalDate.of(1991, Month.AUGUST, 13) );
		loris.setUsername("lovrenco");
		loris.setPassword("$2a$10$KmkeYzB02WuI4g8GyQTOOuxv23vN2QJLadH5pxl055Jq0ZN/sr3Uu");	//loris.setPassword("pa$$w0rd");

		this.tableUsers.add(loris);
	}

	
	
	public ArrayList<User> queryGetUsers() {
		logger.info("SELECT * FROM USERS");
		return this.tableUsers;
	}

	public User queryGetUserByUsername(String username) {
		logger.info("SELECT * FROM USERS WHERE username = '"+username+"'");
		return this.tableUsers.stream().filter( u -> u.getUsername().equals(username) ).findFirst().orElse(null);
	}
	
	public int queryInsertNewUser(User user) {
		logger.info("INSERT NEW USER - " + user.toString());
		DatabaseFake.simulateDatabaseAutoIncrementFor(tableUsers, user);
		boolean success = this.tableUsers.add(user);
		return success ? 1 : 0;
	}



	/*********************************************************************/
	/*********************************************************************/
	/*********************************************************************/


	private static void simulateDatabaseAutoIncrementFor(ArrayList<User> tableUsers, User user){
		Optional<User> userWithMaxId = tableUsers.stream().max( Comparator.comparing(User::getIdUser) );
		int nextIdUser = userWithMaxId.isPresent() ? userWithMaxId.get().getIdUser()+1 : 1;
		user.setIdUser(nextIdUser);
	}

}

package com.certimetergroup.formazione.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class StringUtility {

	public static boolean isEmptyString(String text) {
		return text==null || text.isEmpty();
	}

	public static String generateBCryptEncode(String text){
		BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
		return bcryptEncoder.encode(text);
	}
}

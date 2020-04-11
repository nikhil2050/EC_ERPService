package com.ec.erp.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class ErpEncryptionUtils {

	public String bCryptPassword(String password) {
		String bcyptedPassword;
		bcyptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		return bcyptedPassword;
	}

}

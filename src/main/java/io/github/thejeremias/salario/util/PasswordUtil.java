package io.github.thejeremias.salario.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	
	private PasswordUtil() {
		throw new IllegalArgumentException("Classe utilitária");
	}

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    
}

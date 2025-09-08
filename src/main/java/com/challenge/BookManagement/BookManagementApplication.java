package com.challenge.BookManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementApplication.class, args);
//	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	    String encoded = encoder.encode("user");
//	    System.out.println(encoded);
	}

}

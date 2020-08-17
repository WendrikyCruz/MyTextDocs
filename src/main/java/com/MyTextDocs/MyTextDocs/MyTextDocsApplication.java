package com.MyTextDocs.MyTextDocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MyTextDocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTextDocsApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("teste"));
	}

}

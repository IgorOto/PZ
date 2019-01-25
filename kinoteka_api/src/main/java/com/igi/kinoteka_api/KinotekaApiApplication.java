package com.igi.kinoteka_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KinotekaApiApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(KinotekaApiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void adminHash(){
		String passwd = passwordEncoder.encode("test1");
		
	}
}

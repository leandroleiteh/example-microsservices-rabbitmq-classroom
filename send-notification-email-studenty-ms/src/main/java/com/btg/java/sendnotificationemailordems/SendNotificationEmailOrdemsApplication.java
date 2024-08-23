package com.btg.java.sendnotificationemailordems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
public class SendNotificationEmailOrdemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendNotificationEmailOrdemsApplication.class, args);
	}

}

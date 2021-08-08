package com.ss501.myplayreservation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;

import com.ss501.myplayreservation.app.config.kafka.KafkaProcessor;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
//@EnableFeignClients
public class MyplayReservationApplication {
	public static ApplicationContext applicationContext;
	private static final Logger log = LoggerFactory.getLogger(MyplayReservationApplication.class);
	
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MyplayReservationApplication.class, args);
		log.info("------------------------------------------------");
		log.info("App started successfully."); 
	}
}

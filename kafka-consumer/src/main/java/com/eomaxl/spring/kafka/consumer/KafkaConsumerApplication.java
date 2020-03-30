package com.eomaxl.spring.kafka.consumer;

import org.springframework.boot.SpringApplication;
import java.util.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {
	
	
	List<String> messages  = new ArrayList<>();
	
	List<User> messagesFromUser = new ArrayList<>();
	
	// For consuming the string data
	@GetMapping("/consumeStringMessage")
	public List<String> consumeMsg()
	{
		return messages;
	}
	
	@KafkaListener(groupId="junkyard1",topics="test1",containerFactory="KafkaListenerContainerFactory")
	public List<String> getMessageFromTopic(String data)
	{
		messages.add(data);
		return messages;
	}

	
	//For consuming the json data
	@GetMapping("/consumeUserJsonMessage")
	public List<User> consumeJsonMsg()
	{
		return messagesFromUser;
	}
	
	@KafkaListener(groupId="junkyard-Json",topics="test1",containerFactory="userKafkaListenerContainerFactory")
	public List<User> getMessageFromTopicJson(User user)
	{
		messagesFromUser.add(user);
		return messagesFromUser;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}

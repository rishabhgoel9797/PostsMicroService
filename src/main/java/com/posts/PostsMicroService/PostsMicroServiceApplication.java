package com.posts.PostsMicroService;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostsMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostsMicroServiceApplication.class, args);

	}

}


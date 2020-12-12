package com.example.auditlogsmongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MangoDBConfig {

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connString = new ConnectionString(
                "mongodb://user:shyam1983@cluster0-shard-00-00.eg9gx.mongodb.net:27017,cluster0-shard-00-01.eg9gx.mongodb.net:27017,cluster0-shard-00-02.eg9gx.mongodb.net:27017/auditlogs?ssl=true&replicaSet=atlas-jfiia7-shard-0&authSource=admin&retryWrites=true&w=majority"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return  mongoClient;
    }

     @Bean
     public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "events");
    }
}

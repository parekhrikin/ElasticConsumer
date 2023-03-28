package com.example.elasticconsumer;

import com.example.elasticconsumer.services.KafkaUserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ElasticConsumerApplication {
    @Autowired
    KafkaUserService kafkaUserService;

    public static void main(String[] args) {
        SpringApplication.run(ElasticConsumerApplication.class, args);
    }

    @KafkaListener(topics = "healthplan", groupId = "healthplan-group")
    public void listen(JsonNode plan) {
        System.out.println("Received User information : " + plan.toString());
        try {
            kafkaUserService.savePlan(plan.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @GetMapping("/getElasticUserFromKafka")
//    public Iterable<JsonNode> findAllPlans() {
//        return kafkaUserService.findAllPlans();
//    }
}

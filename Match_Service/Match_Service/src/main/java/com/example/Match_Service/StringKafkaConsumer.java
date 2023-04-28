package com.example.Match_Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StringKafkaConsumer {
        @KafkaListener(topics="my-topic", groupId = "matches")
        public void listen(String match){
                System.out.println(match.toString());
        }
}

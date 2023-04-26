package com.example.Match_Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
        @KafkaListener(topics="my-topic", groupId = "matches")
        public void listen(Match match){

                System.out.println(match);
        }
}

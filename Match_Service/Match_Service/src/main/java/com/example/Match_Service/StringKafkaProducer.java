package com.example.Match_Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class StringKafkaProducer {

//    private KafkaTemplate<String, Match> kafkaTemplate;
        private KafkaTemplate<String, String> kafkaTemplate;


    //    @Autowired
    public StringKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String data){
        Message<String> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC,"my-topic")
                .build();
        kafkaTemplate.send(message );
    }


}

package org.ravi.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ravi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class KafkaService {

    @KafkaListener(topics = "topic1", groupId = "group" )
    public void receiveObject(Object obj){
        ConsumerRecord<String, Object> cr = (ConsumerRecord<String, Object>)obj;
        System.out.print("Received : ");
        System.out.println(cr.value());
    }

    @KafkaListener(topics = "topic1", groupId = "group" )
    public void receiveMessage(String message){
        System.out.println(message);
    }

}



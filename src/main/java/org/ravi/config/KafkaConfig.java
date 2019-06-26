package org.ravi.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    public Map<String, Object> getConsumerStringConfig(){
        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        map.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return map;
    }

    public Map<String, Object> getConsumerObjConfig(){
        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        map.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return map;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> getKafkaListener1(){
        ConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(getConsumerStringConfig());
        ConcurrentKafkaListenerContainerFactory<String, String> listener = new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(cf);
        return listener;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> getKafkaListener2(){
        ConsumerFactory<String, Object> cf = new DefaultKafkaConsumerFactory<>(getConsumerObjConfig());
        ConcurrentKafkaListenerContainerFactory<String, Object> listener = new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(cf);
        return listener;
    }

}

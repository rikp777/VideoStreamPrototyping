package com.emotion.aggregator.service;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.emotion.aggregator.models.Emotion;
import com.emotion.aggregator.models.ReceivedEmotionVoice;
import com.fasterxml.jackson.databind.ObjectMapper;


@Slf4j
public class AggregatorService {

   @Autowired
   Environment env;

   @KafkaListener(id = "consumer", topics = {"${kafka.topic}"} )
   public void onMessage(ConsumerRecord<?, ?> record) {

      System.out.println("The list of values is : " + record.value());

      //ReceivedEmotionVoice emotionVoice = (ReceivedEmotionVoice) record.value();

      ObjectMapper mapper = new ObjectMapper();

      try {

         // JSON string to Java object
         ReceivedEmotionVoice emotion = mapper.readValue((record.value()).toString(), ReceivedEmotionVoice.class);

         // pretty print
         String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emotion);

         System.out.println(prettyStaff1);


      } catch (IOException e) {
         e.printStackTrace();
      }

   }
}






package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.dto.Message;

@Component
public class KafkaProducer {

    private static String[] global_events = { "maintenance_begin", "maintenance_end", "plan_removed", "plan_added", "sale_begin", "sale_end" };

    @Autowired
    private StreamBridge streamBridge;

    @Scheduled(cron = "*/500 * * * * *")
    public void sendMessage(){
        for  (int i = 0; i < 1000; i++) {
            streamBridge.send("producer-out-0",new Message("bridgeEvent","message from stream bridge"));
        }
    }

    @Scheduled(cron = "*/1000 * * * * *")
    public void sendGlobalMessage(){
        String event = global_events[(int) (Math.random() * global_events.length)] + "_" + System.nanoTime();
        streamBridge.send("producer-out-1",new Message("globalEvent",event));
    }

}
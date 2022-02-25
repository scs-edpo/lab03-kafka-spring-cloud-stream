package com.example;

import com.example.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Integer.parseInt;

@RestController
public class KafkaRestController {

    @Autowired
    private StreamBridge streamBridge;


    @PostMapping(value = "/produce")
    public String produce(@RequestBody String str) {

        Integer j = parseInt(str);
        for  (int i = 0; i < j; i++) {
            Message<String> message = new Message("restEvent","message " + i + " from rest controller");
            streamBridge.send("producer-out-0",message);
        }

        return j + " messages sent";
    }

}
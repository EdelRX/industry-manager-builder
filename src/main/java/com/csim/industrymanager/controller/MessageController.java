package com.csim.industrymanager.controller;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csim.industrymanager.model.IndustryModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    /*@GetMapping("message/{message}")
    public ResponseEntity<String> publish(@PathVariable("message") final String message){
        jmsTemplate.convertAndSend(queue, message);
        return new ResponseEntity(message, HttpStatus.OK);
    }*/
    
    @PostMapping("message/send")
    public ResponseEntity<String> publish(@RequestBody final IndustryModel input) throws JmsException, JsonProcessingException{
    	ObjectMapper mapper = new ObjectMapper();
        jmsTemplate.convertAndSend(queue, mapper.writeValueAsString(input));
        return new ResponseEntity(input, HttpStatus.OK);
    }

}

package org.bk.producer.controller;


import org.bk.producer.domain.Message;
import org.bk.producer.domain.MessageAcknowledgement;
import org.bk.producer.service.MessageHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
public class MessageController {

    private final MessageHandlerService messageHandlerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    public MessageController(MessageHandlerService messageHandlerService) {
        this.messageHandlerService = messageHandlerService;
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public Resource<MessageAcknowledgement> pongMessage(@RequestBody Message input, @RequestHeader("payload.trace") boolean tracePayload) {
        if (tracePayload) {
            LOGGER.error("Received Payload: {}", input.getPayload());
        }
        return new Resource<>(this.messageHandlerService.handleMessage(input).block(Duration.ofMillis(20000L)));
    }

}

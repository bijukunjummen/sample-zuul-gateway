package org.bk.producer.service;


import org.bk.producer.domain.Message;
import org.bk.producer.domain.MessageAcknowledgement;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

//import static org.assertj.core.api.Assertions.*;

public class MessageHandlerServiceTest {

    @Test
    public void testMessageHandlerServiceNoDelay() {
        MessageHandlerService messageHandlerService = new MessageHandlerServiceImpl("test");
        Message msg = new Message("id", "payload", false, 0);
        Mono<MessageAcknowledgement> ack = messageHandlerService.handleMessage(msg);

        StepVerifier
                .create(ack)
                .expectNext(new MessageAcknowledgement("id", "payload", "test"))
                .expectComplete();
                

//        TestSubscriber.subscribe(ack).await().assertValues();
    }

    @Test
    public void testMessageHandlerServiceWithDelay() {
        MessageHandlerService messageHandlerService = new MessageHandlerServiceImpl("test");
        Message msg = new Message("id", "payload", false, 100);
        Mono<MessageAcknowledgement> ack = messageHandlerService.handleMessage(msg);
        StepVerifier
                .create(ack)
                .expectNext(new MessageAcknowledgement("id", "payload", "test"))
                .expectComplete();
//        TestSubscriber.subscribe(ack).await().assertValues(new MessageAcknowledgement("id", "payload", "test"));
    }

    @Test
    public void testMessageHandlerServiceWithException() {
        MessageHandlerService messageHandlerService = new MessageHandlerServiceImpl("test");
        Message msg = new Message("id", "payload", true, 100);
        Mono<MessageAcknowledgement> ack = messageHandlerService.handleMessage(msg);
        StepVerifier
                .create(ack)
                .expectError();
//        TestSubscriber.subscribe(ack).await().assertError();
    }
}

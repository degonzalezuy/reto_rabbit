package com.sofka.broker.usecase;

import com.sofka.broker.model.Message;
import com.sofka.broker.model.gateway.MessageGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class MessageUseCase {
    private final MessageGateway messageGateway;

    public Mono<Message>sendDirect(Message message){
            if (message.getFloor()%2!=0){
            return messageGateway.sendDirect(message);
            }else {
             return null;
            }
    }

    public Mono<Message>sendFanout(Message message){
        return messageGateway.sendFanout(message);
    }
}

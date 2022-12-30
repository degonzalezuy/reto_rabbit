package com.sofka.broker.model.gateway;

import com.sofka.broker.model.Message;
import reactor.core.publisher.Mono;

public interface MessageGateway {
    Mono<Message> sendDirect(Message message);
    Mono<Message> sendFanout(Message message);
}

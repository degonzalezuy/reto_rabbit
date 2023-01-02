package com.sofka.broker;

import com.sofka.broker.model.Message;
import com.sofka.broker.model.gateway.MessageGateway;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@EnableDirectAsyncGateway
@RequiredArgsConstructor
public class MessageGatewayAsyncAdapter implements MessageGateway {
    private final DirectAsyncGateway asyncGateway;

    @Override
    public Mono<Message> sendDirect(Message message) {
        String uuid = UUID.randomUUID().toString();
        final Command<Message> command = new Command<>("message." + message.getFloor(), uuid, message);
        return asyncGateway
                .sendCommand(command, "message.pisos")
                .thenReturn(message);
    }

    @Override
    public Mono<Message> sendFanout(Message message){
        String uuid = UUID.randomUUID().toString();
        final Command<Message> command = new Command<>("message.all", uuid, message);
        return asyncGateway
                .sendCommand(command, "message.pisos")
                .thenReturn(message);
    }
}

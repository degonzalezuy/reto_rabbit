package com.sofka.broker;

import com.sofka.broker.model.Message;
import com.sofka.broker.usecase.MessageUseCase;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageService {
    private final MessageUseCase messageUseCase;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Message> all(@RequestBody Message msg) {
        return messageUseCase.sendFanout(msg);
    }

    @PostMapping(path = "/piso", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Message> sendOdd(@RequestBody Message msg) {
        return messageUseCase.sendDirect(msg);
    }
}
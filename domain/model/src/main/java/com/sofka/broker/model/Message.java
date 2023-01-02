package com.sofka.broker.model;

import lombok.Data;

@Data
public class Message {
    private String user;
    private String subject;
    private String message;
    private int floor;
}

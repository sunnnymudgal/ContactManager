package com.cm.helpers;

import lombok.Builder;

import java.awt.*;

@Builder
public class Message {
    private String content;
    @Builder.Default
    private MessageType type = MessageType.blue;
}

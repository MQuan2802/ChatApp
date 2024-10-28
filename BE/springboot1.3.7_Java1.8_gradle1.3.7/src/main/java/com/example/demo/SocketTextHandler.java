package com.example.demo;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class SocketTextHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

        String payload = message.getPayload();
        System.out.print(payload);
//        JSONObject jsonObject = new JSONObject(payload);
        session.sendMessage(new TextMessage("Hi how may we help you?"));
    }



}

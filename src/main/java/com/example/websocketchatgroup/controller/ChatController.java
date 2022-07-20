package com.example.websocketchatgroup.controller;

import com.example.websocketchatgroup.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage") // Tưởng tượng giống requestMapping, value là giá trị của endpoint mà chúng ta đã cấu hình trong class WebSocketConfiguration
    @SendTo("/topic/public") // Giá trị được khai báo trong @SendTo phải bắt đầu giống với giá trị chúng ta đã khai báo trong phương thức enableSimpleBroker()
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Thêm người dùng vào socket
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}

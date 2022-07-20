package com.example.websocketchatgroup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    // Cấu hình Message Broker
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Prefix của các endpoint mà các client có thể subscribe và nhận message từ server
        registry.setApplicationDestinationPrefixes("/msg"); // Định nghĩa prefix cho các điểm đến mà client sẽ gửi message tới WebSocket server
    }

    // Định nghĩa những endpoint mà client sẽ sử dụng để gọi và kết nối tới WebSocket
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS(); // withSockJS() dùng để hỗ trợ WebSocket connection
    }
}

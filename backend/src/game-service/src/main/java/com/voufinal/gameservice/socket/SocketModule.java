package com.voufinal.gameservice.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.voufinal.gameservice.model.Message;
import com.voufinal.gameservice.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@Slf4j
public class SocketModule {
    private final SocketIOServer server;
    private  SocketService socketService;
    private final MessageService serviceService;

    public SocketModule(SocketIOServer server, SocketService socketService, MessageService serviceService) {
        this.server = server;
//        this.socketService = socketService;
        this.serviceService = serviceService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Message.class, onChatReceived());
    }

    private DataListener<Message> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            try {
                String message = data.toString();
                JSONObject jsonObject = new JSONObject(message);

                System.out.println("Data jsonObject: " + jsonObject.toString());

                // Correct way to compare strings
                if (jsonObject.getString("messageType").equals("ANSWER_QUIZ")) {
                    serviceService.calculateScore(jsonObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }


    // reference: https://dev.to/amalja0/how-to-integrating-socketio-with-spring-boot-and-react-spring-boot-implementation-1ma4
    private ConnectListener onConnected() {
        return (client) -> {
            var params = client.getHandshakeData().getUrlParams();
            String room = params.get("room").stream().collect(Collectors.joining());
            String username = params.get("username").stream().collect(Collectors.joining());
            client.joinRoom(room);
            log.info("Socket ID [{}] - room [{}] - username [{}] Connected to chat module", client.getSessionId(), room, username);
            serviceService.saveMessage(room, username, "0");
            serviceService.sendUserListUpdate(room);
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            String room = getHandshakeParam(client, "room");
            String username = getHandshakeParam(client, "username");
            serviceService.removeUserFromRoom(room,username);
            log.info("Socket ID [{}] - room [{}] - username [{}] disconnected from chat module", client.getSessionId(), room, username);
            serviceService.sendUserListUpdate(room);
        };
    }

    private String getHandshakeParam(SocketIOClient client, String param) {
        return client.getHandshakeData().getUrlParams().get(param).stream().collect(Collectors.joining());
    }
}
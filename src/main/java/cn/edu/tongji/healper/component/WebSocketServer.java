package cn.edu.tongji.healper.component;

import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.repository.ClientRepository;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{token}")
public class WebSocketServer {

    private ClientEntity clientEntity;

    private static ConcurrentHashMap<String, WebSocketServer> users = new ConcurrentHashMap<>();

    private Session session = null;

    private static ClientRepository clientRepository;

    // 注入数据库服务
    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        WebSocketServer.clientRepository = clientRepository;
    }
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        // 建立连接
        this.session = session;
        System.out.println("connected");
        clientEntity = WebSocketServer.clientRepository.findClientEntityByUserphone(token);
        System.out.println(clientEntity.getNickname());
        users.put(token, this);
        sendMessage("测试一下");
    }

    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("disconnected");
        if (this.clientEntity != null) {
            users.remove(this.clientEntity.getUserphone());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
        System.out.println(message);
        if (message.equals("online")) {
            sendMessage(searchOnline().toString());
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, String> searchOnline() {
        Map<String, String> msg = new HashMap<>();
        users.forEach((key, value) -> {
            msg.put(key, key);
        });
        return msg;
    }
}
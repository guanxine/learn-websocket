package cn.gx.websocket.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import javax.json.Json;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * websocket 的服务端
 *
 * 2. Implement the WebSocket endpoint
 */
@ServerEndpoint("/cinemaSocket/{client-id}")
public class SocketServer {

    public SocketServer(){

        System.out.println("socket server init");
    }
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet());

    @OnMessage
    public String onMessage(String message, Session session, @PathParam("client-id") String clientId) {
        try {
            Object parse = JSON.parse(message);//5

            System.out.println("received message from client " + clientId);// 10
            System.out.println(peers.size());
            for (Session s : peers) {
                try {
                    // 推送给用户 html js 显示
                    s.getBasicRemote().sendText(message);//6
                    System.out.println("send message to peer ");// push client
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "message was received by socket mediator and processed: " + message;// push client
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("client-id") String clientId) {
        System.out.println("mediator: opened websocket channel for client " + clientId);
        peers.add(session);//2

        try {
            session.getBasicRemote().sendText("good to be in touch");//3 push client
        } catch (IOException e) {
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("client-id") String clientId) {
        System.out.println("mediator: closed websocket channel for client " + clientId);
        peers.remove(session);
    }
}
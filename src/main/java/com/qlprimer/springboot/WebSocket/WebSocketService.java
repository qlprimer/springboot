package com.qlprimer.springboot.WebSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/ws/{token}")
@Component
public class WebSocketService {
    private static Logger logger= LoggerFactory.getLogger(WebSocketService.class);
    private static int onlineCount=0;
    private static CopyOnWriteArraySet<WebSocketService> webSocketSet=new CopyOnWriteArraySet<>();
    private Session session;
    private String token;

    @OnOpen
    public void onOpen(Session session,@PathParam("token") String token){


        this.token=token;
        logger.info("Token====>"+token);
        this.session=session;
        webSocketSet.add(this);
        addOnlineCount();
        logger.info("新接入客户端："+token+",当前客户端人数："+getOnlineCount());

        try {
            sendMessage("成功接入！");
        }catch (IOException e){

        }


    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        logger.info("有一个连线关闭！当前在线人数："+getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session){
        logger.info("接收来自："+token+"的信息："+message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    public static void sendInfo(String message, String token) throws IOException {
        logger.info("推送消息到窗口"+token+"，推送内容:"+message);
        for (WebSocketService item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(token==null) {
                    item.sendMessage(message);
                }else if(item.token.equals(token)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }





    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }


}

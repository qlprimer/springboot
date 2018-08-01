package com.qlprimer.springboot.Controller;

import com.qlprimer.springboot.WebSocket.WebSocketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/send")
public class WebSocketSendMessage {

    @RequestMapping("/sendmessage")
    @ResponseBody
    private String SendMessage(HttpServletRequest request, HttpSession session) throws IOException {
        String message=request.getParameter("message");
        String token=request.getParameter("token");
        WebSocketService.sendInfo(message,token);
        return "OK";
    }
}

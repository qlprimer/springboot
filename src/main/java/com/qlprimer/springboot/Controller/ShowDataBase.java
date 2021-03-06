package com.qlprimer.springboot.Controller;

import com.alibaba.fastjson.JSON;
import com.qlprimer.springboot.Dao.UserMapper;
import com.qlprimer.springboot.Entity.User;
import com.qlprimer.springboot.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ShowDataBase {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    UserService userService;

    @RequestMapping(value = "/show")
    @ResponseBody
    public List<User> show(){
        return userService.selectALL();
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("user","qinlei");
        model.addAttribute("count",100);
        return "index";
    }
    @RequestMapping("/getdata")
    public String getdata(Model model, HttpServletRequest request){
        String username=request.getParameter("username");
        logger.info("UserName=>"+username);
        return "index";
    }
}

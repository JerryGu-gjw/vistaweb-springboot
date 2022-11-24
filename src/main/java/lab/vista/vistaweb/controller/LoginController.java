package lab.vista.vistaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;
import com.fasterxml.jackson.*;

import io.swagger.annotations.Api;
import lab.vista.vistaweb.entity.User;
import lab.vista.vistaweb.mapper.UserMapper;

@RestController
@CrossOrigin
@Api(value = "登陆类控制器",tags="登陆类控制器")
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/api/user/register")
    public ObjectNode addUser(@RequestParam("username")String uAccount, 
    @RequestParam("password")String uPassword, 
    @RequestParam("email")String uEmail){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        if(userMapper.findByAccount(uAccount) != null){
            objectNode.put("code", 301)
            .put("description", "account existed");
            return objectNode;
        }
        int i = userMapper.insert(uAccount, uPassword, uEmail);
        if(i > 0){
            objectNode.put("code", 200)
            .put("description", "account added");
            return objectNode;
        }
        objectNode.put("code", 000)
            .put("description", "Unknow error");
            return objectNode;
    }

    @PutMapping("/api/user/update")
    public String updateUser(User user){
        return "update user";
    }

    @GetMapping("/api/user/userall")
    public List<User> query(){
       List<User> userList = userMapper.findAll();
       return userList;
    }

    @GetMapping("/api/user/userid")
        public User query(@RequestParam("u_id")int u_id){
        User userList = userMapper.findById(u_id);
        System.out.println(userList);
        return userList;
    }

    @PostMapping("/api/user/login")
    public ObjectNode login(@RequestParam("username")String u_account, @RequestParam("password")String u_password){
        //System.out.println(userMapper.login(u_account, u_password));
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        
        if(userMapper.login(u_account, u_password) != null){
            System.out.println("login success");
            objectNode.put("code", 200)
            .put("description", "login success");
            //objectNode.put("number", 42);
            return objectNode;
        }
        objectNode.put("code", 404)
            .put("description", "not found");
        return objectNode;
    }
        
}

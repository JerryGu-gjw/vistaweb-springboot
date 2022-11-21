package lab.vista.vistaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lab.vista.vistaweb.entity.User;
import lab.vista.vistaweb.mapper.UserMapper;

@RestController
@CrossOrigin
@Api(value = "登陆类控制器",tags="登陆类控制器")
public class LoginController {

    @Autowired
    private UserMapper userMapper;
    @PostMapping("/user")
    public String addUser(User user){
        if(!userMapper.findByAccount(user.uAccount).isEmpty()){
            return "300";
        }
        int i = userMapper.insert(user);
        if(i > 0){
            return "add user";
        }
        return "failed add user";
    }

    @PutMapping("/user")
    public String updateUser(User user){
        return "update user";
    }

    @GetMapping("/userall")
    public List<User> query(){
       List<User> userList = userMapper.findAll();
       System.out.println(userList);
       return userList;
    }

    @GetMapping("/userid")
        public List<User> query(@RequestParam("u_id")int u_id){
        List<User> userList = userMapper.findById(u_id);
        System.out.println(userList);
        return userList;
        }
        
}

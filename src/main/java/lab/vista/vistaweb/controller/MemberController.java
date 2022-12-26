package lab.vista.vistaweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.Api;
import lab.vista.vistaweb.entity.Member;
import lab.vista.vistaweb.mapper.MemberMapper;

@RestController
@CrossOrigin
@Api(value = "成员类控制器",tags="成员类控制器")
public class MemberController {

    @Autowired
    private MemberMapper MemberMapper;
    
    @PostMapping("/api/member/add")
    public ObjectNode add(
        @RequestParam("name") String name,
        @RequestParam("colleage") String colleage,
        @RequestParam("specialty") String specialty,
        @RequestParam("phonenum") String phonenum,
        @RequestParam("email") String email,
        HttpServletRequest request) throws IOException{
        
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        MemberMapper.insert(name, colleage, specialty, phonenum, email);

        objectNode.put("code", 200)
            .put("description", "Added!");
            return objectNode;
    }

    @PostMapping("/api/member/all")
    public List<Member> query(){
        return MemberMapper.findAll();
    }
}
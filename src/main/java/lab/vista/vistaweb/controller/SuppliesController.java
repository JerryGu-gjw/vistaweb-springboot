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
import lab.vista.vistaweb.entity.Supplies;
import lab.vista.vistaweb.mapper.SuppliesMapper;

@RestController
@CrossOrigin
@Api(value = "物资类控制器",tags="物资类控制器")
public class SuppliesController {

    @Autowired
    private SuppliesMapper SuppliesMapper;
    
    @PostMapping("/api/supplies/add")
    public ObjectNode add(
        @RequestParam("name") String name,
        @RequestParam("type") String type,
        @RequestParam("storagearea") String storagearea,
        @RequestParam("totalnum") String totalnum,
        @RequestParam("remark") String remark,
        @RequestParam("category") String category,
        HttpServletRequest request) throws IOException{
        
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        SuppliesMapper.insert(name, type, storagearea, totalnum, totalnum, remark, category);

        objectNode.put("code", 200)
            .put("description", "Added!");
            return objectNode;
    }


    @PostMapping("/api/supplies/all")
    public List<Supplies> query(){
        return SuppliesMapper.findAll();
    }
}
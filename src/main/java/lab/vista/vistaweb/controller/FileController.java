package lab.vista.vistaweb.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.Api;
import lab.vista.vistaweb.entity.ExcelFile;
import lab.vista.vistaweb.mapper.ExcelFileMapper;

@RestController
@CrossOrigin
@Api(value = "文件类控制器",tags="文件类控制器")
public class FileController {

    @Autowired
    private ExcelFileMapper ExcelFileMapper;
    
    @PostMapping("/api/upload")
    public ObjectNode upload(
        @RequestParam("file") MultipartFile blob, 
        @RequestParam("fileName") String fileName,
        @RequestParam("username") String username,
        HttpServletRequest request) throws IOException{
        //String path = request.getServletContext().getRealPath("/upload/");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        String basePath = "/home/vista/web/tomcat/webapps/files/";
        File upDir = new File(basePath + username + "/");
        if(!upDir.exists()){
            upDir.mkdir();
        }
        File eFile = new File(basePath + username + "/" + fileName);
        if(eFile.exists()){
            blob.transferTo(eFile);
            objectNode.put("code", 200)
            .put("description", "OverWritted!");
            return objectNode;
        }else{
            blob.transferTo(eFile);
            if(ExcelFileMapper.insert(fileName, username, basePath + username + "/" + fileName) > 0){
                objectNode.put("code", 200)
                .put("description", "file saved");
                return objectNode;
            }
            objectNode.put("code", 303)
                .put("description", "failed");
                return objectNode;
        }
    }

    @PostMapping("/api/download")
    @ResponseBody
    public void iniWorkBook(String filePath,HttpServletRequest request,HttpServletResponse response) {
        try {
            File file = new File(filePath);
            InputStream inputStream = new FileInputStream(file);
            ServletOutputStream out=response.getOutputStream();
            byte buff[]=new byte[1024];
            int length=0;
            while((length=inputStream.read(buff))>0) {
                out.write(buff,0,length);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch(IOException e){
            e.printStackTrace();
        } finally {

        }
    }

    @PostMapping("/api/files/all")
    public List<ExcelFile> query(){
        return ExcelFileMapper.findAll();
    }
}
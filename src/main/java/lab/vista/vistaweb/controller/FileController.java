package lab.vista.vistaweb.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

@RestController
@Api(value = "文件类控制器",tags="文件类控制器")
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile eFile, HttpServletRequest request) throws IOException{
        System.out.println("File Size:" + eFile.getSize());
        //String path = request.getServletContext().getRealPath("/upload/");
        saveFile(eFile, "/tmp/tomcatfile/upload/");
        return "Upload successfully";
    }

    public void saveFile(MultipartFile eFile, String path) throws IOException{
        File upDir = new File(path);
        if(!upDir.exists()){
            upDir.mkdir();
        }

        File file = new File(path+eFile.getOriginalFilename());
        System.out.print(path);
        eFile.transferTo(file);

    }
}

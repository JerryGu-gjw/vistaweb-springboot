package lab.vista.vistaweb.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.log.SystemLogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.Api;
import lab.vista.vistaweb.entity.Invoice;
import lab.vista.vistaweb.mapper.ExcelFileMapper;
import lab.vista.vistaweb.mapper.InvoiceMapper;

@RestController
@CrossOrigin
@Api(value = "发票类控制器",tags="发票类控制器")

public class invoiceController {

    @Autowired
    private InvoiceMapper InvoiceMapper;
    //public int insert(String invoiceName, String applicant, String amount, String path, String remark, String category);
    @PostMapping("/api/invoice/upload")
    public ObjectNode upload(
        @RequestParam("file") MultipartFile blob, 
        @RequestParam("invoicename") String invoicename,
        @RequestParam("applicant") String applicant,
        @RequestParam("amount") String amount,
        @RequestParam("remark") String remark,
        @RequestParam("category") String category,
        HttpServletRequest request) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        String basePath = "/home/vista/web/tomcat/webapps/invoicefile/";
        File upDir = new File(basePath + category + "/");
        if(!upDir.exists()){
            upDir.mkdir();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStamp = simpleDateFormat.format(new Date());
        String filename = timeStamp + "-" + invoicename + ".pdf";
        File eFile = new File(basePath + category + "/" + filename);

        String path = basePath + category + "/" + filename;
        if(eFile.exists()){
            blob.transferTo(eFile);
            objectNode.put("code", 200)
            .put("description", "OverWritted!");
            return objectNode;
        }else{
            blob.transferTo(eFile);
            if(InvoiceMapper.insert(invoicename, applicant, amount, path, remark, category) > 0){
                objectNode.put("code", 200)
                .put("description", "file saved");
                return objectNode;
            }
            objectNode.put("code", 303)
                .put("description", "failed");
                return objectNode;
        }
    }

    @PostMapping("/api/invoice/download")
    @ResponseBody
    public void downloadFile(@RequestParam("id") String id,HttpServletRequest request,HttpServletResponse response) {
        Invoice iv = InvoiceMapper.getPathById(id);
        try {
            File file = new File(iv.getPath());
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

    @PostMapping("/api/invoice/delete")
    @ResponseBody
    public ObjectNode delete(@RequestParam("id") String id,HttpServletRequest request,HttpServletResponse response) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        Invoice iv = InvoiceMapper.getPathById(id);
        try {
            Files.delete(Paths.get(iv.getPath()));
            InvoiceMapper.deleteById(id);
            objectNode.put("code", 200)
            .put("description", "file deleted");
            return objectNode;
            
        } catch(IOException e){
            objectNode.put("code", 300)
            .put("description", "failed delete");
            e.printStackTrace();
            return objectNode;
        } finally {

        }
    }

    @PostMapping("/api/invoice/all")
    public List<Invoice> query(){
        return InvoiceMapper.findAll();
    }
}

package lab.vista.vistaweb.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloSpringBoot {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){return "hello";}
}

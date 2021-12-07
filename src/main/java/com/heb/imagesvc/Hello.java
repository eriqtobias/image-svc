package com.heb.imagesvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {

    @RequestMapping("/hello")
    public @ResponseBody String hello(){
        return "Hello World";
    }
}

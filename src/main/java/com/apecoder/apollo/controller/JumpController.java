package com.apecoder.apollo.controller;

import com.apecoder.apollo.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JumpController {

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/hellohi")
    public String say(){
        return "blog.html";
    }
}

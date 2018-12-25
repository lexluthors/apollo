package com.apecoder.apollo.controller;

import com.apecoder.apollo.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JumpController {

    @Autowired
    private GirlProperties girlProperties;

    @GetMapping(value = "/")
    public String say(){
        return "blog.html";
    }
}

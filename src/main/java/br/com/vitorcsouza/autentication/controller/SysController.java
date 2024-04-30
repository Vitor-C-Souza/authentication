package br.com.vitorcsouza.autentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class SysController {

    @GetMapping
    public String content(){
        return "You are viewing content";
    }
}

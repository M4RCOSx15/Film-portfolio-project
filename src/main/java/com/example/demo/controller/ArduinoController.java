package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arduino")
@CrossOrigin("*")
public class ArduinoController {
    static String comandoAtual;
    @GetMapping("/status")

    public String getStatus() {
        String resposta = comandoAtual;
        comandoAtual = "";
        return resposta;
         }

    public static void setComando(String cmd){
       comandoAtual = cmd;
        System.out.println("O botão apertado no arduino é : "+ cmd);
    }
}

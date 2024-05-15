package com.gerenciadortarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteApi {

    @GetMapping("/teste-api")
    private String teste() {
        return "Sucesso";
    }
    @GetMapping("/teste-api/bem-vindo")
    public String BemVindo(@RequestParam(name = "nome") String nome) {
        return "Bem Vindo "+nome;
    }

}

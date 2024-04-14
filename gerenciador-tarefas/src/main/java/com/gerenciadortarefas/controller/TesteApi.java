package com.gerenciadortarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteApi {

    @GetMapping("/teste-api")
    private String teste() {
        return "Sucesso";
    }

}

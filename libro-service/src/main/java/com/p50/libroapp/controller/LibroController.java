package com.p50.libroapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/libros")
public class LibroController {

    @GetMapping(produces = "application/json")
    @ResponseBody
    public String getAllLibros() {
        return "{Probando Conectividad Rest aaaa}";
    }

}

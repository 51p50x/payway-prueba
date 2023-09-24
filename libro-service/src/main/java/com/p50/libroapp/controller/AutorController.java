package com.p50.libroapp.controller;

import com.p50.libroapp.model.Autor;
import com.p50.libroapp.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping(value="/autores", produces="application/json")
    public ResponseEntity<List<Autor>> getAllAutores() {
        List<Autor> autores = autorService.findAll();
        return ResponseEntity.ok(autores);
    }
}

package com.p50.libroapp.controller;

import com.p50.libroapp.model.Categoria;
import com.p50.libroapp.service.CategoriaService;
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
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value="/categorias", produces="application/json")
    public ResponseEntity<List<Categoria>> getAllCategoria() {
        List<Categoria> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }
}

package com.p50.libroapp.service;

import com.p50.libroapp.model.Autor;
import com.p50.libroapp.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }
}

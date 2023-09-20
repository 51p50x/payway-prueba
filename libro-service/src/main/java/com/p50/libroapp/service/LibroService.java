package com.p50.libroapp.service;

import com.p50.libroapp.exception.ResourceNotFoundException;
import com.p50.libroapp.model.Categoria;
import com.p50.libroapp.model.Libro;
import com.p50.libroapp.model.LibroCategoria;
import com.p50.libroapp.repository.CategoriaRepository;
import com.p50.libroapp.repository.LibroCategoriaRepository;
import com.p50.libroapp.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LibroCategoriaRepository libroCategoriaRepository;

    public Optional<Libro> findById(Long id) {
        return libroRepository.findById(id);
    }

    public List<Libro> findAll(int page, int size) {
        int offset = page * size;
        List<Libro> libros = libroRepository.findAllWithPagination(size, offset);
        for (Libro libro : libros) {
            setLibroCategorias(libro);
        }
        return libros;
    }

    private void setLibroCategorias(Libro libro) {
        List<LibroCategoria> libroCategorias = libroCategoriaRepository.findByLibro(libro);
        List<Categoria> categorias = libroCategorias.stream()
                .map(LibroCategoria::getCategoria)
                .collect(Collectors.toList());
        libro.setCategorias(categorias);
    }


    @Transactional
    public Libro create(Libro libro, List<Long> categoriaIds) {
        libro = libroRepository.save(libro);
        setLibroCategorias(libro, categoriaIds);
        return libro;
    }

    @Transactional
    public Libro update(Long id, Libro libroDetails, List<Long> categoriaIds) {
        Libro libro = libroRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Libro no encontrado con ID: " + id));
        libro.setTitulo(libroDetails.getTitulo());
        libro.setAutor(libroDetails.getAutor());
        libro.setPrecio(libroDetails.getPrecio());
        libro.setEstado(libroDetails.getEstado());
        libro = libroRepository.save(libro);
        setLibroCategorias(libro, categoriaIds);
        return libro;
    }

    @Transactional
    public void delete(Long id) {
        Libro libro = libroRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Libro no encontrado con ID: " + id));
        libroCategoriaRepository.deleteByLibro(libro);
        libroRepository.delete(libro);
    }

    private void setLibroCategorias(Libro libro, List<Long> categoriaIds) {
        List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);
        libroCategoriaRepository.deleteByLibro(libro);
        for (Categoria categoria : categorias) {
            LibroCategoria libroCategoria = new LibroCategoria();
            libroCategoria.setLibro(libro);
            libroCategoria.setCategoria(categoria);
            libroCategoriaRepository.save(libroCategoria);
        }
    }
}


package com.p50.libroapp.service;

import com.p50.libroapp.dto.LibroDto;
import com.p50.libroapp.exception.ResourceNotFoundException;
import com.p50.libroapp.model.Autor;
import com.p50.libroapp.model.Categoria;
import com.p50.libroapp.model.Libro;
import com.p50.libroapp.model.LibroCategoria;
import com.p50.libroapp.repository.AutorRepository;
import com.p50.libroapp.repository.CategoriaRepository;
import com.p50.libroapp.repository.LibroCategoriaRepository;
import com.p50.libroapp.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AutorRepository autorRepository;

    public Optional<Libro> findById(Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        optionalLibro.ifPresent(this::setLibroCategorias);
        return optionalLibro;
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
    public Libro create(LibroDto libroDto) {
        Libro libro = new Libro();

        libro.setTitulo(libroDto.getTitulo());
        libro.setPrecio(libroDto.getPrecio());
        libro.setEstado(libroDto.getEstado());

        Autor autor = autorRepository.findById(libroDto.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con ID: " + libroDto.getAutorId()));
        libro.setAutor(autor);

        libro = libroRepository.save(libro);
        setLibroCategorias(libro, libroDto.getCategoriaIds());

        return libro;
    }

    @Transactional
    public Libro update(Long id, LibroDto libroDto) {
        Libro libro = libroRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Libro no encontrado con ID: " + id));

        libro.setTitulo(libroDto.getTitulo());
        libro.setPrecio(libroDto.getPrecio());
        libro.setEstado(libroDto.getEstado());

        Autor autor = autorRepository.findById(libroDto.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con ID: " + libroDto.getAutorId()));
        libro.setAutor(autor);

        libro = libroRepository.save(libro);
        setLibroCategorias(libro, libroDto.getCategoriaIds());

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


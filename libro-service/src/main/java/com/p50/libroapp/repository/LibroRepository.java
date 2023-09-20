package com.p50.libroapp.repository;

import com.p50.libroapp.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query(value = "SELECT * FROM Libro ORDER BY id ASC LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Libro> findAllWithPagination(int limit, int offset);

}

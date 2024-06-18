package com.challengerLiteratura.eduriu.repositorio;

import com.challengerLiteratura.eduriu.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRespositorio extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloIgnoreCase(String nombre);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> libroPorIdioma(@Param("idioma") String idioma);

}

package com.challengerLiteratura.eduriu.repositorio;

import com.challengerLiteratura.eduriu.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaInicio > :fecha")
    List<Autor> autoresVivos(@Param("fecha") Integer fecha);


}

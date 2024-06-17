package com.challengerLiteratura.eduriu.repositorio;

import com.challengerLiteratura.eduriu.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor,Long> {
}

package dto;

import modelos.Autor;

import java.util.List;

public record LibroDTO(

        String titulo,
        List<Autor> autor,
        List<String> idioma,
        Integer numeroDescargas

) {
}

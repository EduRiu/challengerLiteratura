package com.challengerLiteratura.eduriu.servicios;

import com.challengerLiteratura.eduriu.modelos.*;
import com.challengerLiteratura.eduriu.repositorio.LibroRespositorio;
import com.challengerLiteratura.eduriu.conexion.ConexionAPI;
import com.challengerLiteratura.eduriu.conexion.ConversionDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challengerLiteratura.eduriu.repositorio.AutorRepositorio;

import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    LibroRespositorio libroRespositorio;
    @Autowired
    AutorRepositorio autorRepositorio;

    private ConexionAPI conexionAPI = new ConexionAPI();
    private ConversionDatos conversionDatos = new ConversionDatos();
    private static final String url = "https://gutendex.com/books/";


    public void busquedaLibro(String libro) {

        var json = conexionAPI.consumoAPI(url + "?search=" + libro.replace(" ", "%20"));

        System.out.println(url + "?search=" + libro.replace(" ", "%20"));
        System.out.println("JSON" + json);

        var libroBuscado = conversionDatos.consumoAPI(json, LibroDatosGenerales.class);

        /*System.out.println(libroBuscado);*/

        Optional<LibroDatos> libroEncontrado = libroBuscado.libroResultado().stream()
                .filter(l -> l.titulo().toLowerCase().contains(libro.toLowerCase()))
                .findFirst();

               System.out.println(libroEncontrado);

        if (libroEncontrado.isPresent()) {
            LibroDatos serial = libroEncontrado.get();

            Autor autor = obtenerAutor(serial);
            System.out.println(autor);

            Libro libroParaCargar = new Libro(serial,autor );
            System.out.println(libroParaCargar);

            libroRespositorio.save(libroParaCargar);
            autorRepositorio.save(autor);

            System.out.println("libro cargado");
        } else {
            System.out.println("libro no encontrado");
        }

    }
    public Autor obtenerAutor(LibroDatos libroDatos){
        AutorDatos autorDatos = libroDatos.autores().get(0);
        return new Autor(autorDatos);
    }


}

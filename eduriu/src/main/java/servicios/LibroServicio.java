package servicios;

import conexion.ConexionAPI;
import conexion.ConversionDatos;
import modelos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorio.AutorRepositorio;
import repositorio.LibroRespositorio;

import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRespositorio libroRespositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;

    private ConexionAPI conexionAPI = new ConexionAPI();
    private ConversionDatos conversionDatos = new ConversionDatos();
    private static final String url = "https://gutendex.com/books/";


    public void busquedaLibro(String libro) {

        var json = conexionAPI.consumoAPI(url + "?search=" + libro.replace(" ", "%20"));

/*
        System.out.println(url + "?search=" + libro.replace(" ", "%20"));
        System.out.println("JSON" + json);
*/

        var libroBuscado = conversionDatos.consumoAPI(json, LibroDatosGenerales.class);

        /*System.out.println(libroBuscado);*/

        Optional<LibroDatos> libroEncontrado = libroBuscado.libroResultado().stream()
                .filter(l -> l.titulo().toLowerCase().contains(libro.toLowerCase()))
                .findFirst();

        /*       System.out.println(libroEncontrado);*/

        if (libroEncontrado.isPresent()) {
            LibroDatos serial = libroEncontrado.get();

            Libro libroParaGuardar = new Libro();

            libroParaGuardar.setTitulo(serial.titulo());
            libroParaGuardar.setAutor(serial.autores());
            libroParaGuardar.setIdioma(serial.idioma());
            libroParaGuardar.setNumeroDescargas(serial.numeroDescargas());

            Autor autorParaGuardar = new Autor().obtenerAutor(serial);

            System.out.println(libroParaGuardar);
            System.out.println(autorParaGuardar);

            libroRespositorio.save(libroParaGuardar);
            autorRepositorio.save(autorParaGuardar);


            System.out.println("libro cargado");
        } else {
            System.out.println("libro no encontrado");
        }
    }



}

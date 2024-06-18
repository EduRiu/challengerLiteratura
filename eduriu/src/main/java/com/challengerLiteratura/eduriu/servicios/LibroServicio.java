package com.challengerLiteratura.eduriu.servicios;

import com.challengerLiteratura.eduriu.modelos.*;
import com.challengerLiteratura.eduriu.repositorio.LibroRespositorio;
import com.challengerLiteratura.eduriu.conexion.ConexionAPI;
import com.challengerLiteratura.eduriu.conexion.ConversionDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challengerLiteratura.eduriu.repositorio.AutorRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroServicio {

    @Autowired
    LibroRespositorio libroRespositorio;
    @Autowired
    AutorRepositorio autorRepositorio;


    Scanner teclado = new Scanner(System.in);
    Boolean salida = true;
    private ConexionAPI conexionAPI = new ConexionAPI();
    private ConversionDatos conversionDatos = new ConversionDatos();
    private static final String url = "https://gutendex.com/books/";


    public void menu() {

        System.out.println("""
                *** Bienvenidos al Challenger Alura
                a continuacion, se desplega un menu con opciones
                """);

        int opcion;

        do {
            System.out.println("INGRESE UNA OPCION");
            System.out.println("""
                       1- BUSCAR UN LIBRO POR TITULO
                       2- LISTAR LIBROS REGISTRADOS
                       3- LISTAR AUTORES REGISTRADOS
                       4- LISTAR AUTORES VIVOS EN UN DETERMINADO AñO
                       5- LISTAR LIBROS POR IDIOMA
                       0- SALIR
                    """);
            opcion = teclado.nextInt();
            System.out.println(opcion);

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el título del libro:");
                    String titulo = teclado.next();
                    busquedaLibro(titulo);
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    System.out.println("Ingrese el año:");
                    int ano = teclado.nextInt();
                    listarAutoresVivosEnAno(ano);
                    break;
                case 5:
                    System.out.println("Ingrese el idioma:");
                    System.out.println("""
                            es - español
                            en - ingles
                            fr - frances
                            fi - filipino
                              """);
                    String idioma = teclado.next();
                    listarLibrosPorIdioma(idioma);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    salida = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        } while (salida);

    }

    public void busquedaLibro(String libro) {

        var json = conexionAPI.consumoAPI(url + "?search=" + libro.replace("  ", "%20"));

        System.out.println(url + "?search=" + libro.replace(" ", "%20"));
        System.out.println("JSON" + json);

        var libroBuscado = conversionDatos.consumoAPI(json, LibroDatosGenerales.class);

        System.out.println(libroBuscado);

        Optional<LibroDatos> libroEncontrado = libroBuscado.libroResultado().stream()
                .filter(l -> l.titulo().toLowerCase().contains(libro.toLowerCase()))
                .findFirst();

        System.out.println(libroEncontrado);

        if (libroEncontrado.isPresent()) {
            LibroDatos serial = libroEncontrado.get();

            Autor autor = obtenerAutor(serial);
            System.out.println(autor);

            Libro libroParaCargar = new Libro(serial, autor);
            System.out.println(libroParaCargar);

            if (autorRepositorio.findByNombreIgnoreCase(autor.getNombre()).isPresent()) {
                System.out.println("AUTOR YA REGISTRADO");
            } else {
                autorRepositorio.save(autor);
            }

            if (libroRespositorio.findByTituloIgnoreCase(libroParaCargar.getTitulo()).isPresent()) {
                System.out.println("LIBRO YA INGRESADO");
            } else {
                libroRespositorio.save(libroParaCargar);
                System.out.println("libro cargado");
            }

        } else {
            System.out.println("libro no encontrado");
        }

    }

    public Autor obtenerAutor(LibroDatos libroDatos) {
        AutorDatos autorDatos = libroDatos.autores().get(0);
        return new Autor(autorDatos);
    }


    public void listarLibros() {
        List<Libro> libros = libroRespositorio.findAll();
        System.out.println("Lista de libros:");
        for (Libro libro : libros) {
            System.out.println("ID: " + libro.getId() + ", Título: " + libro.getTitulo() + ", Autor: " + libro.getAutores().getNombre());
        }
    }

    public void listarAutores() {
        List<Autor> autores = autorRepositorio.findAll();
        System.out.println("Lista de autores:");
        for (Autor autor : autores) {
            System.out.println("ID: " + autor.getId() + ", Nombre: " + autor.getNombre());
        }
    }

    public void listarAutoresVivosEnAno(int ano) {

        List<Autor> autores = autorRepositorio.autoresVivos(ano);
        if (autores.isEmpty()) {
            System.out.println("NO HAY AUTORES VIVOS");
        } else {
            System.out.println("Lista de autores vivos al : " + ano);
            for (Autor autor : autores) {
                System.out.println("ID: " + autor.getId() + ", Nombre: " + autor.getNombre());
            }
        }

    }


    public void listarLibrosPorIdioma(String idioma) {

        List<Libro> libros = libroRespositorio.libroPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("no hay libros con ese idioma");
        } else {
            for (Libro libro : libros) {
                System.out.println("ID: " + libro.getId() + ", Título: " + libro.getTitulo() + ", Autor: " + libro.getAutores().getNombre());
            }

        }
    }


}

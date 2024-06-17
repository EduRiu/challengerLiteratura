package com.challengerLiteratura.eduriu.modelos;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autores;
    private String idioma;
    private Integer numeroDescargas;

    public Libro() {
    }

    public Libro(LibroDatos libroDatos, Autor autor){
        this.titulo = libroDatos.titulo();
        this.autores = autor;
        this.idioma = libroDatos.idioma().get(0);
        this.numeroDescargas = libroDatos.numeroDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutores() {
        return autores;
    }

    public void setAutores(Autor autores) {
        this.autores = autores;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "Id=" + Id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idioma='" + idioma + '\'' +
                ", numeroDescargas=" + numeroDescargas +
                '}';
    }
}
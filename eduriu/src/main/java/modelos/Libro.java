package modelos;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    private List<AutorDatos> autores;
    private List<String> idioma;
    private Integer numeroDescargas;


    public Libro() {
    }

    public Libro(String titulo, List<AutorDatos> autores, List<String> idioma, Integer numeroDescargas) {
        this.titulo = titulo;
        this.autores = autores;
        this.idioma = idioma;
        this.numeroDescargas = numeroDescargas;
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

    public List<AutorDatos> getAutor() {
        return autores;
    }

    public void setAutor(List<AutorDatos> autor) {
        this.autores = autor;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
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
                ", idioma=" + idioma +
                ", numeroDescargas=" + numeroDescargas +
                '}';
    }
}

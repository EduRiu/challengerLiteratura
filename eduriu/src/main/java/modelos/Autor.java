package modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private Integer fechaInicio;
    private Integer fechaFinal;
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;


    public Autor() {
    }

    public Autor(Long id, String nombre, Integer fechaInicio, Integer fechaFinal, List<Libro> libros) {
        Id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.libros = libros;
    }

    public Autor(AutorDatos autorDatos) {
        this.nombre = autorDatos.nombre();
        this.fechaInicio = Integer.valueOf(autorDatos.fechaInicio());
        this.fechaFinal = Integer.valueOf(autorDatos.fechaFinal());
    }

    public Autor obtenerAutor(LibroDatos libroDatos){
        AutorDatos autorDatos = libroDatos.autores().get(0);
        return new Autor(autorDatos);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Integer fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Integer fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFinal=" + fechaFinal +
                ", libros=" + libros +
                '}';
    }
}

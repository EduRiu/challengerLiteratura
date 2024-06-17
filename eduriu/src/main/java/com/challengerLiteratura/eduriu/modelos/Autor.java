package com.challengerLiteratura.eduriu.modelos;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaInicio;
    private Integer fechaFinal;

    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;

    public Autor() {
    }

    public Autor (AutorDatos autorDatos){
        this.nombre = autorDatos.nombre();
        this.fechaInicio = autorDatos.fechaInicio();
        this.fechaFinal = autorDatos.fechaFinal();
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

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFinal=" + fechaFinal +
                ", libro=" + libro +
                '}';
    }
}

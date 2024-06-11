package modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private Integer fechaInicio;
    private Integer fechaFinal;
    @OneToMany
    private Libro libro;


}

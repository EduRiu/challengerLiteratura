package modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDatos(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaInicio,
        @JsonAlias("death_year") Integer fechaFinal
) {
}

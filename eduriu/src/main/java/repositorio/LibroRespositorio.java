package repositorio;

import modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRespositorio extends JpaRepository<Libro, Long> {

}

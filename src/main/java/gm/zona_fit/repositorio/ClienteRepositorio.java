package gm.zona_fit.repositorio;

import gm.zona_fit.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {//tipo de dato de la clase, tipo de dato de PK
//de la interfas JPA Guardar, eliminar refrescar clñietne

}

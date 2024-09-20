package mx.com.curso_microservicios.carro_service.repository;

import mx.com.curso_microservicios.carro_service.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

    List<Carro> findByUsuarioId(int carroId);
}

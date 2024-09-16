package mx.com.curso_microservicios.moto_service.repository;

import mx.com.curso_microservicios.moto_service.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {

    List<Moto> findByUsuarioId(int usuarioId);
}

package mx.com.curso_microservicios.usuario_service.feignclients;

import mx.com.curso_microservicios.usuario_service.modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8003")
public interface MotoFeignClient {

    @PostMapping("/moto")
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/moto/usuario/{usuarioId}")
    public List<Moto> getMotos(@PathVariable("usuarioId") int usuarioId);

}

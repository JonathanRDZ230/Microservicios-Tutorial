package mx.com.curso_microservicios.moto_service.controller;

import mx.com.curso_microservicios.moto_service.entity.Moto;
import mx.com.curso_microservicios.moto_service.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoControlador {

    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> listarMotos() {
        List<Moto> motos = motoService.getAll();
        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id) {
        Moto moto = motoService.getMotoById(id);
        if(moto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    @PostMapping
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto) {
        Moto nuevoMoto = motoService.save(moto);
        return ResponseEntity.ok(nuevoMoto);
    }

    @GetMapping("/usuario/{usuarioID}")
    public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable ("usuarioID") int id){
        List<Moto> motos = motoService.byUsuarioId(id);
        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }
}

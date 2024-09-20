package mx.com.curso_microservicios.usuario_service.modelos;

import lombok.Data;

@Data
public class Carro {

    private String marca;
    private String modelo;
    private int usuarioId;

    public Carro() {
    }

}

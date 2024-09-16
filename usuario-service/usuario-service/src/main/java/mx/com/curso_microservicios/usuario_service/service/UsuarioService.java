package mx.com.curso_microservicios.usuario_service.service;

import mx.com.curso_microservicios.usuario_service.entity.Usuario;
import mx.com.curso_microservicios.usuario_service.feignclients.CarroFeignClient;
import mx.com.curso_microservicios.usuario_service.feignclients.MotoFeignClient;
import mx.com.curso_microservicios.usuario_service.modelos.Carro;
import mx.com.curso_microservicios.usuario_service.modelos.Moto;
import mx.com.curso_microservicios.usuario_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

    @SuppressWarnings("unchecked")
    public List<Carro> getCarros(int usuarioId) {
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);
        return carros;
    }

    @SuppressWarnings("unchecked")
    public List<Moto> getMotos(int usuarioId) {
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
        return motos;
    }

    public Carro saveCarro(int usuarioId, Carro carro) {
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(int usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        return motoFeignClient.save(moto);
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId) {
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario == null) {
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }
        resultado.put("Usuario", usuario);
        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        if(carros.isEmpty()) {
            resultado.put("Carros", "El usuario no tiene carros");
        } else {
            resultado.put("Carros", carros);
        }
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if(motos.isEmpty()) {
            resultado.put("Motos", "El usuario no tiene motos");
        } else {
            resultado.put("Motos", motos);
        }
        return resultado;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }
}

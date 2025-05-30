package gm.zona_fit.servicio;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Importanticimo
public class ClienteServicio implements IClienteServicio {

    @Autowired //Autoinyectar una referencia de la capa de datos de la interface repositorio
    private ClienteRepositorio clienteRepositorio;


    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClientePorId(Integer idCliente) {
        Cliente cliente = clienteRepositorio.findById(idCliente).orElse(null); //En caso de que no se encuentre el registro devuelve nullo
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);

    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepositorio.delete(cliente);

    }
}

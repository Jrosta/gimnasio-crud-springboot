package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;
	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	String nl = System.lineSeparator(); //Forma de obtener el caracter de salto de linea

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		//Levantar la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args); //Spring Fabrica de obj -> Primero para utilizar los obj
		logger.info("Aplicacion finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp(){
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info("");
		}

	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
		\n+++ Aplicacion Zona Fit +++
		1. Listar cliente.
		2. Buscar Cliente.
		3. Agregar Cliente.
		4. Modificar Cliente.
		5. Eliminar Cliente.
		6. Salir.
		Elige una opcion:\s""");
		return Integer.parseInt(consola.nextLine());
	}

	private boolean ejecutarOpciones (Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1->{
				logger.info(nl + "--- Listado de Clientes ---" + nl);
				List<Cliente> clientes = clienteServicio.listarCliente();
				clientes.forEach(cliente -> logger.info(cliente.toString() + nl));

			}
			case 2 -> {
				logger.info(nl + "--- Buscar Cliente por ID ---" + nl);
				logger.info("ID Cliente a buscar: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if (cliente != null)
					logger.info("Cliente encontrado" + cliente + nl);
				else
					logger.info("Cliente no encontrado" + cliente + nl);
			}
			case 3 -> {
				logger.info("--- Agregar Cliente ---" + nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Membresia: ");
				var membresia = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente (); //Podriamos agregar un constructor de 3 parametros ()
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				clienteServicio.guardarCliente(cliente);
				logger.info("Cliente agregado: " + cliente + nl);
			}
			case 4 -> {
				logger.info("--- Modificar cliente ---" + nl);
				logger.info("Id Cliente: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if (cliente != null){ // Si es !null -> se encontró en la BD
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Membresia: ");
					var membresia = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);
					clienteServicio.guardarCliente(cliente); //Guardamos el obj de tipo cliente
					logger.info("Cliente modificado: " + cliente + nl);
				}
				else
					logger.info("Cliente NO encontrado: " + cliente + nl);
			}
			case 5-> {
				logger.info("--- Eliminar Cliente ---" + nl);
				logger.info("ID Cliente: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				var cliente = clienteServicio.buscarClientePorId(idCliente);
				if (cliente != null){
					clienteServicio.eliminarCliente(cliente);
					logger.info("Cliente eliminado: " + cliente + nl);
				}
				else
					logger.info("Cliente NO encontrado: " + cliente + nl);

			}
			case 6 -> {
				logger.info("Hasta pronto!! " + nl +nl);
				salir = true;
			}
			default -> logger.info("Opcion NO reconocida: " + opcion + nl);
		}
		return salir;
	}
}

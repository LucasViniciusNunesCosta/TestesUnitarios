package negocio;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 *Alunos: Lucas Vinicius, Gian Manincor
 *Data 24/09/2021
 */
public class GerenciadoraClientestest {

	private GerenciadoraClientes Gclientes;
	private int idCliente1 = 1;
	private int idCliente2 = 2;
	private int idCliente3 = 3;
	
	@Before
	public void cenario() {
		
		//criacao de clientes ficticios
		Cliente cliente1 = new Cliente(idCliente1, "Gian", 26, "GianManincor@gmail.com", 21, true);
		Cliente cliente2 = new Cliente(idCliente2, "Lucas", 19, "LucasVinicius@gmail.com", 20, true);
		Cliente cliente3 = new Cliente(idCliente3, "Pedro", 24, "Pedro@gmail.com", 22, true);
		
		//inserindo os clientes ficticios na lista de clientes do banco
		List<Cliente> ClientesBanco = new ArrayList<Cliente>();
		ClientesBanco.add(cliente1);
		ClientesBanco.add(cliente2);
		
		Gclientes = new GerenciadoraClientes(ClientesBanco);
		
	}
	@After
	public void destruir() {
		Gclientes.limpa();
	}
	
	@Test
	public void devePesquisarClientePorId() {
		Cliente cliente = Gclientes.pesquisaCliente(idCliente1);
		
		assertThat(cliente.getId(), Is.is(idCliente1));
	}
	
	@Test
	public void deveRemoverClientePorId() {
		boolean clienteRemovido = Gclientes.removeCliente(idCliente2);
		
		assertThat(clienteRemovido, Is.is(true));
		assertThat(Gclientes.getClientesDoBanco().size(), Is.is(1));
		assertNull(Gclientes.pesquisaCliente(idCliente2));
	}
	
	@Test
	public void deveVerificarSeClienteEstaAtivo() {
		boolean clienteAtivo = Gclientes.clienteAtivo(idCliente1);
		
		assertThat(clienteAtivo, Is.is(true));
		assertThat(Gclientes.getClientesDoBanco().size(), Is.is(2));
	}
	@Test
	public void deveAdicionarNovoCliente() {
		
	}
}

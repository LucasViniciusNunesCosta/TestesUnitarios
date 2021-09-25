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
	private int idCliente4 = 4;
	
	@Before
	public void cenario() {
		
		//criacao de clientes ficticios
		Cliente cliente1 = new Cliente(idCliente1, "Gian", 26, "GianManincor@gmail.com", 21, true);
		Cliente cliente2 = new Cliente(idCliente2, "Lucas", 9, "LucasVinicius@gmail.com", 20, true);
		Cliente cliente3 = new Cliente(idCliente3, "Pedro", 20, "Pedro@gmail.com", 19, true);
		Cliente cliente4 = new Cliente(idCliente4, "Pedro", 80, "Pedro@gmail.com", 19, true);
		
		//inserindo os clientes ficticios na lista de clientes do banco
		List<Cliente> ClientesBanco = new ArrayList<Cliente>();
		ClientesBanco.add(cliente1);
		ClientesBanco.add(cliente2);
		ClientesBanco.add(cliente3);
		ClientesBanco.add(cliente4);
		
		Gclientes = new GerenciadoraClientes(ClientesBanco);
		
	}
	@After
	public void destruir() {
		Gclientes.limpa();
	}
	
	@Test
	public void devePesquisarClientePorId() {
		//execucao
		Cliente cliente = Gclientes.pesquisaCliente(idCliente1);
		
		//verificacao
		assertThat(cliente.getId(), Is.is(idCliente1));
	}
	
	@Test
	public void deveRemoverClientePorId() {
		//execucao
		boolean clienteRemovido = Gclientes.removeCliente(idCliente2);
		
		//verificacao
		assertTrue(clienteRemovido);
		assertNull(Gclientes.pesquisaCliente(idCliente2));
	}
	
	@Test
	public void deveVerificarSeClienteEstaAtivo() {
		//execucao
		boolean clienteAtivo = Gclientes.clienteAtivo(idCliente1);
		//verificacao
		assertThat(clienteAtivo, Is.is(true));
	}
	
	@Test
	public void deveVerificarValidadeDeIdadeDentroDoIntervaloPermitido() throws IdadeNaoPermitidaException {
		
		Cliente cliente = Gclientes.pesquisaCliente(idCliente3);
		assertTrue(Gclientes.validaIdade(cliente.getIdade()));	
	}
	
	@Test(expected = IdadeNaoPermitidaException.class)
	public void deveVerificarValidadeDeIdadeForaDoIntervaloPermitido() throws IdadeNaoPermitidaException {
		
		Cliente cliente1 = Gclientes.pesquisaCliente(idCliente4);
		Cliente cliente2 = Gclientes.pesquisaCliente(idCliente4);
		Gclientes.validaIdade(cliente1.getIdade());
		Gclientes.validaIdade(cliente2.getIdade());
	}
}

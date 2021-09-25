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
	
	@Before
	public void cenario() {
		
		//criacao de clientes ficticios
		Cliente cliente1 = new Cliente(idCliente1, "Gian", 26, "GianManincor@gmail.com", 21, true);
		Cliente cliente2 = new Cliente(idCliente2, "Lucas", 19, "LucasVinicius@gmail.com", 20, true);
		
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
		assertThat(Gclientes.getClientesDoBanco().size(), Is.is(1));
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
	public void deveVerificarValidadeDeIdade() throws IdadeNaoPermitidaException {
		
		Cliente cliente = new Cliente(5, "Pedro", 24, "Pedro@gmail.com", 19, true);
		int idadeMenor = 11;
		int idadeMaior = 70;
		
		//execucao
		boolean idadeAceita = Gclientes.validaIdade(cliente.getIdade());
		//boolean idadeInsuficiente = Gclientes.validaIdade(idadeMenor);
		//boolean idadeExcedente = Gclientes.validaIdade(idadeMaior);
		
		//verificacao
		assertTrue(idadeAceita);
		//assertFalse(idadeInsuficiente);
		//assertFalse(idadeExcedente);
		
	}
}

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

public class GerenciadoraContasTest {
	private GerenciadoraContas Gcontas;
	
	@Test
	public void saldoSuficienteEmAmbasAsContas() {
		
		//cenario teste 1
		int idConta1 = 1;
		int idConta2 = 2;
		
		ContaCorrente conta1 = new ContaCorrente(idConta1, 200, true);
		ContaCorrente conta2 = new ContaCorrente(idConta2, 0, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta1);
		contasDoBanco.add(conta2);
		
		Gcontas = new GerenciadoraContas(contasDoBanco);
		
		//execucao
		boolean sucesso = Gcontas.transfereValor(idConta1, 100, idConta2);
		
		//verificacao
		assertTrue(sucesso);
		assertThat(conta1.getSaldo(), Is.is(100.00));
		assertThat(conta2.getSaldo(), Is.is(100.00));
	}
	@Test
	public void SaldoPositivoEInsuficienteConta1() {
		//cenario teste2
		int idConta1 = 1;
		int idConta2 = 2;
		
		ContaCorrente conta1 = new ContaCorrente(idConta1, 100, true);
		ContaCorrente conta2 = new ContaCorrente(idConta2, 0, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta1);
		contasDoBanco.add(conta2);
		
		Gcontas = new GerenciadoraContas(contasDoBanco);
		
		//execucao
		boolean sucesso = Gcontas.transfereValor(idConta1, 200, idConta2);
		
		//verificacao
		assertTrue(sucesso);
		assertThat(conta1.getSaldo(), Is.is(-100.00));
		assertThat(conta2.getSaldo(), Is.is(200.00));
	}
	@Test
	public void SaldoInsuficienteENegativoConta1() {
		//cenario teste3
		int idConta1 = 1;
		int idConta2 = 2;
		
		ContaCorrente conta1 = new ContaCorrente(idConta1, -100, true);
		ContaCorrente conta2 = new ContaCorrente(idConta2, 0, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta1);
		contasDoBanco.add(conta2);
		
		Gcontas = new GerenciadoraContas(contasDoBanco);
		
		//execucao
		boolean sucesso = Gcontas.transfereValor(idConta1, 200, idConta2);
		
		//verificacao
		assertTrue(sucesso);
		assertThat(conta1.getSaldo(), Is.is(-300.00));
		assertThat(conta2.getSaldo(), Is.is(200.00));
	}
	@Test
	public void SaldoInsuficienteEmAmbasContas() {
		//cenario teste4
		int idConta1 = 1;
		int idConta2 = 2;
		
		ContaCorrente conta1 = new ContaCorrente(idConta1, -100, true);
		ContaCorrente conta2 = new ContaCorrente(idConta2, -100, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta1);
		contasDoBanco.add(conta2);
		
		Gcontas = new GerenciadoraContas(contasDoBanco);
		
		//execucao
		boolean sucesso = Gcontas.transfereValor(idConta1, 200, idConta2);
		
		//verificacao
		assertTrue(sucesso);
		assertThat(conta1.getSaldo(), Is.is(-300.00));
		assertThat(conta2.getSaldo(), Is.is(100.00));
	}
}

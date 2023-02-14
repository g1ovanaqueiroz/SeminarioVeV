package main;

import java.util.ArrayList;
import java.util.List;

public class Fachada {
	private List<Conta> contas = new ArrayList<Conta>();
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public void createConta(String conta, String cliente) {
		this.contas.add(new Conta(conta, cliente));
	}

	public void createCliente(String ID, String nome) {
		this.clientes.add(new Cliente(ID, nome));
	}

	public void transferir(String contaOrigem, String contaDestino, float valor) throws Exception {
		Conta origem = this.findByNum(contaOrigem);
		Conta destino = this.findByNum(contaDestino);

		if (origem.getSaldo() >= valor) {
			origem.setSaldo(origem.getSaldo() - valor);
			destino.setSaldo(destino.getSaldo() + valor);
		}
		throw new Exception("Saldo insuficiente!");
	}

	public Conta findByNum(String num) throws Exception {
		for (Conta conta : contas) {
			if (conta.getConta() == num)
				return conta;
		}
		throw new Exception("Conta inexistente!");
	}
}

package main;

public class Conta {
	private String nroConta;
	private String IDCliente;
	private float saldo;

	public Conta(String nroConta, String IDCliente) {
		this.nroConta = nroConta;
		this.IDCliente = IDCliente;
		this.saldo = 0;
	}
	
	public String getConta() {
		return this.nroConta;
	}
	
	public float getSaldo() {
		return this.saldo;
	}
	
	public String getCliente() {
		return IDCliente;
	}
	
	public void setCliente(String cliente) {
		this.IDCliente = cliente;
	}
	
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
}
package main;

public class Cliente {
	private String ID;
	private String nome;
	
	public Cliente() {}
	
	public Cliente(String ID, String nome) {
		this.ID = ID;
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
}

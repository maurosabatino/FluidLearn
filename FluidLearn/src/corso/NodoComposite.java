package corso;

import java.util.ArrayList;

public class NodoComposite implements Nodo {
	
	private int idNodo;
	private String nome;
	private String descrizione;
	private int idUDA;
	private ArrayList<Integer> nodi;
	@Override
	public int getIdNodo() {
		return idNodo;
	}
	@Override
	public void setIdNodo(int idNodo) {
		this.idNodo = idNodo;
	}
	@Override
	public String getNome() {
		return nome;
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String getDescrizione() {
		return descrizione;
	}
	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public int getIdUDA() {
		return idUDA;
	}
	@Override
	public void setIdUDA(int idUDA) {
		this.idUDA = idUDA;
	}
	@Override
	public boolean isComposite() {
		return true;
	}
	
	public ArrayList<Integer> getNodi(){
		return nodi;
	}
	
	public void setNodi(ArrayList<Integer> nodi){
		this.nodi = nodi;
	}

	public void addNodo(int idNodo){
		nodi.add(idNodo);
	}
	
	

}

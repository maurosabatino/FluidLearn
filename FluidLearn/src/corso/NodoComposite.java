package corso;

import java.util.ArrayList;

public class NodoComposite implements Nodo {
	
	private int idNodo;
	private String nome;
	private String descrizione;
	private int idUDA;
	private ArrayList<Nodo> nodi;
	
	public NodoComposite(){
		nodi = new ArrayList<Nodo>();
	}
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
	
	public ArrayList<Nodo> getNodi(){
		return nodi;
	}
	
	public void setNodi(ArrayList<Nodo> nodi){
		this.nodi = nodi;
	}

	public void addNodo(Nodo Nodo){
		nodi.add(Nodo);
	}
	
	

}

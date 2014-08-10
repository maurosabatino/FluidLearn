package corso;


public class NodoLeaf implements Nodo{
	private int idNodo;
	private String nome;
	private String descrizione;
	private int idUDA;
	
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
		return false;
	}
}

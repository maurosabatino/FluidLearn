package corso;

import java.util.Date;
import java.util.ArrayList;

public class UnitaDA {

	String nome;
	Date data;
	int idUDA;
	
	int idCorso;

	String descrizione;
	ArrayList<Integer> UDADipendenti;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date date) {
		this.data = date;
	}
	public int getIdUDA() {
		return idUDA;
	}
	public void setIdUDA(int idUDA) {
		this.idUDA = idUDA;
	}
	public int getIdCorso() {
		return idCorso;
	}
	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public ArrayList<Integer> getUDADipendenti() {
		return UDADipendenti;
	}
	public void setUDADipendenti(ArrayList<Integer> uDADipendenti) {
		UDADipendenti = uDADipendenti;
	}
	
}


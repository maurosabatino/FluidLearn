package contributo;

import java.util.Date;

import contributo.corpo.Corpo;
import contributo.state.azione.AzioneNuovo;
import contributo.state.azione.StatoAzione;

public class Post implements Azione{
	StatoAzione stato;
	int IDPost;
	int IDPartecipante;
	int visibilita;
	int idUDA;
	int IDNodo;
	Corpo corpo;
	Date data;
	public Post(){
		stato = new AzioneNuovo();
	}
	
	public int getIDPost() {
		return IDPost;
	}
	public void setIDPost(int iDPost) {
		IDPost = iDPost;
	}
	public int getIDPartecipante() {
		return IDPartecipante;
	}
	public void setIDPartecipante(int iDPartecipante) {
		IDPartecipante = iDPartecipante;
	}
	public int getVisibilita() {
		return visibilita;
	}
	public void setVisibilita(int visibilita) {
		this.visibilita = visibilita;
	}
	public int getIDNodo() {
		return IDNodo;
	}
	public void setIDNodo(int iDNodo) {
		IDNodo = iDNodo;
	}
	public Corpo getCorpo() {
		return corpo;
	}
	public void setCorpo(Corpo corpo) {
		this.corpo = corpo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public boolean hasDeadline() {
		return false;
	}
	@Override
	public void setIDUDA(int idUDA) {
		this.idUDA = idUDA;
	}
	@Override
	public int getIDUDA() {
		return idUDA;
	}
	@Override
	public void pubblica(String stato) {
		this.stato.pubblica(this, stato);
		
	}
	@Override
	public void setStato(StatoAzione stato) {
		this.stato = stato;
	}
	@Override
	public StatoAzione getStato() {
		return stato;
	}
	
	
}

package contributo;

import java.util.Date;

public class Post implements Azione{
	
	int IDPost;
	int IDPartecipante;
	int visibilita;
	int IDNodo;
	Corpo corpo;
	Date data;
	
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
	
	
}
package contributo;

import java.util.Date;

import contributo.corpo.Corpo;
import contributo.state.reazione.ReazioneNuovo;
import contributo.state.reazione.StatoReazione;

public class Commento implements Reazione{
	private int IDCommento;
	private int IDPartecipante;
	private int IDPost;
	private Corpo corpo;
	private Date data;
	
	StatoReazione stato;
	
	public Commento(){
		stato = new ReazioneNuovo();
	}
	
	@Override
	public int getIDCommento() {
		return IDCommento;
	}
	@Override
	public void setIDCommento(int IDCommento) {
		this.IDCommento = IDCommento;
	}
	@Override
	public int getIDPartecipante() {
		return IDPartecipante;
	}
	@Override
	public void setIDPartecipante(int IDPartecipante) {
		this.IDPartecipante = IDPartecipante;
	}
	@Override
	public int getIDPost() {
		return IDPost;
	}
	@Override
	public void setIDPost(int IDPost) {
		this.IDPost = IDPost;
	}
	@Override
	public Date getData() {
		return data;
	}
	@Override
	public void setData(Date data) {
		this.data = data;
	}	
	@Override
	public Corpo getCorpo() {
		return corpo;
	}
	@Override
	public void setCorpo(Corpo corpo) {
		this.corpo = corpo;		
	}

	@Override
	public void pubblica(String stato) {
		this.stato.pubblica(this, stato);
	}

	@Override
	public void setStato(StatoReazione stato) {
		this.stato = stato;
	}

	@Override
	public StatoReazione getStato() {
		return stato;
	}
}

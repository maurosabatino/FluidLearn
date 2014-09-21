package contributo.valutazione;

public class ValutazioneString implements Valutazione {
	
	int idValutazione;
	String voto;
	String note;
	int visibilita;
	
	public String getVoto() {
		return voto;
	}
	
	public void setVoto(String voto) {
		this.voto = voto;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getVisibilità() {
		return visibilita;
	}
	public void setVisibilità(int visibilita) {
		this.visibilita = visibilita;
	}
	
	@Override
	public int getTipo() {
		return Valutazione.STRINGA;
	}

	@Override
	public int getIdValutazione() {
		return idValutazione;
	}

	@Override
	public void setIdValutazione(int idValutazione) {
		this.idValutazione = idValutazione;
		
	}

	
	
	
	
	
	
}

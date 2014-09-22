package contributo.valutazione;

public class ValutazioneInt implements Valutazione{
	
	int idValutazione;
	int voto;
	boolean lode;
	String note;
	int visibilità;
	
	@Override
	public String getVoto() {
		if(lode) return "30L";
		else return voto+"";
	}
	@Override
	public void setVoto(String voto) {
		if(voto.equalsIgnoreCase("30L")){
			this.voto = 30;
			this.lode = true;
		}else this.voto = Integer.parseInt(voto);
	}
	@Override
	public String getNote() {
		return note;
	}
	@Override
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public int getVisibilità() {
		return visibilità;
	}
	@Override
	public void setVisibilità(int visibilita) {
		this.visibilità = visibilita;
	}
	@Override
	public int getTipo() {
		return Valutazione.INTERO;
	}
	@Override
	public int getIdValutazione() {
		return idValutazione;
	}
	@Override
	public void setIdValutazione(int idValutazione) {
		this.idValutazione = idValutazione;
		
	}
	public boolean isLode() {
		return lode;
	}
	public void setLode(boolean lode) {
		this.lode = lode;
	}
	
}

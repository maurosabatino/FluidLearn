package contributo.valutazione;

public interface Valutazione {
	
	public static final int INTERO = 1;
	public static final int STRINGA = 2;
	
	public int getIdValutazione();
	public void setIdValutazione(int idValutazione);
	
	public String getVoto();
	public void setVoto(String voto);
	
	public String getNote();
	public void setNote(String note);
	
	public int getVisibilità();
	public void setVisibilità(int visibilita);
	
	public int getTipo();
}

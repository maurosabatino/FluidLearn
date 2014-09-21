package contributo;

import java.util.Date;

import contributo.corpo.Corpo;
import contributo.state.reazione.StatoReazione;


public interface Reazione {
	public int getIDCommento();
	public void setIDCommento(int IDCommento);
	public int getIDPartecipante();
	public void setIDPartecipante(int IDPartecipante);
	public int getIDPost();
	public void setIDPost(int IDPost);
	public Date getData();
	public void setData(Date data);
	public Corpo getCorpo();
	public void setCorpo(Corpo corpo);
	
	public void pubblica(String stato);
	public void setStato(StatoReazione stato);
	public StatoReazione getStato();
}

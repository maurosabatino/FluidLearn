package contributo;

import java.util.Date;
import contributo.corpo.*;
import contributo.state.azione.*;

public interface Azione {
	public int getIDPost();
	public void setIDPost(int IDPost);
	
	public int getIDPartecipante();
	public void setIDPartecipante(int IDPartecipante);
	
	public int getVisibilita();
	public void setVisibilita(int visibilita);
	
	
	public int getIDUDA();
	public void setIDUDA(int idUDA);
	
	public int getIDNodo();
	public void setIDNodo(int IDNodo);
	
	public Corpo getCorpo();
	public void setCorpo(Corpo corpo);
	
	public Date getData();
	public void setData(Date d);
	
	public boolean hasDeadline();
	
	public void pubblica(String stato);
	
	public void setStato(StatoAzione stato);
	public StatoAzione getStato();
}

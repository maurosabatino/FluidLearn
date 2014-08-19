package contributo;

import java.util.Date;

public interface Azione {
	public int getIDPost();
	public void setIDPost(int IDPost);
	public int getIDPartecipante();
	public void setIDPartecipante(int IDPartecipante);
	public int getVisibilita();
	public void setVisibilita(int visibilita);
	public int getIDNodo();
	public void setIDNodo(int IDNodo);
	public Corpo getCorpo();
	public void setCorpo(Corpo corpo);
	public Date getData();
	public void setData(Date d);
	public void setData(long d);
	public boolean hasDeadline();
}

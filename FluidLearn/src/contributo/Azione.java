package contributo;

import java.util.Date;

public interface Azione {
	public int getIDPost();
	public void setIDPost(int IDPost);
	public int getIDPartecipante();
	public void setIDPartecipante(int IDPartecipante);
	public int getVisibilita();
	public void setVisibilita(int visibilita);
	public void setIDUDA(int idUDA);
	public int getIDUDA();
	public int getIDNodo();
	public void setIDNodo(int IDNodo);
	public Corpo getCorpo();
	public void setCorpo(Corpo corpo);
	public Date getData();
	public void setData(Date d);
	public boolean hasDeadline();
}

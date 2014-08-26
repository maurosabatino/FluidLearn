package contributo;

import java.util.Date;

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
}

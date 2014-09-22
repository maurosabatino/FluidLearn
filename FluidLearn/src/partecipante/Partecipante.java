package partecipante;

import java.sql.SQLException;


public interface Partecipante {
	public int getIDPartecipante();
	public void setIDPartecipante(int idPartecipante);
	
	
	
	public String getNome();
	public void setNome(String nome);
	
	public String getPassword();
	public void setPassword(String password);

	public boolean isAutorePost(int IDPost) throws SQLException;
	public boolean isAutoreCommento(int IDCommento) throws SQLException;
	public boolean hasRole(Role r);
	public boolean isDecorated();
}

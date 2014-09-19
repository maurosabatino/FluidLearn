package partecipante;


public interface Partecipante {
	public int getIDPartecipante();
	public void setIDPartecipante(int idPartecipante);
	
	
	
	public String getNome();
	public void setNome(String nome);
	
	public String getPassword();
	public void setPassword(String password);

	public boolean isAutorePost(int IDPost);
	public boolean isAutoreCommento(int IDCommento);
	public boolean hasRole(Role r);
	public boolean isDecorated();
}

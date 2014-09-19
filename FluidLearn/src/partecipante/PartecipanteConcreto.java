package partecipante;

public class PartecipanteConcreto implements Partecipante{
	int idPartecipante;
	
	String nome;
	String password;
	
	
	@Override
	public int getIDPartecipante() {
		return idPartecipante;
	}

	@Override
	public void setIDPartecipante(int idPartecipante) {
		this.idPartecipante = idPartecipante;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome; 
	}

	@Override
	public boolean isAutorePost(int IDPost) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAutoreCommento(int IDCommento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRole(Role r) {
		return false;
	}

	@Override
	public boolean isDecorated() {
		return false;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
		
	}

	

}

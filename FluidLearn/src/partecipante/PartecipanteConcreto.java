package partecipante;

import java.sql.SQLException;

import controller.DatabaseController;

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
	public boolean isAutorePost(int IDPost) throws SQLException {
		return DatabaseController.isAutorePost(idPartecipante,IDPost);
	}

	@Override
	public boolean isAutoreCommento(int IDCommento) throws SQLException {
		return DatabaseController.isAutoreCommento(idPartecipante,IDCommento);
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

package partecipante;

public class Ideatore implements Partecipante{
	Partecipante part;
	public Ideatore(Partecipante p){
		part = p;
	}
	@Override
	public int getIDPartecipante() {
		return part.getIDPartecipante();
	}

	@Override
	public void setIDPartecipante(int idPartecipante) {
		part.setIDPartecipante(idPartecipante);
	}

	@Override
	public String getNome() {
		return part.getNome();
	}

	@Override
	public void setNome(String nome) {
		part.setNome(nome);
	}
	@Override
	public String getPassword() {
		return part.getPassword();
	}

	@Override
	public void setPassword(String password) {
		part.setPassword(password);
		
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
		if(r == Role.IDEATORE) return true;
		return part.hasRole(r);
	}

	@Override
	public boolean isDecorated() {
		return false;
	}

	

}

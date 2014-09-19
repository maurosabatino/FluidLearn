package partecipante;

public class Moderatore implements Ruolo{
	Partecipante part;
	public Moderatore(Partecipante p){
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
		if(r == Role.MODERATORE) return true;
		return part.hasRole(r);
	}

	@Override
	public boolean isDecorated() {
		return true;
	}

	@Override
	public Partecipante undecorate() {
		if(part.isDecorated()) return ((Ruolo)part).undecorate();
		return part;
	}

	@Override
	public Partecipante undecorate(Role r) {
		if(r == Role.MODERATORE) return part;
		if(part.isDecorated()) part = ((Ruolo)part).undecorate(r);
		return this;
	}
	

}

package partecipante;

public class Tecnico implements Ruolo{
	Partecipante part;
	public Tecnico(Partecipante p){
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
		if(r == Role.TECNICO) return true;
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
		if(r == Role.TECNICO) return part;
		if(part.isDecorated()) part = ((Ruolo)part).undecorate(r);
		return this;
	}
	

}
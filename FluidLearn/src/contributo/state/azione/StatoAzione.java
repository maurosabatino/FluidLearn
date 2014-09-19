package contributo.state.azione;

import contributo.Azione;

public interface StatoAzione {
	public void pubblica(Azione azione, String stato);
	public String getState();
}

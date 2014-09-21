package contributo.state.reazione;

import contributo.Reazione;

public interface StatoReazione {
	public void pubblica(Reazione reazione, String stato);
	public String getState();
}

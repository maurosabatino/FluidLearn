package contributo.state.reazione;

import contributo.Reazione;

public class ReazioneDraft implements StatoReazione {

	@Override
	public void pubblica(Reazione reazione, String stato) {
		if(stato.equalsIgnoreCase("PUBBLICA")) reazione.setStato(new ReazionePubblica());
		
	}

	@Override
	public String getState() {
		return "DRAFT";
	}

}

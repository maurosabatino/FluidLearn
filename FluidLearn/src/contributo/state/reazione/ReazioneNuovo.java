package contributo.state.reazione;

import contributo.Reazione;

public class ReazioneNuovo implements StatoReazione{

	@Override
	public void pubblica(Reazione reazione, String stato) {
		if(stato.equalsIgnoreCase("DRAFT"))reazione.setStato(new ReazioneDraft());
		else reazione.setStato(new ReazionePubblica());
	}

	@Override
	public String getState() {
		return "NUOVO";
	}

}

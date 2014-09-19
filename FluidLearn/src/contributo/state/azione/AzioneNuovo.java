package contributo.state.azione;

import contributo.Azione;


public class AzioneNuovo implements StatoAzione{

	@Override
	public void pubblica(Azione azione, String stato) {
		if(stato.equalsIgnoreCase("DRAFT")) azione.setStato(new AzioneDraft());
		else azione.setStato(new AzionePubblica());
	}

	@Override
	public String getState() {
		return "NUOVO";
	}

}

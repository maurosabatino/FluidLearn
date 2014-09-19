package contributo.state.azione;

import contributo.Azione;


public class AzioneDraft implements StatoAzione{

	@Override
	public void pubblica(Azione azione, String stato) {
		if(stato.equalsIgnoreCase("PUBBLICA")) azione.setStato(new AzionePubblica());
		
	}

	@Override
	public String getState() {
	
		return "DRAFT";
	}

}

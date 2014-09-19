package contributo.state.azione;

import contributo.Azione;


public class AzioneChiuso implements StatoAzione{

	@Override
	public void pubblica(Azione azione, String stato) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getState() {
		return "CHIUSO";
	}

}

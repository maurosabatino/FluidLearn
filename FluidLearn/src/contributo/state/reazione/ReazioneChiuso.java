package contributo.state.reazione;

import contributo.Reazione;

public class ReazioneChiuso implements StatoReazione {

	@Override
	public void pubblica(Reazione reazione, String stato) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getState() {
		return "CHIUSO";
	}

}

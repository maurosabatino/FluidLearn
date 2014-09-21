package contributo.state.azione;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import contributo.Azione;


public class AzionePubblica implements StatoAzione{

	@Override
	public void pubblica(Azione azione, String stato) {
		Calendar closeTime = new GregorianCalendar();
		closeTime.setTime(azione.getData());
		closeTime.add(Calendar.MINUTE, 10);
		if(closeTime.after(new Date())){
			azione.setStato(new AzioneChiuso());
		}
		
	}

	@Override
	public String getState() {
		return "PUBBLICATO";
	}

}

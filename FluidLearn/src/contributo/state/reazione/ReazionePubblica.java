package contributo.state.reazione;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import contributo.Reazione;


public class ReazionePubblica implements StatoReazione{

	@Override
	public void pubblica(Reazione reazione, String stato) {
		Calendar closeTime = new GregorianCalendar();
		closeTime.setTime(reazione.getData());
		closeTime.add(Calendar.MINUTE, 5);
		if(closeTime.after(new Date())){
			reazione.setStato(new ReazioneChiuso());
		}
	}

	@Override
	public String getState() {
		return "PUBBLICATO";
	}

}

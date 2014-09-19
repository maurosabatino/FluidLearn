package contributo.corpo;

public class Testo implements Corpo {
	String text;
	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean hasPlugin() {
		return false;
	}

}

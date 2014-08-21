package contributo;

public class Artefatto implements Corpo{
	int idPlugin;
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
		return true;
	}
	public int getIdPlugin() {
		return idPlugin;
	}
	public void setIdPlugin(int idPlugin) {
		this.idPlugin = idPlugin;
	}
	
}

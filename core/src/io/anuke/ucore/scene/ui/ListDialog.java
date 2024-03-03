package io.anuke.ucore.scene.ui;

public class ListDialog extends Dialog{
	private boolean hide;

	public ListDialog(String title) {
		super(title);
		content().padBottom(10f);
	}
	
	/**Whether to hide after an option is selected.*/
	public void setHide(boolean hide){
		this.hide = hide;
	}
	
	public ListDialog addOption(String name, Runnable run){
		final Runnable runw=run;
		content().addButton(name, new Runnable(){public void run(){
			hide();
			runw.run();
		}}).padLeft(10f).padRight(10f).padBottom(5f).width(200f);
		content().row();
		return this;
	}
}

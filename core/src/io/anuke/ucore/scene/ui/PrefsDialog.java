package io.anuke.ucore.scene.ui;

import io.anuke.ucore.core.Settings;
import io.anuke.ucore.scene.ui.layout.Table;

public class PrefsDialog extends Dialog{

	public PrefsDialog(String title) {
		super(title);
		addCloseButton();
	}
	
	public void sliderPref(String name, String title, int def, int min, int max,StringProcessor s){
		sliderPref(name, title, def, min, max, 1, s);
	}
	
	public void sliderPref(String name, String title, int def, int min, int max, int step, StringProcessor s){
		Table table = getContentTable();
		final String titlew=title;
		final String namew=name;
		final StringProcessor sw=s;
		final Slider slider = new Slider(min, max, 1f, false);
		Settings.defaults(name, def);
		
		slider.setValue(Settings.getInt(name));
		
		final Label label = new Label(title);
		slider.changed(new Runnable(){public void run(){
			label.setText(titlew + ": " + sw.get((int)slider.getValue()));
			Settings.putInt(namew, (int)slider.getValue());
			Settings.save();
		}});
		
		slider.change();
		table.add(label).minWidth(label.getPrefWidth()+50).left();
		table.add(slider);
		final int defw=def;
		table.addButton("Reset",new Runnable(){public void run(){
			slider.setValue(defw);
			slider.change();
		}});
		table.row();
	}
	
	public void checkPref(String name, String title, boolean def){
		Table table = getContentTable();
		final boolean defw=def;
		final CheckBox box = new CheckBox(title);
		Settings.defaults(name, def);
		final String namew=name;
		final String titlew=title;
		box.setChecked(Settings.getBool(name));
		
		box.changed(new Runnable(){public void run(){
			Settings.putBool(namew, box.isChecked);
			Settings.save();
		}});
		
		box.left();
		table.add(box).minWidth(box.getPrefWidth()+50).left();
		table.add().grow();
		table.addButton("Reset", new Runnable(){public void run(){
			box.setChecked(defw);
			box.change();
		}});
		table.row();
	}
	
	public static interface StringProcessor{
		String get(int i);
	}

}

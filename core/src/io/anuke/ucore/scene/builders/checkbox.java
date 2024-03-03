package io.anuke.ucore.scene.builders;

import java.util.function.Consumer;

import io.anuke.ucore.scene.ui.CheckBox;

public class checkbox extends builder<checkbox, CheckBox>{
	
	public checkbox(String text){
		this(text, null);
	}
	
	public checkbox(String text, Consumer<Boolean> listener){
		element = new CheckBox(text);
		if(listener != null){
		final Consumer<Boolean> listenerw=listener;
		final CheckBox elementw=element;
		element.changed(new Runnable(){public void run(){
			listenerw.accept(elementw.isChecked());
		}});
		}
		cell = context().add(element);
	}
	
	public checkbox changed(Consumer<Boolean> listener){
		final Consumer<Boolean> listenerw=listener;
		final CheckBox elementw=element;
		element.changed(new Runnable(){public void run(){
			listenerw.accept(elementw.isChecked());
		}});
		return this;
	}
}

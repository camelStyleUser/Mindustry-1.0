package io.anuke.moment.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.anuke.moment.platform.*;
import io.anuke.moment.Moment;
import io.anuke.moment.MomentVars;
public class DesktopLauncher {
	public static void main (String[] args) {
		boolean forcemobile=false;
		for(String arg:args){
		if(arg.equals("--forcemobile"))forcemobile=true;
		}
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Moment(mindustry 1.0).compiled by 6f#6626");
		config.setMaximized(true);
		config.useVsync(false);
		if(!forcemobile){
		MomentVars.platform=new DesktopPlatform();}
		else{
		AndroidPlatform.forcedmobile=true;
		MomentVars.platform=new AndroidPlatform();
		}
		new Lwjgl3Application(new Moment(), config);
	}
}

package io.anuke.moment.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import io.anuke.moment.Moment;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Moment(mindustry 1.0).compiled by 6f#6626");
		config.setMaximized(true);
		config.useVsync(false);
		new Lwjgl3Application(new Moment(), config);
	}
}

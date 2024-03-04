package io.anuke.moment;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import io.anuke.moment.Moment;
import io.anuke.moment.MomentVars;
import io.anuke.moment.platform.*;
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		MomentVars.platform=new AndroidPlatform();
		initialize(new Moment(), config);
	}
}

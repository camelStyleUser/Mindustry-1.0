package io.anuke.moment.platform;
import com.badlogic.gdx.math.Vector2;
import io.anuke.ucore.core.UInput;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import io.anuke.moment.platform.Platform;
public class DesktopPlatform extends Platform{
public void move(Vector2 vector,float speed){
if(UInput.keyDown("up"))
			vector.y += speed;
		if(UInput.keyDown("down"))
			vector.y -= speed;
		if(UInput.keyDown("left"))
			vector.x -= speed;
		if(UInput.keyDown("right"))
			vector.x += speed;
}
public boolean rotate(){
return UInput.keyUp("rotate");
}
public boolean isSelecting(){
return UInput.buttonUp(Buttons.LEFT);
}
public boolean isBreaking(){
return UInput.buttonDown(Buttons.RIGHT);
}
public boolean isDeselecting(){
return UInput.buttonDown(Buttons.RIGHT);
}
public boolean spawn(){
return UInput.keyUp(Keys.G);
}
}

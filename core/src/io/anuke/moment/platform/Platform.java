/*left mouse-select UInput.buttonUp(Buttons.LEFT)
right mouse-deselect and break UInput.buttonDown(Buttons.RIGHT)
up,down,left,right-movement vector.set(0, 0);
		if(UInput.keyDown("up"))
			vector.y += speed;
		if(UInput.keyDown("down"))
			vector.y -= speed;
		if(UInput.keyDown("left"))
			vector.x -= speed;
		if(UInput.keyDown("right"))
			vector.x += speed;
*/
package io.anuke.moment.platform;
import com.badlogic.gdx.math.Vector2;
public abstract class Platform{
public abstract boolean isBreaking();
public abstract boolean isSelecting();
public abstract boolean isDeselecting();
public abstract boolean rotate();
public abstract boolean spawn();
public abstract void move(Vector2 vector,float speed);
public void update(){};
public void init(){};
public void graphinit(){};
}

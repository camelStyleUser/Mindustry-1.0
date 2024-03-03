package io.anuke.ucore.scene;

import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import io.anuke.ucore.core.Draw;
import io.anuke.ucore.scene.event.InputEvent;
import io.anuke.ucore.scene.event.InputListener;
import io.anuke.ucore.scene.utils.ChangeListener;
import io.anuke.ucore.scene.utils.ClickListener;

/**Extends the BaseElement (Actor) class to provide more functionality.
 * (that is probably a terrible idea)*/
public class Element extends BaseElement{
	private static final Vector2 vec = new Vector2();
	
	protected float alpha = 1f;
	private BooleanSupplier visibility;
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		validate();
		draw();
	}
	
	/**Simple drawing. Use the alpha variable if needed.*/
	public void draw(){
		
	}
	
	/**Find and draws a drawable by name on the width/height.*/
	protected void patch(String name){
		Draw.patch(name, getX(), getY(), getWidth(), getHeight());
	}
	
	/**Find and draws a drawable by name on the width/height. Padding on the sides is applied.*/
	protected void patch(String name, float padding){
		Draw.patch(name, getX() + padding, getY()+padding, getWidth()-padding*2, getHeight()-padding*2);
	}
	
	@Override
	public void act (float delta) {
		super.act(delta);
		if(visibility != null)
			setVisible(visibility.getAsBoolean());
	}
	
	public Vector2 worldPos(){
		return localToStageCoordinates(vec.set(0, 0));
	}
	
	/**Adds a keydown input listener.*/
	public void keyDown(IntConsumer cons){
		final IntConsumer consw=cons;
		addListener(new InputListener(){
			public boolean keyDown (InputEvent event, int keycode) {
				consw.accept(keycode);
				return true;
			}
		});
	}
	
	/**Adds a click listener.*/
	public ClickListener clicked(Runnable r){
		final Runnable rw=r;
		ClickListener click;
		addListener(click = new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				rw.run();
			}
		});
		return click;
	}
	
	/**Fires a change event on all listeners.*/
	public void change(){
		fire(new ChangeListener.ChangeEvent());
		//for(EventListener listener : this.getListeners()){
		//	if(listener instanceof ChangeListener)
		//		((ChangeListener)listener).changed(null, this);
		//}
	}
	
	/**Adds a click listener.*/
	public void changed(Runnable r){
		final Runnable rw=r;
		addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Element actor){
				rw.run();
			}
		});
	}
	
	public void update(Runnable r){
		final Runnable rw=r;
		addAction(new Action(){
			@Override
			public boolean act(float delta){
				rw.run();
				return false;
			}
		});
	}
	
	public void setVisible(BooleanSupplier vis){
		visibility = vis;
	}
}

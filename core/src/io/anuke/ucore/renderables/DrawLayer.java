package io.anuke.ucore.renderables;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;

public enum DrawLayer{
	shadow("shadow", Sorter.shadow){
		public void end(){
			batch.setColor(0,0,0,0.1f);
			drawFull();
		}
	}, 
	light("light", Sorter.light){
		{
			bind = 6;
		}
	};

	public final String name;
	public final float layer;
	public int bind = ordinal() + 1;
	public ShaderProgram shader;
	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	protected FrameBuffer fbo;

	private DrawLayer(String name, float layer){
		this.name = name;
		this.layer = layer;
	}

	public void end(){}

	protected void begin(){}

	public boolean layerEquals(float f){
		return MathUtils.isEqual(f, layer, 0.01f);
	}

	protected void loadShader(){

	}

	protected void drawFull(){
		batch.draw(fbo.getColorBufferTexture(), camera.position.x - camera.viewportWidth / 2 * camera.zoom, camera.position.y + camera.viewportHeight / 2 * camera.zoom, camera.viewportWidth * camera.zoom, -camera.viewportHeight * camera.zoom);
	}

	public void beginDraw(SpriteBatch batch, OrthographicCamera camera, FrameBuffer fbo){
		this.batch = batch;
		this.fbo = fbo;
		this.camera = camera;
		begin();
	}

	public static void loadShaders(){
		for(DrawLayer layer : values()){
			layer.loadShader();
		}
	}
}

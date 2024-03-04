package io.anuke.moment.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;

import io.anuke.moment.Moment;
import io.anuke.ucore.noise.Noise;

public class Generator{
	static final int black = Color.rgba8888(Color.BLACK);//000000ff
	static final int white = Color.rgba8888(Color.WHITE);//ffffffff
	static final int red = Color.rgba8888(Color.RED);//ff0000ff
	static final int blue = Color.rgba8888(Color.BLUE);//0000ffff
	static final int gray = Color.rgba8888(Color.LIGHT_GRAY);//bfbfbfff
	static final int orange = Color.rgba8888(Color.ORANGE);//ffa500ff
	public static void generate(Tile[][] tiles, String mapname){
	generate(tiles,mapname,true);
	}
	public static void generate(Tile[][] tiles, String mapname,boolean isinternal){
		Pixmap pix;
		if(isinternal){
		pix = new Pixmap(Gdx.files.internal("maps/"+mapname+".png"));
		}else{
		pix = new Pixmap(Gdx.files.absolute(mapname));
		}
		Noise.setSeed(MathUtils.random(0, 99999));
		boolean hasore=false;
		for(int x = 0; x < tiles.length; x ++){
			for(int y = 0; y < tiles.length; y ++){
			int color = pix.getPixel(x, pix.getHeight()-1-y);
			if(color==gray||color==orange){hasore=true;break;}
			}
			if(hasore)break;
		}
		for(int x = 0; x < tiles.length; x ++){
			for(int y = 0; y < tiles.length; y ++){
				TileType floor = TileType.stone;
				TileType block = TileType.air;
				
				int color = pix.getPixel(x, pix.getHeight()-1-y);
				
				if((!hasore)&&Noise.nnoise(x, y, 8, 1) > 0.22){
					floor = TileType.iron;
				}
				
				if((!hasore)&&Noise.nnoise(x, y, 6, 1) > 0.245){
					floor = TileType.coal;
				}
				
				if(color == white){
					block = TileType.dirtblock;
					
					
				}else if(color == blue){
					Moment.i.core = tiles[x][y];
				}else if(color == red){
					Moment.i.spawnpoints.add(tiles[x][y]);
				}else if(color==gray){
				floor = TileType.coal;
				}else if(color==orange){
				floor=TileType.iron;
				}
				
				tiles[x][y].setBlock(block);
				tiles[x][y].setFloor(floor);
			}
		}
		
		pix.dispose();
	}
}

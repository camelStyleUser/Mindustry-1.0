package io.anuke.moment.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;

import io.anuke.moment.Moment;
import io.anuke.ucore.noise.Noise;
/*
stone wall 8b8b8b
iron wall aaaaaa
stone drill 6b6b6b
iron drill c3a490
coal drill 272727
router 585858
smelter c9543d
heal turret b5e8a4
turret 1869a7
double turret aac7d7
machine turret c45938
conveyor up 474747
conveyor left 474748
conveyor down 474847
conveyor right 474848
*/
public class Generator{
	static final int black = Color.rgba8888(Color.BLACK);//000000ff
	static final int white = Color.rgba8888(Color.WHITE);//ffffffff
	static final int red = Color.rgba8888(Color.RED);//ff0000ff
	static final int blue = Color.rgba8888(Color.BLUE);//0000ffff
	static final int gray = Color.rgba8888(Color.LIGHT_GRAY);//bfbfbfff
	static final int orange = Color.rgba8888(Color.ORANGE);//ffa500ff
	static final int stoneWall=0x8b8b8bff;
	static final int ironWall=0xaaaaaaff;
	static final int stoneDrill=0x6b6b6bff;
	static final int ironDrill=0xc3a490ff;
	static final int coalDrill=0x272727ff;
	static final int router=0x585858ff;
	static final int smelter=0xc9543dff;
	static final int healTurret=0xb5e8a4ff;
	static final int turret=0x1869a7ff;
	static final int doubleTurret=0xaac7d7ff;
	static final int machineTurret=0xc45938ff;
	static final int convUp=0x474747ff;
	static final int convLeft=0x474748ff;
	static final int convDown=0x474847ff;
	static final int convRight=0x474848ff;
	public static void generate(Tile[][] tiles, String mapname){
	generate(tiles,mapname,true);
	}
	public static int setR(int c,int r){return (c&0x00ffffff)|(r<<24);}
	public static int setG(int c,int g){return (c&0xff00ffff)|(g<<16);}
	public static int setB(int c,int b){return (c&0xffff00ff)|(b<<8);}
	public static int setA(int c,int a){return (c&0xffffff00)|(a);}
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
				int rotation=0;
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
				}else if(color==stoneWall){
				Moment.seenBuild=true;
				block=TileType.stonewall;
				rotation = 0;
				}else if(color==ironWall){Moment.seenBuild=true;
				block=TileType.ironwall;
				rotation = 0;
				}else if(color==stoneDrill){Moment.seenBuild=true;
				block=TileType.stonedrill;
				floor=TileType.stone;
				rotation = 0;
				}else if(color==ironDrill){Moment.seenBuild=true;
				block=TileType.irondrill;
				floor=TileType.iron;
				rotation = 0;
				}else if(color==coalDrill){Moment.seenBuild=true;
				block=TileType.coaldrill;
				floor=TileType.coal;
				rotation = 0;
				}else if(color==router){Moment.seenBuild=true;
				block=TileType.router;
				rotation = 0;
				}else if(color==smelter){Moment.seenBuild=true;
				block=TileType.smelter;
				rotation = 0;
				}else if(color==healTurret){Moment.seenBuild=true;
				block=TileType.healturret;
				rotation = 0;
				}else if(color==turret){Moment.seenBuild=true;
				block=TileType.turret;
				rotation = 0;
				}else if(color==doubleTurret){Moment.seenBuild=true;
				block=TileType.doubleturret;
				rotation = 0;
				}else if(color==machineTurret){Moment.seenBuild=true;
				block=TileType.machineturret;
				rotation = 0;
				}else if(color==convUp){Moment.seenBuild=true;
				block=TileType.conveyor;
				rotation = 1;
				}else if(color==convLeft){Moment.seenBuild=true;
				block=TileType.conveyor;
				rotation = 2;
				}else if(color==convDown){Moment.seenBuild=true;
				block=TileType.conveyor;
				rotation = 3;
				}else if(color==convRight){Moment.seenBuild=true;
				block=TileType.conveyor;
				rotation = 0;
				}
				
				tiles[x][y].setBlock(block);
				tiles[x][y].setFloor(floor);
				tiles[x][y].rotation=rotation;
			}
		}
		
		pix.dispose();
	}
}

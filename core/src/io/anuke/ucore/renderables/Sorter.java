package io.anuke.ucore.renderables;

public enum Sorter{
	tile{
		public int compare(Renderable a, Renderable b){
			if(b.provider == tile){
				if(a.getLayer() == b.getLayer())
					return 0;
				return a.getLayer() < b.getLayer() ? 1 : -1;
			}else{
				return -1;
			}

		}
	},
	object{
		public int compare(Renderable a, Renderable b){
			if(b.provider == object){
				if(a.getLayer() == b.getLayer())
					return 0;
				return a.getLayer() < b.getLayer() ? 1 : -1;
			}else{
				return 1;
			}

		}
	};
	public static final float shadow = -999999;
	public static final float light = 999999;
	
	public abstract int compare(Renderable a, Renderable b);
}

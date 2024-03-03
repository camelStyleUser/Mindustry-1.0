package io.anuke.moment.android;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;
import io.anuke.ucore.core.UInput;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.anuke.moment.platform.Platform;
import io.anuke.ucore.core.Draw;
import io.anuke.ucore.core.UGraphics;
import io.anuke.ucore.modules.SceneModule;
import io.anuke.ucore.scene.builders.*;
import io.anuke.ucore.scene.style.Styles;
import io.anuke.ucore.scene.ui.*;
import io.anuke.ucore.scene.ui.layout.Table;
import io.anuke.ucore.util.Mathf;
import io.anuke.ucore.core.DrawContext;
public class AndroidPlatform extends Platform{
private final Vector3 mouseInWorld3D = new Vector3();
public static boolean breaking;
public static boolean rotate;
float lx,ly,cx,cy;
static final double dist=0.001;
public void move(Vector2 vector,float speed){
if(!Gdx.input.isTouched(1)){
return;
}
vector.set(cx-lx,cy-ly);
}
public boolean rotate(){
return rotate;

}
public boolean isSelecting(){
return (Vector2.dst(lx,ly, cx, cy))<dist&&(!breaking)&&(!Gdx.input.isTouched(1));
}
public boolean isBreaking(){
return (Vector2.dst(lx,ly, cx, cy))<dist&&(breaking)&&(!Gdx.input.isTouched(1));
}
public boolean isDeselecting(){
return this.isBreaking();
}
public void update(){
lx=cx;
ly=cy;
mouseInWorld3D.x = Gdx.input.getX();
mouseInWorld3D.y = Gdx.input.getY();
mouseInWorld3D.z = 0;
DrawContext.camera.unproject(mouseInWorld3D);
cx = mouseInWorld3D.x;
cy = mouseInWorld3D.y;
if(rotate==true){
rotate=false;
}
}
public void graphinit(){
new table(){{
abottom();
new button("rotate", new Runnable(){public void run(){
if(!AndroidPlatform.rotate){
AndroidPlatform.rotate=true;
}
}}){{
get().left();
}};
new button("break", new Runnable(){public void run(){
AndroidPlatform.breaking=(!AndroidPlatform.breaking);
}}){{
get().right();
}};
}};
}
}

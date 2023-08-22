package lazyalienserver.carpetlasaddition.render;


import net.minecraft.client.render.debug.DebugRenderer;

public class BaseRender {
    private static double MAX_RENDER_DISTANCE=256.0;

    public static double getMaxRenderDistance(){
        return MAX_RENDER_DISTANCE;
    }
    public static void setMaxRenderDistance(double MaxRenderDistance){
        MAX_RENDER_DISTANCE=MaxRenderDistance;
    }
   public static void drawString(String texts,float x,float y,float z, int colors){
       DebugRenderer.drawString(texts,x,y,z,colors,0.03F,true,0.0F,true);
   }
}

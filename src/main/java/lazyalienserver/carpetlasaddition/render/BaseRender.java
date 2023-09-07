package lazyalienserver.carpetlasaddition.render;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.brigadier.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class BaseRender {
    private static double MAX_RENDER_DISTANCE=256.0;

    public static double getMaxRenderDistance(){
        return MAX_RENDER_DISTANCE;
    }

    public static int setMaxRenderDistance(Integer MaxRenderDistance){
        if(MaxRenderDistance>=0) {
            MAX_RENDER_DISTANCE = MaxRenderDistance;
            return Command.SINGLE_SUCCESS;
        }
        else {
            return 0;
        }
    }
   public static void drawString(String texts,float x,float y,float z, int colors,boolean visibleThroughObjects){
       DebugRenderer.drawString(texts,x,y,z,colors,0.03F,true,0.0F,visibleThroughObjects);
   }
   public static void drawBox(Box box,float red,float green,float blue,float alpha){
        DebugRenderer.drawBox(box,red,green,blue,alpha);
   }
   public static void drawBox(BlockPos blockPos1,BlockPos blockPos2,float red,float green,float blue,float alpha,boolean visibleThroughObjects){
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enablePolygonOffset();
        if (visibleThroughObjects) {
           RenderSystem.disableDepthTest();
        } else {
           RenderSystem.enableDepthTest();
        }
        DebugRenderer.drawBox(blockPos1,blockPos2,red,green,blue,alpha);

        RenderSystem.enableDepthTest();
        RenderSystem.disablePolygonOffset();
        RenderSystem.depthMask(true);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
   }

    public static void drawBoxWithLine(BlockPos blockPos1,BlockPos blockPos2,float red,float green,float blue,float alpha,boolean visibleThroughObjects){
        Vec3d vec3d1=new Vec3d(blockPos1.getX(),blockPos1.getY(),blockPos1.getZ());
        Vec3d vec3d2=new Vec3d(blockPos2.getX(),blockPos2.getY(),blockPos2.getZ());



        drawLine(vec3d1,vec3d2,red,green,blue,1,5);
        //drawBox(blockPos1,blockPos2,red,green,blue,alpha,visibleThroughObjects);

    }

    public static void drawLine(Vec3d pos1,Vec3d pos2,float red, float green, float blue, float alpha,float width){
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.lineWidth(width);

        drawLines(pos1,pos2,red,green,blue,alpha);

        RenderSystem.lineWidth(1);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    private static void drawLines(Vec3d pos1,Vec3d pos2,float red, float green, float blue, float alpha){
        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();

        if (!camera.isReady()) {
            return;
        }
        Vec3d vec3d = camera.getPos().negate();
        Box box=new Box(pos1,pos2).offset(vec3d);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(box.minX,box.minY,box.minY).color(red,green,blue,alpha).next();
        bufferBuilder.vertex(box.minX,box.minY,box.minY).color(red,green,blue,alpha).next();
        bufferBuilder.vertex(box.maxX,box.maxY,box.maxZ).color(red,green,blue,alpha).next();
        bufferBuilder.vertex(box.maxX,box.maxY,box.maxZ).color(red,green,blue,alpha).next();

        tessellator.draw();
    }

}

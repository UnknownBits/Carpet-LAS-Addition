package lazyalienserver.carpetlasaddition.render;


import com.mojang.brigadier.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

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
   public static void drawString(MatrixStack matrices,String texts, float x, float y, float z, int colors){
       //DebugRenderer.drawString(texts,x,y,z,colors,0.03F,true,0.0F,true);
       DebugRenderer.drawString(matrices, VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer()),texts,x,y,z,colors,0.03F,true,0.0F,true);
   }
}

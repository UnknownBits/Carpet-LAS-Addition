package lazyalienserver.carpetlasaddition.render;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.brigadier.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.AffineTransformation;
import net.minecraft.util.math.BlockPos;
import org.joml.Matrix3fc;
import org.joml.Matrix4f;

public class BaseRender {
    private static double MAX_RENDER_DISTANCE=256.0;

    private static final float FONT_SIZE=0.025F;

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
   public static void drawString(MatrixStack matrices, String texts, BlockPos pos, int colors){
       //drawString(texts,x,y,z,colors,0.03F,true,0.0F,true);
       drawString(matrices,pos,0.03F,new String[]{texts},new int[]{colors});
       //DebugRenderer.drawString(matrices,VertexConsumerProvider.immediate(new BufferBuilder(0x200000)),texts,x,y,z,colors,0.03F,true,0.0F,true);
   }

   /**
    *  <p>感谢Fallen_Breath的Pistorder</p>
    *  <p>qwq还是我太菜啦呜呜</p>
    * {@see 具体详见 <a href="https://github.com/Fallen-Breath/pistorder/blob/1.20-fabric/src/main/java/me/fallenbreath/pistorder/impl/PistorderDisplay.java#L205">...</a>}
    * */
    private static void drawString(MatrixStack matrixStack, BlockPos pos, float line, String[] texts, int[] colors)
    {
        MinecraftClient client = MinecraftClient.getInstance();
        Camera camera = client.gameRenderer.getCamera();
        if (camera.isReady() && client.getEntityRenderDispatcher().gameOptions != null && client.player != null)
        {
            double x = (double)pos.getX() + 0.5D;
            double y = (double)pos.getY() + 0.5D;
            double z = (double)pos.getZ() + 0.5D;
            if (client.player.squaredDistanceTo(x, y, z) > MAX_RENDER_DISTANCE * MAX_RENDER_DISTANCE)
            {
                return;
            }
            double camX = camera.getPos().x;
            double camY = camera.getPos().y;
            double camZ = camera.getPos().z;
            matrixStack.push();
            matrixStack.translate((float)(x - camX), (float)(y - camY), (float)(z - camZ));
            matrixStack.multiplyPositionMatrix(new Matrix4f().rotation(camera.getRotation()));
            matrixStack.scale(-FONT_SIZE, -FONT_SIZE, 1);
            RenderSystem.disableDepthTest();  // visibleThroughObjects

            float totalWidth = 0.0F;
            for (String text: texts)
            {
                totalWidth += client.textRenderer.getWidth(text);
            }

            float writtenWidth = 0.0F;
            for (int i = 0; i < texts.length; i++)
            {
                float renderX = -totalWidth * 0.5F + writtenWidth;
                float renderY = client.textRenderer.getWrappedLinesHeight(texts[i], Integer.MAX_VALUE) * (-0.5F + 1.25F * line);

                VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
                client.textRenderer.draw(texts[i], renderX, renderY, colors[i], false, matrixStack.peek().getPositionMatrix(), immediate, TextRenderer.TextLayerType.SEE_THROUGH, 0, 0xF000F0);
                immediate.draw();

                writtenWidth += client.textRenderer.getWidth(texts[i]);
            }

            RenderSystem.enableDepthTest();
            matrixStack.pop();
        }
    }
}

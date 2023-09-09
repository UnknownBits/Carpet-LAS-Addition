package lazyalienserver.carpetlasaddition.render;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.brigadier.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.AffineTransformation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
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
   public static void drawString(MatrixStack matrices, String texts, BlockPos pos, int colors) {
       //drawString(texts,x,y,z,colors,0.03F,true,0.0F,true);
       drawString(matrices, pos, 0.03F, new String[]{texts}, new int[]{colors});
       //DebugRenderer.drawString(matrices,VertexConsumerProvider.immediate(new BufferBuilder(0x200000)),texts,x,y,z,colors,0.03F,true,0.0F,true);
   }
   public static void drawBox(Box box, float red, float green, float blue, float alpha){
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

    public static void drawBoxWithLine(BlockPos blockPos1,BlockPos blockPos2,float red,float green,float blue,float alpha,boolean visibleThroughObjects){
        Vec3d vec3d1=new Vec3d(blockPos1.getX(),blockPos1.getY(),blockPos1.getZ());
        Vec3d vec3d2=new Vec3d(blockPos2.getX(),blockPos2.getY(),blockPos2.getZ());

        //drawLine(vec3d1,vec3d2,red,green,blue,1,5);
        drawBox(blockPos1,blockPos2,red,green,blue,alpha,visibleThroughObjects);

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

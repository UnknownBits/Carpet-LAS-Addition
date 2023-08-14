package lazyalienserver.carpetlasaddition.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRender {
    public static Map<World,Box> drawBoxList=new HashMap<>();
    public static final List<Vec3d> RenderLineList=new ArrayList<>();

    public static void RenderLine(){
        for (Vec3d pos:RenderLineList){
            RenderLine(pos,MinecraftClient.getInstance().gameRenderer.getCamera(), 3);
        }
    }
    public static void RenderLine(Vec3d pos,Camera camera,int length){


            double d = camera.getPos().getX();
            double e = camera.getPos().getY();
            double f = camera.getPos().getZ();
            RenderSystem.depthMask(true);
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableTexture();

            MatrixStack matrixStack = RenderSystem.getModelViewStack();
            matrixStack.push();
            matrixStack.translate((double) pos.x-d, (double)pos.y-e, (double)pos.z-f);
            RenderSystem.applyModelViewMatrix();
            RenderSystem.setShader(GameRenderer::getRenderTypeLinesShader);

            BufferBuilder bufferBuilder=Tessellator.getInstance().getBuffer();
            bufferBuilder.begin(VertexFormat.DrawMode.LINES,VertexFormats.POSITION_COLOR);
            RenderSystem.lineWidth(5.0f);

            bufferBuilder.vertex(length,length,length).color(255,255,255,255).next();
            bufferBuilder.vertex(length,length*2,length).color(25,255,255,255).next();
            Tessellator.getInstance().draw();

            RenderSystem.lineWidth(1.0f);
            matrixStack.pop();
            RenderSystem.applyModelViewMatrix();
            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();
            RenderSystem.enableCull();
            RenderSystem.enableTexture();
        //bufferBuilder.vertex(8.0, 8.0, 8.0).color(255,255,255, 255).normal().next();

    }

    public static void drawOutlinedBox(){
        for(Map.Entry<World,Box> worldBoxEntry:drawBoxList.entrySet()){
            if (worldBoxEntry.getKey() != null&&worldBoxEntry.getValue()!=null) {
                //MinecraftClient.getInstance().player.sendMessage(Text.of("Key:"+worldBoxEntry.getKey()+",Value:"+worldBoxEntry.getValue()),true);
                drawOutlinedBox(worldBoxEntry.getValue(),new VertexBuffer());
            }
        }
    }
    public static void drawOutlinedBox(Box bb, VertexBuffer vertexBuffer)
    {
        RenderSystem.lineWidth(5.0f);
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINES,
                VertexFormats.POSITION);
        drawOutlinedBox(bb, bufferBuilder);
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.lineWidth(1.0f);
        //vertexBuffer.upload(bufferBuilder);

    }

    public static void drawOutlinedBox(Box bb, BufferBuilder bufferBuilder)
    {
        //bufferBuilder.color(255,255,255,255);
        bufferBuilder.vertex(bb.minX, bb.minY, bb.minZ).next();
        bufferBuilder.vertex(bb.maxX, bb.minY, bb.minZ).next();

        bufferBuilder.vertex(bb.maxX, bb.minY, bb.minZ).next();
        bufferBuilder.vertex(bb.maxX, bb.minY, bb.maxZ).next();

        bufferBuilder.vertex(bb.maxX, bb.minY, bb.maxZ).next();
        bufferBuilder.vertex(bb.minX, bb.minY, bb.maxZ).next();

        bufferBuilder.vertex(bb.minX, bb.minY, bb.maxZ).next();
        bufferBuilder.vertex(bb.minX, bb.minY, bb.minZ).next();

        bufferBuilder.vertex(bb.minX, bb.minY, bb.minZ).next();
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.minZ).next();

        bufferBuilder.vertex(bb.maxX, bb.minY, bb.minZ).next();
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.minZ).next();

        bufferBuilder.vertex(bb.maxX, bb.minY, bb.maxZ).next();
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.maxZ).next();

        bufferBuilder.vertex(bb.minX, bb.minY, bb.maxZ).next();
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.maxZ).next();

        bufferBuilder.vertex(bb.minX, bb.maxY, bb.minZ).next();
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.minZ).next();

        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.minZ).next();
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.maxZ).next();

        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.maxZ).next();
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.maxZ).next();

        bufferBuilder.vertex(bb.minX, bb.maxY, bb.maxZ).next();
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.minZ).next();
    }
}

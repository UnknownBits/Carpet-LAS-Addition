package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger.BlockUpdateLogger;
import lazyalienserver.carpetlasaddition.logging.Loggers.HopperCoolTimeLogger.HopperCoolTimeLogger;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(at=@At("RETURN"),method = "render")
    public void render(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f positionMatrix, CallbackInfo ci){

        /*MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.push();
        matrixStack.multiplyPositionMatrix(matrices.peek().getPositionMatrix());
        RenderSystem.applyModelViewMatrix();*/

        WorldRendererMixin.render(matrices);

        /*RenderSystem.applyModelViewMatrix();
        //WorldRendererMixin.Test(matrices);
        matrixStack.pop();
        RenderSystem.applyModelViewMatrix();*/
    }

    @Unique
    private static void render(MatrixStack matrices){
        HopperCoolTimeLogger.RenderHopperCoolTime(matrices);
        BlockUpdateLogger.RenderBlockUpDate(matrices);
    }
    @Unique
    private static void Test(MatrixStack matrices){
        Camera camera= MinecraftClient.getInstance().gameRenderer.getCamera();
        MinecraftClient.getInstance().debugRenderer.chunkBorderDebugRenderer.render(matrices,null,camera.getPos().x,camera.getPos().y,camera.getPos().z);
        //VertexConsumerProvider.Immediate immediate = this.bufferBuilders.getEntityVertexConsumers();
        //VertexConsumer vertexConsumer3 = immediate.getBuffer(RenderLayer.getLines());
    }
}


package lazyalienserver.carpetlasaddition;

import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.commands.Client.*;
import lazyalienserver.carpetlasaddition.commands.TestCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class CarpetLASClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerCommand(ClientCommandManager.DISPATCHER);
    }


    public static void registerCommand(CommandDispatcher<FabricClientCommandSource> dispatcher){
        //LAS
        LazyAlienServerCommand.register(dispatcher);

        //Calc
        BinaryCommand.register(dispatcher);
        DecimalCommand.register(dispatcher);
        CalculateCommand.register(dispatcher);

        //Setting
        SetRenderDistanceCommand.register(dispatcher);
        CalcPearlCommand.register(dispatcher);

        //Test
        TestCommand.register(dispatcher);
    }

}

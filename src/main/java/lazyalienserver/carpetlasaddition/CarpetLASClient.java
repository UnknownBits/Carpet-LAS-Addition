package lazyalienserver.carpetlasaddition;

import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.commands.Client.*;
import lazyalienserver.carpetlasaddition.commands.TestCommand;
import lazyalienserver.carpetlasaddition.network.ClientNetworkHandler;
import lazyalienserver.carpetlasaddition.utils.CarpetLASAdditionTranslations;
import lazyalienserver.carpetlasaddition.utils.LASResource;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

@Environment(EnvType.CLIENT)
public class CarpetLASClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //TestCommand.register(ClientCommandManager.DISPATCHER);
        if (ClientCommandManager.getActiveDispatcher() != null) {
            registerCommand(ClientCommandManager.getActiveDispatcher());
            SetRenderDistanceCommand.register(ClientCommandManager.getActiveDispatcher());
        }
    }

    public static void ClientNetwork(){
        ClientNetworkHandler.loadServer();
    }
    private static void loadLASResource(){
        LASResource.LASTranslationsResource = CarpetLASAdditionTranslations.getLASResource(LASResource.lang);
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

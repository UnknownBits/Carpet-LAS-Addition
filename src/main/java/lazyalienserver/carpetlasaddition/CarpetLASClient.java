package lazyalienserver.carpetlasaddition;

import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.commands.Client.BinaryCommand;
import lazyalienserver.carpetlasaddition.commands.Client.CalculateCommand;
import lazyalienserver.carpetlasaddition.commands.Client.CalcPearlCommand;
import lazyalienserver.carpetlasaddition.commands.Client.SetRenderDistanceCommand;
import lazyalienserver.carpetlasaddition.commands.Client.DecimalCommand;
import lazyalienserver.carpetlasaddition.commands.TestCommand;
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

    public static void registerCommand(CommandDispatcher<FabricClientCommandSource> dispatcher){
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

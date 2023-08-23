package lazyalienserver.carpetlasaddition;

import lazyalienserver.carpetlasaddition.commands.Client.SetRenderDistanceCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;

@Environment(EnvType.CLIENT)
public class CarpetLASClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //TestCommand.register(ClientCommandManager.DISPATCHER);
        SetRenderDistanceCommand.register(ClientCommandManager.DISPATCHER);
    }
}

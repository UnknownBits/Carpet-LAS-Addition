package lazyalienserver.carpetlasaddition;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class CarpetLASClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //TestCommand.register(ClientCommandManager.DISPATCHER);
    }
}

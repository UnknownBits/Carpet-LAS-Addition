package lazyalienserver.carpetlasaddition.commands.Client;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

public class SetRenderDistanceCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher){
        //dispatcher.register(literal("setRenderDistance").executes(c-> BaseRender.setMaxRenderDistance()));
    }
}

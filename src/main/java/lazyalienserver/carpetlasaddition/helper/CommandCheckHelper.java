package lazyalienserver.carpetlasaddition.helper;

import lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger.BlockUpdateLogger;
import lazyalienserver.carpetlasaddition.logging.Loggers.HopperCoolTimeLogger.HopperCoolTimeLogger;
import lazyalienserver.carpetlasaddition.logging.Loggers.RenderLogger;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;

public class CommandCheckHelper {
    private static final HashMap<String,CommandSourceInterface> COMMANDS = new HashMap<>();
    public static void checkCommand(String command, ServerPlayerEntity player) {
        for (Map.Entry<String, CommandSourceInterface> commands : COMMANDS.entrySet()){
            if (command.equals(commands.getKey())){
                commands.getValue().run(player);
            }
        }
    }

    public static void registerCheckCommand(String command, CommandSourceInterface runnable){
        COMMANDS.put(command, runnable);
    }

    static {
        CommandCheckHelper.registerCheckCommand("/log blockUpdate", BlockUpdateLogger::clearClient);
        CommandCheckHelper.registerCheckCommand("/log hopperCoolTime", HopperCoolTimeLogger::clearClient);
        CommandCheckHelper.registerCheckCommand("/log blockUpdate clear", BlockUpdateLogger::clearClient);
        CommandCheckHelper.registerCheckCommand("/log hopperCoolTime clear", HopperCoolTimeLogger::clearClient);
        CommandCheckHelper.registerCheckCommand("/log clear", RenderLogger::clearClientLoggers);
    }
}

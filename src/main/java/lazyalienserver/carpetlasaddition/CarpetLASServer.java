package lazyalienserver.carpetlasaddition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.logging.LoggerRegistry;
import lazyalienserver.carpetlasaddition.utils.CarpetLASAdditionTranslations;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class CarpetLASServer implements ModInitializer,CarpetExtension {
    @Override
    public String version(){
        return "1.1.0";
    }
    public static final String MOD_NAME="Carpet-LAS-Addition";
    public String modID(){
        return "carpetlasaddition";
    }
    public static void loadExtension(){
        CarpetServer.manageExtension(new CarpetLASServer());
    }
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    @Override
    public void onInitialize() {
        CarpetLASServer.loadExtension();
    }

    @Override
    public void registerLoggers(){
        LoggerRegistry.registerLoggers();
    }

    @Override
    public void onGameStarted(){
        CarpetServer.settingsManager.parseSettingsClass(CarpetLASSetting.class);
    }
    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher)
    {
    //Commands
    }
    @Override
    public Map<String, String> canHasTranslations(String lang)
    {
        return CarpetLASAdditionTranslations.getTranslationFromResourcePath(lang);
    }


}

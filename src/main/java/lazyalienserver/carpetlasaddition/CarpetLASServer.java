package lazyalienserver.carpetlasaddition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.utils.CarpetLASAdditionTranslations;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Map;

public class CarpetLASServer implements CarpetExtension, ModInitializer {
    @Override
    public String version(){
        return "Carpet-LAS-Addition";
    }
    public String modID(){
        return "carpetlasaddition";
    }
    public static void loadExtension(){
        CarpetServer.manageExtension(new CarpetLASServer());
    }
    @Override
    public void onInitialize() {
        CarpetLASServer.loadExtension();
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

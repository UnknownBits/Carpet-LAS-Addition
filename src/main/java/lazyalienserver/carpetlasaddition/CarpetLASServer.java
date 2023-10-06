package lazyalienserver.carpetlasaddition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.commands.Server.LazyAlienServerCommand;
import lazyalienserver.carpetlasaddition.logging.LoggerRegistry;
import lazyalienserver.carpetlasaddition.network.ServerNetworkHandler;
import lazyalienserver.carpetlasaddition.records.Record;
import lazyalienserver.carpetlasaddition.records.RecordList;
import lazyalienserver.carpetlasaddition.utils.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Map;

public class CarpetLASServer implements ModInitializer,CarpetExtension {

    public static final String MOD_NAME="Carpet-LAS-Addition";
    @Override
    public String version(){
        return "1.2.0";
    }
    public String modID(){
        return "carpetlasaddition";
    }
    public static void loadExtension(){
        CarpetServer.manageExtension(new CarpetLASServer());
    }

    @Override
    public void onInitialize() {
        CarpetLASServer.ServerNetWork();
        CarpetLASServer.loadExtension();
        CommandRegistrationCallback.EVENT.register(CarpetLASServer::registerLASCommands);
    }

    public static void ServerNetWork(){
        ServerNetworkHandler.loadServer();
    }

    @Override
    public void registerLoggers(){
        LoggerRegistry.registerLoggers();
    }

    public static void registerLASCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean b) {
        LazyAlienServerCommand.register(dispatcher);
    }

    @Override
    public void onGameStarted(){
        LASLogUtils.log("Carpet-LAS-Addition loaded.");
        CarpetServer.settingsManager.parseSettingsClass(CarpetLASSetting.class);
    }
    @Override
    public void onServerLoaded(MinecraftServer server){
        DateTimeUtils.initDayScheduleEvent();
        Record.registerRecords();
        createdir();
        LASResource.loadLASResource();
    }

    @Override
    public void onServerClosed(MinecraftServer server){
        RecordList.saveAllRecord();
    }

    private void createdir(){
        for (Map.Entry<String,Record> record: RecordList.recordList.entrySet()){
            FileUtils.createDir(record.getValue().getRecordsDir());
        }
    }
    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher)
    {
    //Commands
    }
    @Override
    public Map<String, String> canHasTranslations(String lang)
    {
        return CarpetLASAdditionTranslations.getCarpetResource(lang);
    }
    @Override
    public void onTick(MinecraftServer server){

    }
}

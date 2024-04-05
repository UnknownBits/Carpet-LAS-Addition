package lazyalienserver.carpetlasaddition.commands.Client;

import carpet.utils.Messenger;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import lazyalienserver.carpetlasaddition.utils.FileUtils;
import lazyalienserver.carpetlasaddition.utils.LASResource;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.util.ArrayList;

import static lazyalienserver.carpetlasaddition.commands.Server.LazyAlienServerCommand.CLALog;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class LazyAlienServerCommand {
    public static ArrayList<Text> info=new ArrayList<>();

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("LAS").executes(LazyAlienServerCommand::LASInfo)
                .then(literal("lang").executes(LazyAlienServerCommand::LASlang))
                .then(literal("about").executes(LazyAlienServerCommand::aboutMod))
                .then(literal("log").executes(LazyAlienServerCommand::setLog)));
        registerLang(dispatcher);
    }

    private static void registerLang(CommandDispatcher<FabricClientCommandSource> dispatcher){
        for (String file: FileUtils.getResourceFiles("assets/carpet-las-addition/LAS/lang")){
            String lang=file.split(".json$")[0];
            dispatcher.register(literal("LAS").then(literal("lang").then(literal(lang).executes(c->setLASlang(lang)))));
        }
    }

    private static Integer LASInfo(CommandContext<FabricClientCommandSource> context){
        info.clear();
        MutableText title= (MutableText) Messenger.s(LASResource.getLASTranslationsResource("LAS.info.title"));
        title.setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://lazyalienserver.top/")).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,Text.of(LASResource.getLASTranslationsResource("LAS.info.website")))).withColor(Formatting.BLUE).withBold(true));
        info.add(title);
        info.add(Messenger.s(LASResource.getLASTranslationsResource("LAS.info.base")));
        info.add(Messenger.s(LASResource.getLASTranslationsResource("LAS.info.purpose"),"b"));

        Messenger.send(context.getSource().getPlayer(),info);

        return Command.SINGLE_SUCCESS;
    }

    private static Integer LASlang(CommandContext<FabricClientCommandSource> context){
        info.clear();
        info.add(Messenger.s("lang:","b"));
        info.add(Messenger.s(LASResource.lang,"ibc"));
        Messenger.send(context.getSource().getPlayer(),info);
        return Command.SINGLE_SUCCESS;
    }

    private static Integer setLASlang(String lang){
        LASResource.lang=lang;
        LASResource.reloadLASLang();
        return Command.SINGLE_SUCCESS;
    }

    private static Integer aboutMod(CommandContext<FabricClientCommandSource> context){
        info.clear();
        MutableText baseText= (MutableText) Messenger.s(LASResource.getLASTranslationsResource("CLA.info.about"));
        baseText.setStyle(Style.EMPTY.withBold(true).withColor(Formatting.LIGHT_PURPLE).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,Text.of("github"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://github.com/UnknownBits/Carpet-LAS-Addition")));
        info.add(baseText);
        info.add(Messenger.s(LASResource.getLASTranslationsResource("CLA.info.author")));
        info.add(Messenger.s(LASResource.getLASTranslationsResource("CLA.info.helper")));
        info.add(Messenger.s(LASResource.getLASTranslationsResource("CLA.info.icon_helper")));
        Messenger.send(context.getSource().getPlayer(),info);
        return Command.SINGLE_SUCCESS;
    }

    private static Integer setLog(CommandContext<FabricClientCommandSource> context){
        CLALog=!CLALog;
        info.clear();
        info.add(Messenger.s(CLALog ?LASResource.getLASTranslationsResource("CLA.setting.LogEnabled"):LASResource.getLASTranslationsResource("CLA.setting.LogDisabled")));
        Messenger.send(context.getSource().getPlayer(),info);
        return Command.SINGLE_SUCCESS;
    }
}

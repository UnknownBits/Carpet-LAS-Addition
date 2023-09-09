package lazyalienserver.carpetlasaddition.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
@Environment(EnvType.CLIENT)
public class CalcPearlScreen extends Screen {
    public static Text CalcPearlScreen=Text.of("CalcPearlScreen");
    public CalcPearlScreen() {
        super(CalcPearlScreen);
    }
    @Override
    protected void init() {
        if (MinecraftClient.getInstance().currentScreen != null) {
            Log.log(LogLevel.DEBUG, LogCategory.LOG,"init");
            addDrawableChild(new ButtonWidget(MinecraftClient.getInstance().currentScreen.width/2,MinecraftClient.getInstance().currentScreen.height/2,MinecraftClient.getInstance().currentScreen.width/2,MinecraftClient.getInstance().currentScreen.height/2,Text.of("exit"),p->this.close()));
        }
        Log.log(LogLevel.DEBUG, LogCategory.LOG,"null");
        super.init();
    }


}

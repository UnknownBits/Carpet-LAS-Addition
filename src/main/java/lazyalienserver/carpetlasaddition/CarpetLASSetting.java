package lazyalienserver.carpetlasaddition;

import carpet.settings.Rule;

public class CarpetLASSetting {
    public static final String LASAddition="LAS";
    @Rule(desc = "EnderPearlChunkLoading", category = {LASAddition})
    public static boolean enderPearlChunkLoading = false;

    @Rule(desc = "NC更新24模式的被充能音符盒加载4gt区块[强:3*3,弱:5*5,边界7*7]",category = {LASAddition})
    public static boolean NC_noteBlockLoadChunk=false;
}

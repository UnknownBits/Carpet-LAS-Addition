package lazyalienserver.carpetlasaddition;

import carpet.settings.Rule;

public class CarpetLASSetting {
    public static final String LASAddition="LAS";
    @Rule(desc = "EnderPearlChunkLoading", category = {LASAddition})
    public static boolean enderPearlChunkLoading = false;

    @Rule(desc = "PillagerAliveTime",category = {LASAddition})
    public static boolean PillagerAliveTime=false;
}

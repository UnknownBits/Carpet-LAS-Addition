package lazyalienserver.carpetlasaddition;

import carpet.settings.Rule;

public class CarpetLASSetting {
    public static final String LASAddition="LAS";
    @Rule(desc = "Allow horizontally moving ender pearls to force chunk loading", category = {LASAddition})
    public static boolean enderPearlChunkLoading = false;
    @Rule(desc = "Above the charge there is a 24-stop note box with an blue orchids pot loads 300gt Chunk [Strong: 3*3, Weak: 5*5, Boundary 7*7]",category = {LASAddition})
    public static boolean NoteBlockLoadChunk =false;
    @Rule(desc = "Any item placed in the bin will add one level to the bin [you need to right-click the bin once after each setting change to apply the current setting]",category = {LASAddition})
    public static boolean CompostGradeModification=false;
    @Rule(desc = "Replace the minecart drop mechanism with version 1.18.2 with the drop mechanism in version 1.19",category = {LASAddition})
    public static boolean MinecartDropModify =false;


}
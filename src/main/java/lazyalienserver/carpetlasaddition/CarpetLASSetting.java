package lazyalienserver.carpetlasaddition;

import carpet.settings.Rule;

import static lazyalienserver.carpetlasaddition.settings.LASAdditionRuleCategory.*;

public class CarpetLASSetting {

    @Rule(
            desc = "Allow horizontally moving ender pearls to force chunk loading", category = {LASAddition,BUGFIX}
    )
    public static boolean enderPearlChunkLoading = false;
    @Rule(
            desc = "Above the charge there is a 24-stop note box with an blue orchids pot loads 300gt Chunk [Strong: 3*3, Weak: 5*5, Boundary 7*7]",category = {LASAddition,SURVIVAL,CREATIVE}
    )
    public static boolean NoteBlockLoadChunk =false;
    @Rule(
            desc = "Any item placed in the bin will add one level to the bin [you need to right-click the bin once after each setting change to apply the current setting]",category = {LASAddition,CREATIVE}
    )
    public static boolean CompostGradeModification=false;
    @Rule(
            desc = "Replace the minecart drop mechanism with version 1.18.2 with the drop mechanism in version 1.19",category = {LASAddition,SURVIVAL,BUGFIX}
    )
    public static boolean MinecartDropModify =false;

    @Rule(
            desc = "A Calculate Command in minecraft",category = {LASAddition,LASAdditionCommand,COMMAND}
    )
    public static String commandCalculate = "true";

    @Rule(
            desc = "A Command,it can Decimal to Binary",category = {LASAddition,LASAdditionCommand,COMMAND}
    )
    public static String commandBinary = "true";

    @Rule(
            desc = "A Command,it can Binary to Decimal",category = {LASAddition,LASAdditionCommand,COMMAND}
    )
    public static String commandDecimal = "true";


}
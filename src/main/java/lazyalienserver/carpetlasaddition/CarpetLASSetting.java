package lazyalienserver.carpetlasaddition;

import carpet.settings.Rule;

public class CarpetLASSetting {
    public static final String LASAddition="LAS";
    @Rule(desc = "Allow horizontally moving ender pearls to force chunk loading", category = {LASAddition})
    public static boolean enderPearlChunkLoading = false;
    @Rule(desc = "NC Update 24 mode charged note box loads 4gt Chunk [Strong: 3*3, Weak: 5*5, Boundary 7*7]",category = {LASAddition})
    public static boolean NC_noteBlockLoadChunk=false;
    @Rule(desc = "Charge 24 mode note box loading blocks",category = {LASAddition})
    public static boolean NoteBlockLoadChunk=false;
    @Rule(desc = "Any item placed in the bin will add one level to the bin [you need to right-click the bin once after each setting change to apply the current setting]",category = {LASAddition})
    public static boolean CompostGradeModification=false;



}

package lazyalienserver.carpetlasaddition;

import carpet.settings.Rule;

public class CarpetLASSetting {
    public static final String LASAddition="LAS";
    @Rule(desc = "Allow horizontally moving ender pearls to force chunk loading",extra = {"让水平移动的末影珍珠可以强加载区块"}, category = {LASAddition})
    public static boolean enderPearlChunkLoading = false;

    @Rule(desc = "NC Update 24 mode charged note box loads 4gt Chunk [Strong: 3*3, Weak: 5*5, Boundary 7*7]",extra = {"NC更新24模式的被充能音符盒加载4gt区块[强:3*3,弱:5*5,边界7*7]"},category = {LASAddition})
    public static boolean NC_noteBlockLoadChunk=false;
    @Rule(desc = "Any item placed in the bin will add one level to the bin [you need to right-click the bin once after each setting change to apply the current setting]",extra = {"任何放入堆肥桶的物品,都将使堆肥桶增加一级[每次更改设置后需要右键一次堆肥桶才能应用当前设置]"},category = {LASAddition})
    public static boolean CompostGradeModification=false;

}

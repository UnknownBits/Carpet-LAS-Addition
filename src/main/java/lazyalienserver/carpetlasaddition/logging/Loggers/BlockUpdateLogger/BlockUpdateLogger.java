package lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger;

import carpet.CarpetServer;
import lazyalienserver.carpetlasaddition.logging.LoggerRegistry;
import lazyalienserver.carpetlasaddition.logging.Loggers.RenderLogger;
import lazyalienserver.carpetlasaddition.network.NetWorkPacket;
import lazyalienserver.carpetlasaddition.render.BaseRender;
import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlockUpdateLogger {

    public static final String ID = "blockUpdate";

    private static final Map<Pair<Identifier, BlockPos>, UpdateType> BlockUpdate = new ConcurrentHashMap<>();

    public static void addBlockUpdate(Identifier world, BlockPos pos, UpdateType type) {
        if (LoggerRegistry.__blockUpdate) {
            if(BlockUpdate.get(new Pair<>(world, pos)) != type){
                LASLogUtils.log("addBlockUpdate");
                if(BlockUpdate.get(new Pair<>(world, pos))!=null){
                    BlockUpdate.put(new Pair<>(world, pos),UpdateType.NC_And_PP);
                }else BlockUpdate.put(new Pair<>(world, pos),type);
            }
        }
    }

    public static void sendBlockUpdate() {
        //TODO 实际渲染有偏差，可能与锂钠有关系
        for (Map.Entry<String, String> Player : RenderLogger.getRenderLogger("blockUpdate").getSubscribedOnlinePlayers().entrySet()) {
            ServerPlayerEntity player = CarpetServer.minecraft_server.getPlayerManager().getPlayer(Player.getKey());
            if (player != null) {
                for (Map.Entry<Pair<Identifier, BlockPos>, UpdateType> blockUpdate : BlockUpdate.entrySet()) {
                    if (blockUpdate.getKey().getLeft().equals(player.getWorld().getRegistryKey().getValue())) {
                        BlockPos pos = blockUpdate.getKey().getRight();
                        int x, y, z;
                        x = pos.getX();
                        y = pos.getY();
                        z = pos.getZ();
                        if (player.squaredDistanceTo(x, y, z) <= BaseRender.getMaxRenderDistance() * BaseRender.getMaxRenderDistance()) {
                            NetWorkPacket.serverSend(player, NetWorkPacket.BlockUpdate, NetWorkPacket.createStringPacket(pos.toShortString() + ", " + blockUpdate.getValue().s));
                        }
                    }
                }
            }
        }
        BlockUpdate.clear();
    }

    public static void clearClient(ServerPlayerEntity player){
        NetWorkPacket.serverSend(player, NetWorkPacket.ClearRender, NetWorkPacket.createStringPacket(ID));
    }

    public static void clearAll(){
        for (Map.Entry<String, String> Player : RenderLogger.getRenderLogger("blockUpdate").getSubscribedOnlinePlayers().entrySet()) {
            ServerPlayerEntity player = CarpetServer.minecraft_server.getPlayerManager().getPlayer(Player.getKey());
            if (player!= null) {
                NetWorkPacket.serverSend(player, NetWorkPacket.ClearRender, NetWorkPacket.createStringPacket(ID));
            }
        }
    }
//    private static final Map<BlockPos, UpdateType> BlockUpdateMap = new ConcurrentHashMap<>();
//    private static final Logger log=carpet.logging.LoggerRegistry.getLogger("blockUpdate");
//
//    private static int option=0;
//
//    private static boolean isReturn=true;
//
//    private static void setIsReturn(boolean Return){
//        isReturn = Return;
//    }
//
//    private static void setOption(int option1){
//        option=option1;
//    }
//
//
//    public static void RenderBlockUpDate(){
//        if (LoggerRegistry.__blockUpdate) {
//            for (Map.Entry<BlockPos, UpdateType> entry : BlockUpdateMap.entrySet()) {
//                if (entry.getValue() == UpdateType.NC) {
//                    BaseRender.drawString("NC", entry.getKey().getX() + 0.5F, entry.getKey().getY() + 0.5F, entry.getKey().getZ() + 0.5F, Formatting.DARK_AQUA.getColorValue(), true,0.025F);
//                    BaseRender.drawBoxWithLine(entry.getKey(), entry.getKey().add(1, 1, 1), 1, 0, 0, 0.1F, true);
//                } else if (entry.getValue() == UpdateType.PP) {
//                    BaseRender.drawString("PP", entry.getKey().getX() + 0.5F, entry.getKey().getY() + 0.5F, entry.getKey().getZ() + 0.5F, Formatting.DARK_AQUA.getColorValue(), true,0.025F);
//                    BaseRender.drawBoxWithLine(entry.getKey(), entry.getKey().add(1, 1, 1), 1, 1, 0, 0.1F, true);
//                } else {
//                    BaseRender.drawString("NC&PP", entry.getKey().getX() + 0.5F, entry.getKey().getY() + 0.5F, entry.getKey().getZ() + 0.5F, Formatting.DARK_AQUA.getColorValue(), true,0.025F);
//                    BaseRender.drawBoxWithLine(entry.getKey(), entry.getKey().add(1, 1, 1), 0.5F, 0.5F, 1, 0.1F, true);
//                }
//            }
//        }
//    }
//    public static void NCUpdate(BlockPos pos){
//        tick(pos,UpdateType.NC);
//        if (BlockUpdateMap.get(pos)==null){
//            BlockUpdateMap.put(new BlockPos(pos),UpdateType.NC);
//            //HashMapHelper.put(BlockUpdateMap,pos,UpdateType.NC);
//        }
//        else if(BlockUpdateMap.get(pos)==UpdateType.PP) {
//            BlockUpdateMap.put(new BlockPos(pos), UpdateType.NC_And_PP);
//            //HashMapHelper.put(BlockUpdateMap,pos,UpdateType.NC_And_PP);
//        } else {
//            return;
//        }
//    }
//    public static void PPUpdate(BlockPos pos){
//        tick(pos,UpdateType.PP);
//        if (BlockUpdateMap.get(pos)==null){
//            BlockUpdateMap.put(new BlockPos(pos),UpdateType.PP);
//            //HashMapHelper.put(BlockUpdateMap,pos,UpdateType.PP);
//        }
//        else if(BlockUpdateMap.get(pos)==UpdateType.NC) {
//            BlockUpdateMap.put(new BlockPos(pos), UpdateType.NC_And_PP);
//            //HashMapHelper.put(BlockUpdateMap,pos,UpdateType.NC_And_PP);
//        }
//        else {
//            return;
//        }
//    }
//
//    public static void List(){
//        for (Map.Entry<BlockPos, UpdateType> entry:BlockUpdateMap.entrySet()) {
//            Messenger.print_server_message(CarpetServer.minecraft_server,Messenger.c("  "+entry.getValue().toString(),Messenger.tp("Update",entry.getKey())));
//        }
//    }
//
//    public static void ClearHashMap(){
//        BlockUpdateMap.clear();
//    }
//    public static void tickInTickFreeze(){
//        if (option!=1) {
//            ClearHashMap();
//        }
//    }
//   public static void tick(BlockPos pos,UpdateType type){
//        log.log((option) -> {
//
//            switch (option) {
//                case "brief" -> {
//                    setOption(0);
//                    if (isReturn) {
//                        return new BaseText[]{Messenger.c("  " + CarpetServer.minecraft_server.getOverworld().getTime() + "  @" + type.toString(), Messenger.tp("Update", pos))};
//                    }
//                    else return null;
//                }
//                case "all" -> {
//                    setOption(1);
//                    if (isReturn) {
//                        return new BaseText[]{Messenger.c("  " + CarpetServer.minecraft_server.getOverworld().getTime() + "  @" + type.toString(), Messenger.tp("Update", pos))};
//                    }
//                    else return null;
//                }
//                case "clean" -> {
//                    setOption(0);
//                    //BlockUpdateLogger.ClearHashMap();
//                    return null;
//                }
//                case "enableReturn"->{
//                    setIsReturn(true);
//                    return null;
//                }
//                case "disableReturn"->{
//                    setIsReturn(false);
//                    return null;
//                }
//                default -> {
//                    return null;
//                }
//            }
//        });
//    }
}

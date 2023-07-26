package lazyalienserver.carpetlasaddition.helper;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import lazyalienserver.carpetlasaddition.utils.ChunkUtils;
import net.minecraft.server.world.ServerWorld;

public class CheckLASCarpetSetting {
    public static void checkNoteChunkLoad(ServerWorld serverWorld){
        if(!CarpetLASSetting.NoteBlockLoadChunk&& !ChunkUtils.NoteChunkLoadList.isEmpty()){
            ChunkUtils.RemoveAllNoteBlockChunkLoad(serverWorld);
        }
    }
}

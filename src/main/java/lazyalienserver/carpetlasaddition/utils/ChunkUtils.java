package lazyalienserver.carpetlasaddition.utils;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;

public class ChunkUtils {
    public static final ChunkTicketType<ChunkPos> ENDER_PEARL_TICKET=ChunkTicketType.create("endeer_pearl", Comparator.comparingLong(ChunkPos::toLong),2);
    public static final ChunkTicketType<ChunkPos> NoteBlockChunkTicket=ChunkTicketType.create("NoteBlock", Comparator.comparingLong(ChunkPos::toLong),300);
    public static void addEnderPearlChunkTicket(Entity entity)
    {
        World world = entity.getEntityWorld();
        Vec3d velocity = entity.getVelocity();

        if (world instanceof ServerWorld &&
                (Math.abs(velocity.x) > 0.001 || Math.abs(velocity.z) > 0.001))
        {
            Vec3d pos = entity.getPos();
            double nx = pos.x + velocity.x;
            double nz = pos.z + velocity.z;
            ChunkPos cp = new ChunkPos(MathHelper.floor(nx) >> 4, MathHelper.floor(nz) >> 4);
            ChunkPos cp2 = new ChunkPos(MathHelper.floor(pos.x) >> 4, MathHelper.floor(pos.z) >> 4);
            ((ServerWorld) world).getChunkManager().addTicket(ENDER_PEARL_TICKET, cp2, 3, cp2);
            ((ServerWorld) world).getChunkManager().addTicket(ENDER_PEARL_TICKET, cp, 3, cp);
            /*
            //Log-Start
            if(TestCommand.tf) {
                LASLogUtils.log(world.getServer().getTickTime() + "   POS:  x:" + pos.x + "  z:" + pos.z);
                LASLogUtils.log(world.getServer().getTickTime() + "   Velocity:  x:" + velocity.x + "  z:" + velocity.z);
                LASLogUtils.log(world.getServer().getTickTime() + "   P+Z:  nx:" + nx + "  nz:" + nz);

                Messenger.print_server_message(world.getServer(), Messenger.tp(" " + world.getServer().getTickTime() + " POS:", pos));
                Messenger.print_server_message(world.getServer(), Messenger.c(" " + world.getServer().getTickTime() + "  Velocity:  x:" + velocity.x + "  z:" + velocity.z));
                Messenger.print_server_message(world.getServer(), Messenger.tp(" " + world.getServer().getTickTime() + " P+Z:", nx, pos.y, nz));
            }
            //Log-End
            */
        }
    }
    public static void addNCNoteBlockChunkTicket(World world, BlockPos pos){
        if(world instanceof ServerWorld){
            ChunkPos cp=ChunkUtils.BlockPOStoChunkPOS(pos);
            ((ServerWorld)world).getChunkManager().addTicket(NoteBlockChunkTicket,cp,3,cp);
        }
    }
    private static ChunkPos BlockPOStoChunkPOS(BlockPos blockPos){
        return new ChunkPos(blockPos.getX()>>4,blockPos.getZ()>>4);
    }
}

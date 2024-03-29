package lazyalienserver.carpetlasaddition.helper;

import net.minecraft.server.network.ServerPlayerEntity;

/**
 *
 * @author  Fanzhitianyu
 * @see     lazyalienserver.carpetlasaddition.helper
 */
@FunctionalInterface
public interface CommandSourceInterface {
    public abstract void run(ServerPlayerEntity player);
}

package com.purplerupter.gscl;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.List;

@Mod(modid = GSCL.MODID, name = GSCL.NAME, version = GSCL.VERSION, dependencies = "required-after:gamestages@[2.0.123,)")
public class GSCL {

    public static final String MODID = "gscl";
    public static final String NAME = "Game Stage Condition Library";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Register event handlers, etc.
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Initialize mod
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        // Register commands
    }

    public static boolean isGameStageNearby(Entity entity, List<String> stages) {
        WorldServer world = (WorldServer) entity.world;
        int renderDistance = getRenderDistance(world.getMinecraftServer());

        for (EntityPlayerMP player : world.getMinecraftServer().getPlayerList().getPlayers()) {
            if (player.dimension == entity.dimension) {
                if (hasGameStages(player, stages) && isWithinRenderDistance(entity, player, renderDistance)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getRenderDistance(MinecraftServer server) {
        return server.isSinglePlayer() ? server.getPlayerList().getViewDistance() : server.getPlayerList().getViewDistance();
    }

    private static boolean hasGameStages(EntityPlayerMP player, List<String> stages) {
        for (String stage : stages) {
            if (!GameStageHelper.hasStage(player, stage)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isWithinRenderDistance(Entity entity, EntityPlayerMP player, int renderDistance) {
        int chunkXEntity = entity.chunkCoordX;
        int chunkZEntity = entity.chunkCoordZ;
        int chunkXPlayer = player.chunkCoordX;
        int chunkZPlayer = player.chunkCoordZ;

        return Math.abs(chunkXEntity - chunkXPlayer) <= renderDistance && Math.abs(chunkZEntity - chunkZPlayer) <= renderDistance;
    }
}

package net.axay.fabrik.persistence.mixin.chunk;

import net.axay.fabrik.persistence.CompoundProvider;
import net.axay.fabrik.persistence.PersistentCompound;
import net.axay.fabrik.persistence.PersistentCompoundImpl;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.ProtoChunk;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelChunk.class)
public class LevelChunkMixin implements CompoundProvider {
    @Unique
    private PersistentCompound compound = new PersistentCompoundImpl();

    // this targets the constructor which creates a WorldChunk from a ProtoChunk
    @Inject(
            method = "<init>(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ProtoChunk;Lnet/minecraft/world/level/chunk/LevelChunk$PostLoadProcessor;)V",
            at = @At("RETURN")
    )
    private void initFromProtoChunk(ServerLevel serverLevel,
                                    ProtoChunk protoChunk,
                                    LevelChunk.PostLoadProcessor postLoadProcessor,
                                    CallbackInfo ci) {
        compound = ((CompoundProvider) protoChunk).getCompound();
    }

    @NotNull
    @Override
    public PersistentCompound getCompound() {
        return compound;
    }
}

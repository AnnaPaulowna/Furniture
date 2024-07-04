package com.berksire.furniture.block.entity;

import com.berksire.furniture.client.entity.FakeFishTankEntity;
import com.berksire.furniture.registry.EntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Objects;
import java.util.Optional;

public class FishTankBlockEntity extends BlockEntity {
    private boolean hasCod;
    private boolean hasPufferfish;
    private boolean hasSalmon;
    private int linkedRealTankBlock = -1;

    public FishTankBlockEntity(BlockPos pos, BlockState state) {
        super(EntityTypeRegistry.FISH_TANK_BLOCK_ENTITY.get(), pos, state);
    }

    public boolean hasCod() {
        return hasCod;
    }

    public void setHasCod(boolean hasCod) {
        this.hasCod = hasCod;
        setChanged();
    }

    public boolean hasPufferfish() {
        return hasPufferfish;
    }

    public void setHasPufferfish(boolean hasPufferfish) {
        this.hasPufferfish = hasPufferfish;
        setChanged();
    }

    public boolean hasSalmon() {
        return hasSalmon;
    }

    public void setHasSalmon(boolean hasSalmon) {
        this.hasSalmon = hasSalmon;
        setChanged();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        hasCod = tag.getBoolean("HasCod");
        hasPufferfish = tag.getBoolean("HasPufferfish");
        hasSalmon = tag.getBoolean("HasSalmon");
        linkedRealTankBlock = tag.getInt("LinkedRealTankBlock");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("HasCod", hasCod);
        tag.putBoolean("HasPufferfish", hasPufferfish);
        tag.putBoolean("HasSalmon", hasSalmon);
        tag.putInt("LinkedRealTankBlock", linkedRealTankBlock);
    }

    public Optional<FakeFishTankEntity> getLinkedRealEntity() {
        if (Objects.requireNonNull(this.getLevel()).getEntity(linkedRealTankBlock) instanceof FakeFishTankEntity entity) {
            return Optional.of(entity);
        } else return Optional.empty();
    }

    public static void tick(Level level, BlockPos pos, FishTankBlockEntity blockEntity) {
        if (blockEntity.linkedRealTankBlock == -1) {
            level.getEntitiesOfClass(FakeFishTankEntity.class, new AABB(pos)).stream().findAny().ifPresentOrElse(
                    e -> blockEntity.linkedRealTankBlock = e.getId(), () -> {
                        FakeFishTankEntity fishTank = EntityTypeRegistry.FAKE_FISH_TANK.get().create(level);
                        assert fishTank != null;
                        fishTank.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                        level.addFreshEntity(fishTank);
                    }
            );
        }
    }

    @Override
    public void setRemoved() {
        this.linkedRealTankBlock = -1;
        this.getLinkedRealEntity().ifPresent(e -> e.remove(Entity.RemovalReason.DISCARDED));
        super.setRemoved();
    }
}

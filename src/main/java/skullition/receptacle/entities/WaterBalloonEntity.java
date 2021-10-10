package skullition.receptacle.entities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import skullition.receptacle.Setup;

public class WaterBalloonEntity extends ThrownItemEntity {
    PlayerEntity player;

    public WaterBalloonEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public WaterBalloonEntity(World world, LivingEntity owner) {
        super(Setup.WATER_BALLOON_ENTITY_ENTITY_TYPE, owner, world);
        this.player = (PlayerEntity) owner;
    }

    public WaterBalloonEntity(World world, double x, double y, double z) {
        super(Setup.WATER_BALLOON_ENTITY_ENTITY_TYPE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Setup.WATER_BALLOON_ITEM;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        BlockPos pos = entity.getBlockPos();
        this.world.setBlockState(pos, Blocks.WATER.getDefaultState());
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float) 0);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        BlockPos pos = new BlockPos(hitResult.getPos());
        if (!this.world.isClient) {
            this.discard();
            if (this.world.isAir(pos)) {
                this.world.setBlockState(pos, Blocks.WATER.getDefaultState());
            } else if (this.world.isAir(pos.up())) {
                this.world.setBlockState(pos.up(), Blocks.WATER.getDefaultState());
            } else if (this.player != null && this.world.isAir(pos.offset(player.getHorizontalFacing().getOpposite()))) {
                this.world.setBlockState(pos.offset(player.getHorizontalFacing().getOpposite()), Blocks.WATER.getDefaultState());
            }
        }
    }
}

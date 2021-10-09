package skullition.receptacle.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import skullition.receptacle.Setup;

public class WaterBalloonEntity extends ThrownItemEntity {
    public WaterBalloonEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public WaterBalloonEntity(World world, LivingEntity owner) {
        super(Setup.WATER_BALLOON_ENTITY_ENTITY_TYPE, owner, world);
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
        World world = entity.getEntityWorld();
        world.setBlockState(pos, Blocks.WATER.getDefaultState());
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float) 0);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.discard();
        }
    }

}

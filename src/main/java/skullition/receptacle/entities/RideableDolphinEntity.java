package skullition.receptacle.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.MessageType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import skullition.receptacle.Receptacle;

import java.util.UUID;

public class RideableDolphinEntity extends DolphinEntity implements Saddleable {
    private static final TrackedData<Boolean> SADDLED;
    private static final TrackedData<Integer> BOOST_TIME;

    static {
        SADDLED = DataTracker.registerData(RideableDolphinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        BOOST_TIME = DataTracker.registerData(RideableDolphinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    private final SaddledComponent saddledComponent;


    public RideableDolphinEntity(EntityType<? extends DolphinEntity> entityType, World world) {
        super(entityType, world);
        this.saddledComponent = new SaddledComponent(this.dataTracker, BOOST_TIME, SADDLED);
    }

    public static DefaultAttributeContainer.Builder createRideableDolphinAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2000000476837158D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }

    @Override
    public boolean canBeSaddled() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void saddle(@Nullable SoundCategory sound) {
        this.saddledComponent.setSaddled(true);
        if (sound != null) {
            this.world.playSoundFromEntity(null, this, SoundEvents.ENTITY_PIG_SADDLE, sound, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return this.saddledComponent.isSaddled();
    }

    @Override
    protected void dropInventory() {
        super.dropInventory();
        if (this.isSaddled()) {
            this.dropItem(Items.SADDLE);
        }
    }

    @Nullable
    @Override
    public Entity getPrimaryPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public boolean canBeControlledByRider() {
        Entity entity = this.getPrimaryPassenger();
        return entity instanceof PlayerEntity;
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (this.isSaddled() && !this.hasPassengers() && !player.shouldCancelInteraction()) {
            if (!this.world.isClient) {
                player.startRiding(this);
            }

            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SADDLED, false);
        this.dataTracker.startTracking(BOOST_TIME, 0);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.saddledComponent.writeNbt(nbt);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.saddledComponent.readNbt(nbt);
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isAlive() && this.isTouchingWater()) {
            Entity entity2 = this.getFirstPassenger();
            if (this.hasPassengers() && this.canBeControlledByRider() && entity2 instanceof PlayerEntity) {
                this.setYaw(entity2.getYaw());
                this.prevYaw = this.getYaw();
                this.setPitch(entity2.getPitch() * 0.5F);
                this.setRotation(this.getYaw(), this.getPitch());
                this.bodyYaw = this.getYaw();
                this.headYaw = this.getYaw();
                this.flyingSpeed = this.getMovementSpeed() * 0.1F;
                if (this.saddledComponent.boosted && this.saddledComponent.boostedTime++ > this.saddledComponent.currentBoostTime) {
                    this.saddledComponent.boosted = false;
                }

                if (this.isLogicalSideForUpdatingMovement()) {
                    float f = this.getSaddledSpeed();
                    if (this.saddledComponent.boosted) {
                        f += f * MathHelper.sin((float)this.saddledComponent.boostedTime / (float)this.saddledComponent.currentBoostTime * 3.1415927F / 2);
                    }

                     this.setMovementSpeed(f);
                     this.setMovementInput(new Vec3d(0.0D, 1.0D, 1.0D));
                    this.updateVelocity(this.getMovementSpeed(), movementInput);
                    this.move(MovementType.SELF, this.getVelocity());
                    this.setVelocity(this.getVelocity().multiply(0.9D));
                    this.bodyTrackingIncrements = 0;
                } else {
                    this.updateLimbs(this, false);
                    this.setVelocity(Vec3d.ZERO);
                }

                this.tryCheckBlockCollision();
            } else {
                this.flyingSpeed = 0.02F;
                this.setMovementInput(movementInput);
            }
        } else {
            super.travel(movementInput);
        }
    }

    public void setMovementInput(Vec3d movementInput) {
        super.travel(movementInput);
    }

    public float getSaddledSpeed() {
        return (float) this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) * 1.225F;
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (BOOST_TIME.equals(data) && this.world.isClient) {
            this.saddledComponent.boost();
        }

        super.onTrackedDataSet(data);
    }

    @Override
    public boolean canBeRiddenInWater() {
        return true;
    }
}


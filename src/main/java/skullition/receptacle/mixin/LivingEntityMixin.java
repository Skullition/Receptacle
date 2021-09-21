package skullition.receptacle.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import skullition.receptacle.Setup;
import skullition.receptacle.items.MagmaWalkerEnchantment;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "applyMovementEffects")
	private void injectApplyMovementEffects(BlockPos pos, CallbackInfo ci) {
		int i = EnchantmentHelper.getEquipmentLevel(Setup.MAGMA_WALKER_ENCHANTMENT, (LivingEntity) (Object)this);
		if (i > 0) {
			MagmaWalkerEnchantment.freezeLava((LivingEntity) (Object)this, world, pos, i);
		}
	}
}

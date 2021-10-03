package skullition.receptacle.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import skullition.receptacle.items.abstractitems.AbstractPortableShelterItem;

public class LargeGlassShelterItem extends AbstractPortableShelterItem {
    public LargeGlassShelterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));
        BlockPos pos = user.getBlockPos();
        final BlockState glass = Blocks.GLASS.getDefaultState();
        super.createLargeShelter(world, pos, glass, user, hand);
        world.playSound(null, pos, SoundEvents.BLOCK_GLASS_PLACE, SoundCategory.PLAYERS, 1f, 1f);

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}

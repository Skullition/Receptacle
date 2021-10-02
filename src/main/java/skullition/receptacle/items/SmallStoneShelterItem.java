package skullition.receptacle.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import skullition.receptacle.items.abstractitems.AbstractPortableShelterItem;

public class SmallStoneShelterItem extends AbstractPortableShelterItem {
    public SmallStoneShelterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));
        BlockPos pos = user.getBlockPos();
        final BlockState glass = Blocks.GLASS.getDefaultState();
        super.createSmallShelter(world, pos, glass, user, hand);

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}

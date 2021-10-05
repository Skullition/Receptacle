package skullition.receptacle.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FloatingBlockItem extends BlockItem {
    public FloatingBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient)  {return TypedActionResult.success(stack);}
        BlockPos posDown = user.getBlockPos().down();
        if (world.getBlockState(posDown).isAir()) {
            world.setBlockState(posDown, this.getBlock().getDefaultState());
            stack.setCount(stack.getCount() - 1);
        }
        return TypedActionResult.success(stack);
    }
}

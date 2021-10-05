package skullition.receptacle.items;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FloatingBlockItem extends BlockItem {
    public FloatingBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        Direction playerDirection = user.getHorizontalFacing();
        if (world.isClient)  {return TypedActionResult.success(stack);}
        BlockPos posDownFront = user.getBlockPos().down().offset(playerDirection);
        if (world.getBlockState(posDownFront).isAir()) {
            world.setBlockState(posDownFront, this.getBlock().getDefaultState());
            stack.setCount(stack.getCount() - 1);
            world.playSound(null, posDownFront, SoundEvents.BLOCK_GLASS_PLACE, SoundCategory.PLAYERS, 1f, 1f);
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("floating_block.tooltip"));
    }
}

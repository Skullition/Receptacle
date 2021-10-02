package skullition.receptacle.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PortableShelterItem extends Item {
    public PortableShelterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));
        BlockPos pos = user.getBlockPos();
        final BlockState glass = Blocks.GLASS.getDefaultState();
        // loops are overrated
        this.placeBlock(world, pos.up(2), glass);

        this.placeBlock(world,pos.west(), glass);
        this.placeBlock(world, pos.west().add(0, 1, 0),glass);

        this.placeBlock(world,pos.east(), glass);
        this.placeBlock(world, pos.east().add(0, 1, 0),glass);

        this.placeBlock(world,pos.north(), glass);
        this.placeBlock(world, pos.north().add(0, 1, 0),glass);

        this.placeBlock(world,pos.south(), glass);
        this.placeBlock(world, pos.south().add(0, 1, 0),glass);

        this.placeBlock(world, pos, Blocks.TORCH.getDefaultState());
        ItemStack itemStack = user.getStackInHand(hand);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private void placeBlock(World world, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos).isAir()) {
            world.setBlockState(pos, state);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(1, new TranslatableText("portable_shelter_item.tooltip").formatted(Formatting.GRAY));
    }
}

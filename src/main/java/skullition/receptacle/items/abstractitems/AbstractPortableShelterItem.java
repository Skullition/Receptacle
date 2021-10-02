package skullition.receptacle.items.abstractitems;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AbstractPortableShelterItem extends Item {
    public AbstractPortableShelterItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(1, new TranslatableText("portable_shelter_item.tooltip").formatted(Formatting.GRAY));
    }

    protected void placeBlock(World world, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos).isAir()) {
            world.setBlockState(pos, state);
        }
    }

    protected void createSmallShelter(World world, BlockPos pos, BlockState state, PlayerEntity user, Hand hand) {
        this.placeBlock(world, pos.up(2), state);

        this.placeBlock(world, pos.west(), state);
        this.placeBlock(world, pos.west().add(0, 1, 0), state);

        this.placeBlock(world, pos.east(), state);
        this.placeBlock(world, pos.east().add(0, 1, 0), state);

        this.placeBlock(world, pos.north(), state);
        this.placeBlock(world, pos.north().add(0, 1, 0), state);

        this.placeBlock(world, pos.south(), state);
        this.placeBlock(world, pos.south().add(0, 1, 0), state);

        if (!world.getBlockState(pos.down()).isAir()) {
            this.placeBlock(world, pos, Blocks.TORCH.getDefaultState());
        }
        ItemStack itemStack = user.getStackInHand(hand);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
    }
}

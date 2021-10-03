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

    protected void createLargeShelter(World world, BlockPos pos, BlockState state, PlayerEntity user, Hand hand) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockPos west = pos.west(2);
        BlockPos west2 = west.south();

        mutable.set(west2);
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                this.placeBlock(world, mutable.add(0, y, -x), state);
                world.addBlockBreakParticles(mutable, state);
            }
        }

        BlockPos south = pos.south(2);
        BlockPos south2 = south.east();

        mutable.set(south2);
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                this.placeBlock(world, mutable.add(-x, y, 0), state);
            }
        }


        BlockPos east = pos.east(2);
        BlockPos east2 = east.south();

        mutable.set(east2);
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                this.placeBlock(world, mutable.add(0, y, -x), state);
                world.addBlockBreakParticles(mutable, state);
            }
        }

        BlockPos north = pos.north(2);
        BlockPos north2 = north.east();

        mutable.set(north2);
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                this.placeBlock(world, mutable.add(-x, y, 0), state);
            }
        }


        BlockPos roof = pos.up(3);
        BlockPos roof2 = roof.east();
        BlockPos roof3 = roof2.north();

        mutable.set(roof3);
        for (int x = 0; x < 3; ++x) {
            for (int z = 0; z < 3; ++z) {
                this.placeBlock(world, mutable.add(-x, 0, z), state);
            }
        }

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

package skullition.receptacle.gametests;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.util.math.BlockPos;
import skullition.receptacle.Setup;
import skullition.receptacle.items.MagmaWalkerEnchantment;

public class MagmaWalkerEnchantmentTest implements FabricGameTest {
    @GameTest(structureName = EMPTY_STRUCTURE)
    @SuppressWarnings("unused")
    // FIXME: 9/22/21 freezeLava call won't freeze lava
    public void LavaFreezeTest(TestContext test) {
        BlockPos pos = new BlockPos(4, 4, 4);
        BlockPos absolutePos = test.getAbsolutePos(pos);
        ServerWorld world = test.getWorld();
        test.setBlockState(pos.down(), Blocks.LAVA.getDefaultState());
        MagmaWalkerEnchantment.freezeLava(test.createMockPlayer(), world, absolutePos, 1);
        test.expectBlockAtEnd(Setup.FROZEN_MAGMA_BLOCK, pos.down());

    }
}

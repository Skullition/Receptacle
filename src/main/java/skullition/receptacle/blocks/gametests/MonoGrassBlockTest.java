package skullition.receptacle.blocks.gametests;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.util.math.BlockPos;
import skullition.receptacle.Setup;

public class MonoGrassBlockTest implements FabricGameTest {
    @SuppressWarnings("unused")
    @GameTest(structureName = EMPTY_STRUCTURE)
    public void grassCanGrow(TestContext test) {
        BlockPos pos1 = new BlockPos(1, 1, 1);
        BlockPos pos2 = new BlockPos(1, 1, 2);
        test.setBlockState(pos1, Setup.MONO_GRASS_BLOCK);
        test.setBlockState(pos2, Setup.MONO_DIRT);
        for (int i = 0; i < 100; i++) {
            test.forceRandomTick(pos1);
        }
        test.expectBlockAtEnd(Setup.MONO_GRASS_BLOCK, pos2);
    }

    @GameTest(structureName = EMPTY_STRUCTURE)
    public void grassIsRemoved(TestContext test) {

        BlockPos pos1 = new BlockPos(1, 1, 1);
        BlockPos pos2 = new BlockPos(1, 0, 1);
        test.setBlockState(pos1, Setup.MONO_GRASS_BLOCK);
        test.setBlockState(pos2, Setup.MONO_GRASS_BLOCK);
        test.forceRandomTick(pos2);
        test.expectBlockAtEnd(Setup.MONO_DIRT, pos2);
    }
}

package skullition.receptacle;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Receptacle implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "receptacle";

	public static final ItemGroup RECEPTACLE_ITEM_GROUP = FabricItemGroupBuilder
			.build(new Identifier(Receptacle.MODID, "receptacle_item_group"), () -> new ItemStack(Setup.MONO_DIRT));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Setup.registerAll();
		LOGGER.debug("onInitialize() called.");
	}
}

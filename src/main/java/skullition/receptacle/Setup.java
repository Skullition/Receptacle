package skullition.receptacle;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import skullition.receptacle.blocks.FrozenMagmaBlock;
import skullition.receptacle.blocks.MonoGrassBlock;
import skullition.receptacle.entities.RideableDolphinEntity;
import skullition.receptacle.items.MagmaWalkerEnchantment;
import skullition.receptacle.items.SmallGlassShelterItem;

public class Setup {
    // items
    public static final SmallGlassShelterItem SMALL_GLASS_SHELTER_ITEM = new SmallGlassShelterItem(new FabricItemSettings().group(Receptacle.RECEPTACLE_ITEM_GROUP).maxCount(16));
    // blocks
    public static final Block MONO_DIRT = new Block(FabricBlockSettings.of(Material.SOIL).strength(0.5F).sounds(BlockSoundGroup.GRAVEL));
    public static final Block MONO_COBBLESTONE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F, 6.0F));
    public static final MonoGrassBlock MONO_GRASS_BLOCK = new MonoGrassBlock(FabricBlockSettings.of(Material.SOIL).strength(0.5F).sounds(BlockSoundGroup.GRASS).ticksRandomly());
    public static final FrozenMagmaBlock FROZEN_MAGMA_BLOCK = new FrozenMagmaBlock(AbstractBlock.Settings.of(Material.ICE).slipperiness(0.98F).ticksRandomly().strength(50.0F, 1200.0F));
    // block entities
    // sounds
    public static final SoundEvent MONO_AMBIENT = new SoundEvent(new Identifier(Receptacle.MODID, "monobiome"));
    // enchantments
    public static final MagmaWalkerEnchantment MAGMA_WALKER_ENCHANTMENT = new MagmaWalkerEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.FEET});
    // entities
    public static final EntityType<RideableDolphinEntity> RIDEABLE_DOLPHIN_ENTITY = FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, RideableDolphinEntity::new).dimensions(EntityDimensions.fixed(0.9F, 0.6F)).build();

    public static void registerAll() {
        // items
        Registry.register(Registry.ITEM, new Identifier(Receptacle.MODID, "small_glass_shelter_item"), SMALL_GLASS_SHELTER_ITEM);
        // blocks
        Registry.register(Registry.BLOCK, new Identifier(Receptacle.MODID, "mono_grass_block"), MONO_GRASS_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Receptacle.MODID, "mono_dirt"), MONO_DIRT);
        Registry.register(Registry.BLOCK, new Identifier(Receptacle.MODID, "mono_cobblestone"), MONO_COBBLESTONE);
        Registry.register(Registry.BLOCK, new Identifier(Receptacle.MODID, "frozen_magma_block"), FROZEN_MAGMA_BLOCK);
        // block items
        Registry.register(Registry.ITEM, new Identifier(Receptacle.MODID, "mono_dirt"), new BlockItem(MONO_DIRT, new FabricItemSettings().group(Receptacle.RECEPTACLE_ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(Receptacle.MODID, "mono_grass_block"), new BlockItem(MONO_GRASS_BLOCK, new FabricItemSettings().group(Receptacle.RECEPTACLE_ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(Receptacle.MODID, "mono_cobblestone"), new BlockItem(MONO_COBBLESTONE, new FabricItemSettings().group(Receptacle.RECEPTACLE_ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(Receptacle.MODID, "frozen_magma_block"), new BlockItem(FROZEN_MAGMA_BLOCK, new FabricItemSettings().group(Receptacle.RECEPTACLE_ITEM_GROUP)));
        // block entities
        // sounds
        Registry.register(Registry.SOUND_EVENT, new Identifier(Receptacle.MODID, "monobiome"), MONO_AMBIENT);
        // enchantments
        Registry.register(Registry.ENCHANTMENT, new Identifier(Receptacle.MODID, "magma_walker_enchantment"), MAGMA_WALKER_ENCHANTMENT);
        // entities
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Receptacle.MODID, "rideable_dolphin_entity"), RIDEABLE_DOLPHIN_ENTITY);
        // entity attributes
        FabricDefaultAttributeRegistry.register(RIDEABLE_DOLPHIN_ENTITY, RideableDolphinEntity.createRideableDolphinAttributes());

    }
}

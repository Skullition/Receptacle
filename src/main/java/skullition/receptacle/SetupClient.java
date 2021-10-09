package skullition.receptacle;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import skullition.receptacle.entities.WaterBalloonEntity;
import skullition.receptacle.render.entity.RideableDolphinEntityRender;
import skullition.receptacle.render.entity.model.RideableDolphinEntityModel;

public class SetupClient {
    public static final EntityModelLayer RIDEABLE_DOLPHIN_LAYER = new EntityModelLayer(new Identifier(Receptacle.MODID, "rideable_dolphin"),"main");

    public static void registerAll() {
        EntityRendererRegistry.register(Setup.RIDEABLE_DOLPHIN_ENTITY, RideableDolphinEntityRender::new);
        EntityModelLayerRegistry.registerModelLayer(RIDEABLE_DOLPHIN_LAYER, RideableDolphinEntityModel::getTexturedModelData);
        BlockRenderLayerMap.INSTANCE.putBlock(Setup.FLOATING_BLOCK, RenderLayer.getCutout());
        EntityRendererRegistry.register(Setup.WATER_BALLOON_ENTITY_ENTITY_TYPE, FlyingItemEntityRenderer::new);
    }
}

package skullition.receptacle;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import skullition.receptacle.render.entity.RideableDolphinEntityRender;
import skullition.receptacle.render.entity.model.RideableDolphinEntityModel;

public class SetupClient {
    public static final EntityModelLayer RIDEABLE_DOLPHIN_LAYER = new EntityModelLayer(new Identifier(Receptacle.MODID, "rideable_dolphin"),"main");

    public static void registerAll() {
        EntityRendererRegistry.register(Setup.RIDEABLE_DOLPHIN_ENTITY, RideableDolphinEntityRender::new);
        EntityModelLayerRegistry.registerModelLayer(RIDEABLE_DOLPHIN_LAYER, RideableDolphinEntityModel::getTexturedModelData);
    }
}

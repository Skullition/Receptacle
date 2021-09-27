package skullition.receptacle.render.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import skullition.receptacle.Receptacle;
import skullition.receptacle.SetupClient;
import skullition.receptacle.entities.RideableDolphinEntity;
import skullition.receptacle.render.entity.feature.RideableDolphinHeldItemFeatureRenderer;
import skullition.receptacle.render.entity.model.RideableDolphinEntityModel;

public class RideableDolphinEntityRender extends MobEntityRenderer<RideableDolphinEntity, RideableDolphinEntityModel<RideableDolphinEntity>> {
    public RideableDolphinEntityRender(EntityRendererFactory.Context context) {
        super(context, new RideableDolphinEntityModel<>(context.getPart(SetupClient.RIDEABLE_DOLPHIN_LAYER)), 0.7F);
        this.addFeature(new RideableDolphinHeldItemFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(RideableDolphinEntity entity) {
        return new Identifier(Receptacle.MODID, "textures/entity/rideable_dolphin.png");
    }
}

package skullition.receptacle.render.entity.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import skullition.receptacle.entities.RideableDolphinEntity;
import skullition.receptacle.render.entity.model.RideableDolphinEntityModel;


public class RideableDolphinHeldItemFeatureRenderer extends FeatureRenderer<RideableDolphinEntity, RideableDolphinEntityModel<RideableDolphinEntity>> {
    public RideableDolphinHeldItemFeatureRenderer(FeatureRendererContext<RideableDolphinEntity, RideableDolphinEntityModel<RideableDolphinEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, RideableDolphinEntity rideableDolphinEntity, float f, float g, float h, float j, float k, float l) {
        boolean bl = rideableDolphinEntity.getMainArm() == Arm.RIGHT;
        matrixStack.push();
        float m = 1.0F;
        float n = -1.0F;
        float o = MathHelper.abs(rideableDolphinEntity.getPitch()) / 60.0F;
        if (rideableDolphinEntity.getPitch() < 0.0F) {
            matrixStack.translate(0.0D, 1.0F - o * 0.5F, -1.0F + o * 0.5F);
        } else {
            matrixStack.translate(0.0D, 1.0F + o * 0.8F, -1.0F + o * 0.2F);
        }

        ItemStack itemStack = bl ? rideableDolphinEntity.getMainHandStack() : rideableDolphinEntity.getOffHandStack();
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(rideableDolphinEntity, itemStack, ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, i);
        matrixStack.pop();
    }
}

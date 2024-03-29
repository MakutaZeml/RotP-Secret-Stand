package com.zeml.rotp_spiderman.client.render.entity.model.projectile;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootHEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.jetbrains.annotations.NotNull;

public class WebShotModel extends EntityModel<WebShootHEntity> {
	private final ModelRenderer bone;

	public WebShotModel() {
		texWidth = 32;
		texHeight = 32;

		bone = new ModelRenderer(this);
		bone.setPos(8.0F, 0F, -8.0F);
		bone.texOffs(18, 0).addBox(-9.5F, -5.0F, 1.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		bone.texOffs(0, 0).addBox(-9.0F, -5.5F, 1.5F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone.texOffs(12, 7).addBox(-10.0F, -4.5F, 1.5F, 4.0F, 2.0F, 3.0F, 0.0F, false);
		bone.texOffs(11, 5).addBox(-9.0F, -4.5F, 5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 8).addBox(-8.5F, -4.0F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
	}

	public void setRotationAngle(@NotNull ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(WebShootHEntity entity, float walkAnimPos, float walkAnimSpeed, float ticks, float yRotationOffset, float xRotation) {
		bone.yRot = yRotationOffset * ((float) Math.PI / 180F);
		bone.xRot = xRotation * ((float) Math.PI / 180F);

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
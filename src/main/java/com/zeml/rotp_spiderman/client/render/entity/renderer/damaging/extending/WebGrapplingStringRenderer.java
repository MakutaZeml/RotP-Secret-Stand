package com.zeml.rotp_spiderman.client.render.entity.renderer.damaging.extending;

import com.zeml.rotp_spiderman.client.render.entity.model.ownerbound.repeating.WebStringModel;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebGrapplingEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class WebGrapplingStringRenderer extends WebStringAbstractRenderer<WebGrapplingEntity> {

    public WebGrapplingStringRenderer(EntityRendererManager renderManager) {
        super(renderManager, new WebStringModel<WebGrapplingEntity>());
    }
}

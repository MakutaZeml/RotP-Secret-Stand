package com.zeml.rotp_spiderman.client.render.entity.renderer.damaging.extending;

import com.zeml.rotp_spiderman.client.render.entity.model.ownerbound.repeating.WebStringModel;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebStringEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class WebStringRenderer extends WebStringAbstractRenderer<WebStringEntity> {

    public WebStringRenderer(EntityRendererManager renderManager) {
        super(renderManager, new WebStringModel<WebStringEntity>());
    }
}

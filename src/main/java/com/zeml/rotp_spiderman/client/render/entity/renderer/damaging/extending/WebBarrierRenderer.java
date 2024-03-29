package com.zeml.rotp_spiderman.client.render.entity.renderer.damaging.extending;

import com.zeml.rotp_spiderman.client.render.entity.model.ownerbound.repeating.WebStringModel;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebBarrierEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class WebBarrierRenderer extends WebStringAbstractRenderer<WebBarrierEntity> {

    public WebBarrierRenderer(EntityRendererManager renderManager) {
        super(renderManager, new WebStringModel<WebBarrierEntity>());
    }
}

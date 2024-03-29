package com.zeml.rotp_spiderman.client.render.entity.renderer.damaging.extending;

import com.zeml.rotp_spiderman.RotpSpiderManAddon;
import com.zeml.rotp_spiderman.client.render.entity.model.ownerbound.repeating.WebStringModel;
import com.github.standobyte.jojo.client.render.entity.renderer.damaging.extending.ExtendingEntityRenderer;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public abstract class WebStringAbstractRenderer<T extends OwnerBoundProjectileEntity> extends ExtendingEntityRenderer<T, WebStringModel<T>> {
    
    public WebStringAbstractRenderer(EntityRendererManager renderManager, WebStringModel<T> model) {
        super(renderManager, model, new ResourceLocation(RotpSpiderManAddon.MOD_ID, "textures/entity/projectiles/web.png"));
    }
}

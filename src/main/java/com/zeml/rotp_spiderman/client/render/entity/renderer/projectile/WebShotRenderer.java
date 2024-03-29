package com.zeml.rotp_spiderman.client.render.entity.renderer.projectile;

import com.github.standobyte.jojo.client.render.entity.renderer.SimpleEntityRenderer;
import com.zeml.rotp_spiderman.RotpSpiderManAddon;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootHEntity;
import com.zeml.rotp_spiderman.client.render.entity.model.projectile.WebShotModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class WebShotRenderer extends SimpleEntityRenderer<WebShootHEntity, WebShotModel> {

    public WebShotRenderer(EntityRendererManager rendererManager){
        super(rendererManager, new WebShotModel(), new ResourceLocation(RotpSpiderManAddon.MOD_ID,"textures/entity/projectiles/web_shot.png"));

    }
}

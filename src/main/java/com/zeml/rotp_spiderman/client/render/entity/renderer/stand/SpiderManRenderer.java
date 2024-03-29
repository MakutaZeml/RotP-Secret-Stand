package com.zeml.rotp_spiderman.client.render.entity.renderer.stand;

import com.zeml.rotp_spiderman.RotpSpiderManAddon;
import com.zeml.rotp_spiderman.client.render.entity.model.stand.SpiderManModel;
import com.zeml.rotp_spiderman.entity.stand.stands.SpiderManEntity;
import com.github.standobyte.jojo.client.render.entity.renderer.stand.StandEntityRenderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SpiderManRenderer extends StandEntityRenderer<SpiderManEntity, SpiderManModel> {
    
    public SpiderManRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SpiderManModel(), new ResourceLocation(RotpSpiderManAddon.MOD_ID, "textures/entity/stand/spider_man.png"), 0);
    }
}

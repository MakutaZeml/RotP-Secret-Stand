package com.zeml.rotp_spiderman.client;

import com.zeml.rotp_spiderman.RotpSpiderManAddon;
import com.zeml.rotp_spiderman.client.render.entity.renderer.damaging.extending.WebBarrierRenderer;
import com.zeml.rotp_spiderman.client.render.entity.renderer.damaging.extending.WebGrapplingStringRenderer;
import com.zeml.rotp_spiderman.client.render.entity.renderer.damaging.extending.WebStringRenderer;
import com.zeml.rotp_spiderman.client.render.entity.renderer.projectile.WebShotRenderer;
import com.zeml.rotp_spiderman.client.render.entity.renderer.stand.SpiderManRenderer;
import com.zeml.rotp_spiderman.init.AddonStands;
import com.zeml.rotp_spiderman.init.InitEntities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = RotpSpiderManAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_STRING.get(), WebStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_GRAPPLING_STRING.get(), WebGrapplingStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_BARRIER.get(), WebBarrierRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.WEB_SHOT.get(), WebShotRenderer:: new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.WEB_SHOT_H.get(),WebShotRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AddonStands.SPIDER_MAN.getEntityType(), SpiderManRenderer::new);

    }
}

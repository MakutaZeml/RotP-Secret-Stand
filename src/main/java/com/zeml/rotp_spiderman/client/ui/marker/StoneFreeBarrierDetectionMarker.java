package com.zeml.rotp_spiderman.client.ui.marker;

import java.util.List;

import com.zeml.rotp_spiderman.RotpSpiderManAddon;
import com.zeml.rotp_spiderman.init.AddonStands;
import com.github.standobyte.jojo.client.ui.marker.MarkerRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class StoneFreeBarrierDetectionMarker extends MarkerRenderer {

    public StoneFreeBarrierDetectionMarker(Minecraft mc) {
        super(AddonStands.SPIDER_MAN.getStandType().getColor(), new ResourceLocation(RotpSpiderManAddon.MOD_ID, "textures/action/stone_free_barrier.png"), mc);
    }
    
    @Override
    protected boolean shouldRender() {
        return true;
    }

    @Override
    protected void updatePositions(List<MarkerInstance> list, float partialTick) {
//        IStandPower.getStandPowerOptional(mc.player).ifPresent(stand -> {
//            if (stand.getStandManifestation() instanceof SpiderManEntity) {
//                // FIXME the net isn't synced to client
//                ((SpiderManEntity) stand.getStandManifestation()).getBarriersNet()
//                .wasRippedAt().forEach(point -> {
//                    // FIXME the constructor isn't visible
//                    list.add(new MarkerInstance(point, false));
//                });
//            }
//        });
    }
}

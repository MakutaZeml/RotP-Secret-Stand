package com.zeml.rotp_spiderman.action.stand;

import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebGrapplingEntity;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.world.World;

public class WebGrapple extends StandEntityAction {
    public static final StandPose GRAPPLE_POSE = new StandPose("SF_GRAPPLE");
    
    public WebGrapple(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            WebGrapplingEntity string = new WebGrapplingEntity(world, standEntity, userPower);
            if (isShiftVariation()) {
                string.setBindEntities(true);
            }
            world.addFreshEntity(string);
        }
    }
    
    @Override
    public boolean standRetractsAfterTask(IStandPower standPower, StandEntity standEntity) {
    	return isShiftVariation();
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return this.isShiftVariation() && target.getType() == TargetType.ENTITY;
    }
}

package com.zeml.rotp_spiderman.action.stand;

import com.github.standobyte.jojo.entity.stand.StandPose;
import com.zeml.rotp_spiderman.entity.stand.stands.SpiderManEntity;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandManifestation;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class WebBarrier extends StandEntityAction {

    public static final StandPose GRAPPLE_POSE = new StandPose("SF_GRAPPLE");

    public WebBarrier(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        if (stand instanceof SpiderManEntity) {
            SpiderManEntity spoderman = (SpiderManEntity) stand;
            if (!spoderman.canPlaceBarrier()) {
                return conditionMessage("barrier");
            }
            return ActionConditionResult.POSITIVE;
        }
        return ActionConditionResult.NEGATIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            SpiderManEntity spoderman = (SpiderManEntity) standEntity;
            spoderman.attachBarrier(task.getTarget().getBlockPos());
        }
    }
    
    public static int getMaxBarriersPlaceable(IStandPower power) {
        int level = power.getResolveLevel();
        return level >= 4 ? 50 : 10;
    }
    
    @Override
    public TranslationTextComponent getTranslatedName(IStandPower power, String key) {
        IStandManifestation stand = power.getStandManifestation();
        int barriers = stand instanceof SpiderManEntity ? ((SpiderManEntity) stand).getPlacedBarriersCount() : 0;
        return new TranslationTextComponent(key, barriers, getMaxBarriersPlaceable(power));
    }
    
    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.BLOCK;
    }

}

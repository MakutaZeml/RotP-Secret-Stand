package com.zeml.rotp_spiderman.entity.stand.stands;

import java.util.UUID;

import com.zeml.rotp_spiderman.action.stand.WebBarrier;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebBarrierEntity;
import com.zeml.rotp_spiderman.init.InitSounds;
import com.zeml.rotp_spiderman.util.SFBarriersNet;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpiderManEntity extends StandEntity {
    private static final UUID SPEED_MODIFIER_RETRACTION_UUID = UUID.fromString("a421b1ab-85a8-4164-a9ba-dbda0bc560ce");
    private static final AttributeModifier SPEED_MODIFIER_RETRACTION = new AttributeModifier(SPEED_MODIFIER_RETRACTION_UUID, "Retraction speed boost", 2.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    private static final DataParameter<Integer> PLACED_BARRIERS = EntityDataManager.defineId(SpiderManEntity.class, DataSerializers.INT);
    
    private static final DataParameter<Boolean> HAS_FOREARM = EntityDataManager.defineId(SpiderManEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HAS_SHORT_FOREARM = EntityDataManager.defineId(SpiderManEntity.class, DataSerializers.BOOLEAN);
    
    private WebBarrierEntity stringToUser;
    private WebBarrierEntity stringFromStand;
    private SFBarriersNet placedBarriers = new SFBarriersNet();
    
    public SpiderManEntity(StandEntityType<SpiderManEntity> type, World world) {
        super(type, world);
    }
    
    @Override
    public void tick() {
        if (!level.isClientSide()) {
        	placedBarriers.tick();
            setPlacedBarriersCount(placedBarriers.getSize());
        }
        super.tick();
    }
    
    public SFBarriersNet getBarriersNet() {
    	return placedBarriers;
    }
    
    public void createUserString() {
        if (!level.isClientSide()) {
            if (stringToUser == null || !stringToUser.isAlive()) {
                stringToUser = new WebBarrierEntity(level, this);
                if (stringFromStand != null && stringFromStand.isAlive()) {
                    stringFromStand.remove();
                }
                stringFromStand = stringToUser;
                level.addFreshEntity(stringFromStand);
            }
        }
    }
    
    public boolean canPlaceBarrier() {
        return getPlacedBarriersCount() < WebBarrier.getMaxBarriersPlaceable(getUserPower());
    }
    
    public boolean hasBarrierAttached() {
        return getPlacedBarriersCount() > 0 || 
                stringFromStand != null && stringFromStand.isAlive() && stringFromStand != stringToUser;
    }
    
    public void attachBarrier(BlockPos blockPos) {
        if (!level.isClientSide()) {
            if (!canPlaceBarrier()) {
                return;
            }
            if (stringFromStand != null && stringFromStand.isAlive()) {
                if (blockPos.equals(stringFromStand.getOriginBlockPos())) {
                    return;
                }
                stringFromStand.attachToBlockPos(blockPos);
                placedBarriers.add(stringFromStand);
                setPlacedBarriersCount(placedBarriers.getSize());
            }
            stringFromStand = new WebBarrierEntity(level, this);
            stringFromStand.setOriginBlockPos(blockPos);
            level.addFreshEntity(stringFromStand);
            playSound(InitSounds.SPIDER_MAN_BARRIER_PLACED.get(), 1.0F, 1.0F);
        }
    }
    
    public int getPlacedBarriersCount() {
        return entityData.get(PLACED_BARRIERS);
    }
    
    private void setPlacedBarriersCount(int value) {
        entityData.set(PLACED_BARRIERS, value);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(PLACED_BARRIERS, 0);
        entityData.define(HAS_FOREARM, true);
        entityData.define(HAS_SHORT_FOREARM, false);
    }
    
    public boolean hasForeArm() {
        return entityData.get(HAS_FOREARM);
    }

    public void setForeArm(boolean forearm) {
        entityData.set(HAS_FOREARM, forearm);
    }
    
    public boolean hasShortForeArm() {
        return entityData.get(HAS_SHORT_FOREARM);
    }

    public void setShortForeArm(boolean shortforearm) {
        entityData.set(HAS_SHORT_FOREARM, shortforearm);
    }
    
    @Override
    protected void setStandFlag(StandFlag flag, boolean value) {
        super.setStandFlag(flag, value);
        if (flag == StandFlag.BEING_RETRACTED && !value && isCloseToUser()) {
            if (stringToUser != null && stringToUser.isAlive() && stringToUser.is(stringFromStand)) {
                ModifiableAttributeInstance speedAttributeInstance = getAttribute(Attributes.MOVEMENT_SPEED);
                if (speedAttributeInstance.getModifier(SPEED_MODIFIER_RETRACTION_UUID) != null) {
                    speedAttributeInstance.removeModifier(SPEED_MODIFIER_RETRACTION);
                }
                stringToUser.remove();
            }
        }
    }
}

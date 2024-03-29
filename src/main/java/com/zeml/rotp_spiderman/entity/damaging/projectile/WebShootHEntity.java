package com.zeml.rotp_spiderman.entity.damaging.projectile;

import com.github.standobyte.jojo.JojoModConfig;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.entity.damaging.projectile.ModdedProjectileEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.potion.StatusEffect;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.zeml.rotp_spiderman.init.InitEntities;
import com.zeml.rotp_spiderman.init.InitSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class WebShootHEntity extends ModdedProjectileEntity {

    private EffectInstance immobilizedEffect = null;

    @Nullable
    private IStandPower userStandPower;

    public WebShootHEntity(LivingEntity shooter, World world, @Nullable IStandPower standPower){
        super(InitEntities.WEB_SHOT.get(), shooter, world);
        userStandPower=standPower;
    }
    public WebShootHEntity(EntityType<WebShootHEntity> type, World world) {
        super(type, world);
    }

    @Override
    public int ticksLifespan() {
        return 100;
    }

    @Override
    protected float getBaseDamage() {
        return 0;
    }

    @Override
    protected float getMaxHardnessBreakable() {
        return 0;
    }

    @Override
    public boolean standDamage() {
        return true;
    }

    private static final Vector3d OFFSET = new Vector3d(0.0, -0.3, 0.75);
    @Override
    protected Vector3d getOwnerRelativeOffset() {
        return OFFSET;
    }

    @Override
    protected void onHitEntity(@NotNull EntityRayTraceResult entityRayTraceResult){
        Entity entity = entityRayTraceResult.getEntity();
        if(entity instanceof LivingEntity){
            LivingEntity target = (LivingEntity)entity;
            if(target instanceof CreeperEntity){
                target.addEffect(new EffectInstance(ModStatusEffects.STUN.get(),ticksLifespan() - tickCount));
                immobilizedEffect = target.getEffect(ModStatusEffects.STUN.get());
            }
            else if (target instanceof StandEntity){
                target.addEffect(new EffectInstance(ModStatusEffects.STUN.get(),20));
                immobilizedEffect = target.getEffect(ModStatusEffects.STUN.get());
            }else {
                target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,ticksLifespan() - tickCount,3));
                immobilizedEffect = target.getEffect(Effects.MOVEMENT_SLOWDOWN);
            }



        }
        playSound(InitSounds.SPIDER_MAN_WEB_HIT.get(), 2F,1F);
        this.remove();
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult blockRayTraceResult) {
        super.onHitBlock(blockRayTraceResult);
        this.breakProjectile(ActionTarget.TargetType.BLOCK, blockRayTraceResult);
        playSound(InitSounds.SPIDER_MAN_WEB_HIT.get(), 2F,1F);
        this.remove();
    }

}

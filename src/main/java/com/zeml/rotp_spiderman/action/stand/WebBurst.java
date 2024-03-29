package com.zeml.rotp_spiderman.action.stand;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandStatFormulas;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.general.GeneralUtil;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootHEntity;
import com.zeml.rotp_spiderman.init.InitSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WebBurst extends StandEntityAction {

    private EffectInstance fallEffect = null;
    public WebBurst(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    public void standTickPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if(!world.isClientSide()){
            LivingEntity user = userPower.getUser();
            double fireRate = StandStatFormulas.projectileFireRateScaling(standEntity, userPower)/2.5;
            Vector3d pos = new Vector3d(standEntity.getX(),standEntity.getY()+1.3,standEntity.getZ());
            GeneralUtil.doFractionTimes(()->{
                if(!user.isOnGround()){
                    user.addEffect(new EffectInstance(Effects.SLOW_FALLING,25));
                    fallEffect = user.getEffect(Effects.SLOW_FALLING);
                }
                WebShootHEntity webShoot = new WebShootHEntity(standEntity,world,userPower);
                webShoot.moveTo(pos);
                webShoot.shootFromRotation(standEntity,2.0F,6.0F);
                standEntity.shootProjectile(webShoot,2.5F,9F);
                standEntity.playSound(InitSounds.WEB_SHOOT.get(),1F,1F);
            },fireRate);
        }
    }

}

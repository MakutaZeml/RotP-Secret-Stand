package com.zeml.rotp_spiderman.action.stand;

import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandStatFormulas;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.general.GeneralUtil;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootEntity;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootHEntity;
import com.zeml.rotp_spiderman.init.InitSounds;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SMFinisher extends StandEntityHeavyAttack {
    public SMFinisher(StandEntityHeavyAttack.Builder builder){
        super(builder);
    }

    @Override
    public void standTickPerform(@NotNull World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            double fireRate = StandStatFormulas.projectileFireRateScaling(standEntity, userPower)/2.5;
            Vector3d pos = new Vector3d(standEntity.getX(),standEntity.getY()+1.3,standEntity.getZ());
            GeneralUtil.doFractionTimes(()->{
                WebShootEntity webShoot = new WebShootEntity(standEntity,world,userPower);
                webShoot.addTag("burst");
                webShoot.moveTo(pos);
                webShoot.shootFromRotation(standEntity,2.0F,6.0F);
                standEntity.shootProjectile(webShoot,2.5F,10F);
                standEntity.playSound(InitSounds.WEB_SHOOT.get(),1F,1F);
            },fireRate);
        }
    }

}

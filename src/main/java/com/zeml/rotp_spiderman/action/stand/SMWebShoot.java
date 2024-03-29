package com.zeml.rotp_spiderman.action.stand;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootEntity;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootHEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SMWebShoot extends StandEntityAction {

    public SMWebShoot(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    public void standPerform(@NotNull World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task){
        if(!world.isClientSide){
            WebShootEntity webShoot = new WebShootEntity(standEntity,world,userPower);
            Vector3d pos = new Vector3d(standEntity.getX(),standEntity.getY()+1.3,standEntity.getZ());
            webShoot.moveTo(pos);
            webShoot.shootFromRotation(standEntity,2.0F,1.0F);
            standEntity.addProjectile(webShoot);
        }
    }

}

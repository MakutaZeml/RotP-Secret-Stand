package com.zeml.rotp_spiderman.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.IHasStandPunch;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandStatFormulas;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.general.GeneralUtil;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootEntity;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootHEntity;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebBarrierEntity;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebGrapplingEntity;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebStringEntity;
import com.zeml.rotp_spiderman.init.InitSounds;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static javafx.scene.input.KeyCode.T;


public class SpiderSense extends StandEntityHeavyAttack {

    public SpiderSense(StandEntityHeavyAttack.Builder builder){
        super(builder);
    }

    Entity tar;
    boolean audio = false;
    boolean vision = false;
    boolean once = false;


    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target){
        Entity exist = EntityRange(user,5F);
        if(exist != null){
            if(!audio){
                user.playSound(InitSounds.SPIDER_SENSE.get(),1,1);
                audio = true;
            }
            return ActionConditionResult.POSITIVE;
        }else {
            audio=false;
            return ActionConditionResult.NEGATIVE;
        }

    }


    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource){



        double strength = stand.getAttackDamage();
        if (this.tar != null){ double ux = stand.getUser().getX();
            double uz= stand.getUser().getZ();
            double ex= this.tar.getX();
            double ez= this.tar.getZ();
            double dist = Math.sqrt( Math.pow(ux-ex,2)+Math.pow(uz-ez,2)) ;

            Vector3d post = Pos(this.tar,stand,dist);
            stand.moveTo(post);

            vision = false;
            once=false;

            if(this.tar instanceof CreatureEntity){
                return (new HeavyPunchInstance(stand, this.tar, dmgSource)).damage(StandStatFormulas.getHeavyAttackDamage(strength/5)).addKnockback(1.6F + (float)strength / 8.0F).setStandInvulTime(1);
            }else {
                return (new HeavyPunchInstance(stand, this.tar, dmgSource)).damage(StandStatFormulas.getHeavyAttackDamage(strength/5)).addKnockback(1.3F + (float)strength / 8.0F).setStandInvulTime(1);
            }
        }
        else {
            return (new HeavyPunchInstance(stand, target, dmgSource)).damage(StandStatFormulas.getHeavyAttackDamage(strength/5)).addKnockback(1.0F + (float)strength / 8.0F).setStandInvulTime(1);

        }


    }

    @Override
    public void standTickPerform(@NotNull World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task){
        if(world.isClientSide){
            float meter = standEntity.getFinisherMeter();
            if(meter > .5){
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

    @Override
    public void standTickWindup(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        Entity exist = EntityRange(Objects.requireNonNull(standEntity.getUser()),10F);
        if(exist != null){
            double ux = standEntity.getUser().getX();
            double uz= standEntity.getUser().getZ();
            double ex= exist.getX();
            double ez= exist.getZ();
            double dist = Math.sqrt( Math.pow(ux-ex,2)+Math.pow(uz-ez,2));
            this.tar =exist;
            double vx = exist.getX();
            double vy;
            double vz = exist.getZ();

            if(exist instanceof  LivingEntity){
                vy= exist.getEyeY();
            }else {
                vy=exist.getY();
            }
            Vector3d vista = new Vector3d(vx,vy,vz);
            if(!vision){
                standEntity.getUser().lookAt(EntityAnchorArgument.Type.EYES,vista);
                vision=true;
            }

            Vector3d post = Pos(exist,standEntity,dist);

            standEntity.moveTo(post);

        }
        IHasStandPunch.playPunchSwingSound(task, Phase.WINDUP, 3, this, standEntity);

    }

    @Override
    public void onTaskSet(World world, StandEntity standEntity, IStandPower standPower, Phase phase, StandEntityTask task, int ticks) {
        standEntity.alternateHands();
        if (!world.isClientSide()) {
            float meter = standEntity.getFinisherMeter();
            if (meter >=.5){
                standEntity.addFinisherMeter(-0.51F, 0);
            }else {
                standEntity.addFinisherMeter(0.05F, 0);
            }

        }
    }

    static public Vector3d Pos(Entity exist, StandEntity stand, double dist){
        double ux = stand.getUser().getX();
        double uz= stand.getUser().getZ();
        double ex= exist.getX();
        double ez= exist.getZ();

        double tpx;
        double tpz;

        if(dist>3.5){
            tpx=ux+3.5*(ex-ux)/dist;
            tpz=uz+3.5*(ez-uz)/dist;
        }else {
            tpx=ux+(dist-1)*(ex-ux)/dist;
            tpz=uz+(dist-1)*(ez-uz)/dist;;
        }
         Vector3d post = new Vector3d(tpx,exist.getY(),tpz);
        return post;
    }



    public static Entity EntityRange(@NotNull LivingEntity user, float range){
        World world =user.level;
        Entity entidad = world.getEntities(null,user.getBoundingBox().inflate(range,range*2/5,range)).stream()
                .filter(entity -> !entity.is(user)).filter(entity ->!(entity instanceof AnimalEntity))
                .filter(entity -> !entity.isAlliedTo(user))
                .filter(entity -> !(entity instanceof WebShootHEntity|| entity instanceof WebShootEntity || entity instanceof WebBarrierEntity|| entity instanceof WebStringEntity || entity instanceof WebGrapplingEntity))
                .filter(entity -> !(entity instanceof ExperienceOrbEntity || entity instanceof ItemEntity || entity instanceof ItemFrameEntity || entity instanceof VillagerEntity|| entity instanceof ArmorStandEntity))
                .filter(entity -> {
                    if (entity instanceof ProjectileEntity) {
                        RayTraceResult result = world.clip(new RayTraceContext(
                                entity.position(),
                                entity.position().add(entity.getDeltaMovement()),
                                RayTraceContext.BlockMode.COLLIDER,
                                RayTraceContext.FluidMode.NONE,
                                entity
                        ));
                        return result.getType() == RayTraceResult.Type.MISS;
                    }
                    return true;
                })
                .findFirst().orElse(null);
        return entidad;
    }

}

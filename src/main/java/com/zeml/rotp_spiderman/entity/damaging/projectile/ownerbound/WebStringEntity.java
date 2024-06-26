package com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound;

import com.zeml.rotp_spiderman.init.InitEntities;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.util.mc.damage.DamageUtil;
import com.github.standobyte.jojo.util.mod.JojoModUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WebStringEntity extends OwnerBoundProjectileEntity {
    private float yRotOffset;
    private float xRotOffset;
    private boolean isBinding;
    private boolean dealtDamage;
    private float knockback = 0;

    public WebStringEntity(World world, LivingEntity entity, float angleXZ, float angleYZ, boolean isBinding) {
        super(InitEntities.SF_STRING.get(), entity, world);
        this.yRotOffset = angleXZ;
        this.xRotOffset = angleYZ;
        this.isBinding = isBinding;
    }
    
    public WebStringEntity(EntityType<? extends WebStringEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean standDamage() {
        return true;
    }
    
    public boolean isBinding() {
        return isBinding;
    }
    
    @Override
    public float getBaseDamage() {
        return isBinding ? 0.30769231F : 0.61538462F;
    }
    
    public void addKnockback(float knockback) {
    	this.knockback = knockback;
    }
    
    @Override
    protected boolean hurtTarget(Entity target, LivingEntity owner) {
        return !dealtDamage ? super.hurtTarget(target, owner) : false;
    }
    
    @Override
    protected boolean shouldHurtThroughInvulTicks() {
        return true;
    }
    
    @Override
    protected void afterEntityHit(EntityRayTraceResult entityRayTraceResult, boolean entityHurt) {
        if (entityHurt) {
            dealtDamage = true;
            Entity target = entityRayTraceResult.getEntity();
            if (isBinding) {
                if (target instanceof LivingEntity) {
                    LivingEntity livingTarget = (LivingEntity) target;
                    if (!JojoModUtil.isTargetBlocking(livingTarget)) {
                        attachToEntity(livingTarget);
                        livingTarget.addEffect(new EffectInstance(ModStatusEffects.IMMOBILIZE.get(), ticksLifespan() - tickCount));
                    }
                }
            }
            else {
                if (knockback > 0 && target instanceof LivingEntity) {
                	DamageUtil.knockback((LivingEntity) target, knockback, yRot);
                }
                setIsRetracting(true);
            }
        }
    }
    
    @Override
    protected float knockbackMultiplier() {
        return 0F;
    }
    
    @Override
    protected float getMaxHardnessBreakable() {
        return 0.0F;
    }

    @Override
	public int ticksLifespan() {
        int ticks = super.ticksLifespan();
        if (isBinding && isAttachedToAnEntity()) {
            ticks += 10;
        }
        return ticks;
    }
    
    @Override
    protected float movementSpeed() {
        return 16 / (float) ticksLifespan();
    }
    
    @Override
	public boolean isBodyPart() {
        return true;
    }
    
    private static final Vector3d OFFSET = new Vector3d(-0.3, -0.2, 0.75);
    @Override
    protected Vector3d getOwnerRelativeOffset() {
        return OFFSET;
    }

    @Override
    protected Vector3d originOffset(float yRot, float xRot, double distance) {
        return super.originOffset(yRot + yRotOffset, xRot + xRotOffset, distance);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        super.writeSpawnData(buffer);
        buffer.writeFloat(yRotOffset);
        buffer.writeFloat(xRotOffset);
        buffer.writeBoolean(isBinding);
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        super.readSpawnData(additionalData);
        this.yRotOffset = additionalData.readFloat();
        this.xRotOffset = additionalData.readFloat();
        this.isBinding = additionalData.readBoolean();
    }
}

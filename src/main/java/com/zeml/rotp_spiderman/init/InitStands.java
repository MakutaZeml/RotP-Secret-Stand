package com.zeml.rotp_spiderman.init;

import com.zeml.rotp_spiderman.RotpSpiderManAddon;
import com.zeml.rotp_spiderman.action.stand.*;
import com.zeml.rotp_spiderman.entity.stand.stands.SpiderManEntity;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction.Phase;
import com.github.standobyte.jojo.action.stand.StandEntityBlock;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), RotpSpiderManAddon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), RotpSpiderManAddon.MOD_ID);
    
 // ======================================== Spider-Man ========================================
    
    public static final RegistryObject<StandEntityAction> SPIDER_MAN_PUNCH = ACTIONS.register("spider_man_punch", 
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.SPIDER_MAN_PUNCH_LIGHT)
                    .standSound(Phase.WINDUP, InitSounds.SPIDER_MAN_ORA)));


    public static final RegistryObject<StandEntityAction> SPIDER_MAN_BARRAGE = ACTIONS.register("spider_man_barrage",
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(InitSounds.SPIDER_MAN_BARRAGE)
                    .standSound(InitSounds.SPIDER_MAN_ORA_ORA_ORA)));


    public static final RegistryObject<StandEntityHeavyAttack> SPIDER_MAN_FINISHER = ACTIONS.register("spider_man_finisher",
            ()->new SMFinisher(new StandEntityHeavyAttack.Builder().resolveLevelToUnlock(1).partsRequired(StandPart.ARMS)
                    .standPerformDuration(30)
            ));


    public static final RegistryObject<StandEntityHeavyAttack> SPIDER_MAN_HEAVY_PUNCH = ACTIONS.register("spider_man_heavy_punch",
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .punchSound(InitSounds.SPIDER_MAN_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, InitSounds.SPIDER_MAN_ORA_LONG)
                    .partsRequired(StandPart.ARMS).setFinisherVariation(SPIDER_MAN_FINISHER)
                    .shiftVariationOf(SPIDER_MAN_PUNCH).shiftVariationOf(SPIDER_MAN_BARRAGE)
                    ));

    public static final RegistryObject<StandEntityAction> SPIDER_MAN_SHOOT = ACTIONS.register("spider_man_shoot",
            () -> new SMWebShoot(new StandEntityAction.Builder().staminaCost(10).standPerformDuration(10)
                    .cooldown(2, 1, 0.5F)
                    .partsRequired(StandPart.ARMS).standOffsetFront().standPose(StandPose.RANGED_ATTACK)
                    .standSound(InitSounds.WEB_SHOOT)
            ));

    public static final RegistryObject<StandEntityAction> SPIDER_MAN_WEB_BURST = ACTIONS.register("spider_man_burst",
            ()->new WebBurst(new StandEntityAction.Builder().staminaCostTick(10).partsRequired(StandPart.ARMS)
                    .shiftVariationOf(SPIDER_MAN_SHOOT).standOffsetFront().holdType().standRecoveryTicks(10)
                    .standPose(StandPose.RANGED_ATTACK).resolveLevelToUnlock(1).cooldown(40,3,0.5F)));

    
    public static final RegistryObject<StandEntityAction> SPIDER_MAN_STRING_BIND = ACTIONS.register("spider_man_attack_binding",
            () -> new WebAttack(new StandEntityHeavyAttack.Builder().staminaCost(75).standPerformDuration(25)
                    .cooldown(25, 100, 0.5F)
                    .partsRequired(StandPart.ARMS).punchSound(InitSounds.SILENCIO)
                    .standOffsetFront().standPose(StandPose.RANGED_ATTACK).standSound(InitSounds.WEB_SHOOT)
                    ));


    public static final RegistryObject<StandEntityAction> SPIDER_MAN_BLOCK = ACTIONS.register("spider_man_block",
            () -> new StandEntityBlock());
    
    public static final RegistryObject<StandEntityAction> SPIDER_MAN_GRAPPLE = ACTIONS.register("spider_man_grapple",
            () -> new WebGrapple(new StandEntityAction.Builder().holdType().standUserWalkSpeed(1.0F)
                    .resolveLevelToUnlock(2).standSound(InitSounds.WEB_SHOOT)
                    .standPose(StandPose.RANGED_ATTACK).standOffsetFromUser(-0.5, 0.25)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> SPIDER_MAN_GRAPPLE_ENTITY = ACTIONS.register("spider_man_grapple_entity",
            () -> new WebGrapple(new StandEntityAction.Builder().staminaCostTick(1).holdType().standUserWalkSpeed(1.0F)
                    .resolveLevelToUnlock(2).standSound(InitSounds.WEB_SHOOT)
                    .standPose(StandPose.RANGED_ATTACK)
                    .partsRequired(StandPart.ARMS)
                    .shiftVariationOf(SPIDER_MAN_GRAPPLE)));
    
    public static final RegistryObject<StandEntityAction> SPIDER_MAN_BARRIER = ACTIONS.register("spider_man_barrier",
            () -> new WebBarrier(new StandEntityAction.Builder().standPose(StandPose.RANGED_ATTACK)
                    .resolveLevelToUnlock(3).standSound(InitSounds.WEB_SHOOT)
                    .partsRequired(StandPart.MAIN_BODY)));

    public static final RegistryObject<StandEntityAction> SPIDER_SENSE = ACTIONS.register("spider_sense",
            ()-> new SpiderSense(new StandEntityHeavyAttack.Builder().cooldown(100).staminaCost(100).resolveLevelToUnlock(4)
                    .standPerformDuration(10))
                    );


    public static final RegistryObject<StandEntityAction> SPIDER_MAN_WALL = ACTIONS.register("spider_man_climb",
            ()-> new SMWallClimb(new StandEntityAction.Builder().staminaCostTick(2F)));

    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<SpiderManEntity>> STAND_SPIDER_MAN =
            new EntityStandRegistryObject<>("spider_man",
                    STANDS, 
                    () -> new EntityStandType<StandStats>(
                            0x1047C5, ModStandsInit.PART_6_NAME,

                            new StandAction[] {
                                    SPIDER_MAN_PUNCH.get(), 
                                    SPIDER_MAN_BARRAGE.get(),
                                    SPIDER_MAN_SHOOT.get(),
                                    SPIDER_SENSE.get(),
                                    },
                            new StandAction[] {
                                    SPIDER_MAN_BLOCK.get(),
                                    SPIDER_MAN_WALL.get(),
                                    SPIDER_MAN_GRAPPLE.get(),
                                    SPIDER_MAN_BARRIER.get()
                                    },

                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(16.0)
                            .speed(12.0)
                            .range(3.0, 15.0)
                            .durability(15.0)
                            .precision(10.0)
                            .build("Spider Man"),

                            new StandType.StandTypeOptionals()
                            .addSummonShout(InitSounds.PETER_SPIDER_MAN)
                            .addOst(InitSounds.SPIDER_MAN_OST)),

                    InitEntities.ENTITIES, 
                    () -> new StandEntityType<SpiderManEntity>(SpiderManEntity::new, 0.65F, 1.95F)
                    .summonSound(InitSounds.SPIDER_MAN_SUMMON)
                    .unsummonSound(InitSounds.SPIDER_MAN_UNSUMMON))
            .withDefaultStandAttributes();
}

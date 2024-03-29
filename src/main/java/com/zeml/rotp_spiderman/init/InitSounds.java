package com.zeml.rotp_spiderman.init;

import java.util.function.Supplier;

import com.zeml.rotp_spiderman.RotpSpiderManAddon;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.util.mc.OstSoundList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RotpSpiderManAddon.MOD_ID);
    
    public static final RegistryObject<SoundEvent> SILENCIO = SOUNDS.register("silencio", 
            () -> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "silencio")));
    
    public static final RegistryObject<SoundEvent> PETER_SPIDER_MAN = SOUNDS.register("peter_spider_man",
            () -> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "peter_spider_man")));

    public static final Supplier<SoundEvent> SPIDER_MAN_SUMMON = SOUNDS.register("spiderman_summon",
            ()-> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID,"spiderman_summon")));
    
    public static final Supplier<SoundEvent> SPIDER_MAN_UNSUMMON =SOUNDS.register("spiderman_umsummon",
            ()-> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID,"spiderman_unsummon")));
    
    public static final Supplier<SoundEvent> SPIDER_MAN_PUNCH_LIGHT = SOUNDS.register("spider_punch",
            ()-> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID,"spider_punch")));
    
    public static final Supplier<SoundEvent> SPIDER_MAN_PUNCH_HEAVY = SPIDER_MAN_PUNCH_LIGHT;
    
    public static final Supplier<SoundEvent> SPIDER_MAN_BARRAGE = ModSounds.STAND_PUNCH_LIGHT;
    
    public static final RegistryObject<SoundEvent> SPIDER_MAN_ORA = SOUNDS.register("stone_free_ora",
            () -> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "stone_free_ora")));
    
    public static final RegistryObject<SoundEvent> SPIDER_MAN_ORA_LONG = SOUNDS.register("stone_free_ora_long",
            () -> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "stone_free_ora_long")));
    
    public static final RegistryObject<SoundEvent> SPIDER_MAN_ORA_ORA_ORA = SOUNDS.register("stone_free_ora_ora_ora",
            () -> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "stone_free_ora_ora_ora")));
    
    public static final RegistryObject<SoundEvent> WEB_SHOOT = SOUNDS.register("spiderman_web_shoot",
            () -> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "spiderman_web_shoot")));
    
    public static final RegistryObject<SoundEvent> SPIDER_MAN_BARRIER_PLACED = SOUNDS.register("spider_man_barrier_placed",
            () -> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "spiderman_web_hit")));
    
    public static final RegistryObject<SoundEvent> SPIDER_MAN_BARRIER_RIPPED = SOUNDS.register("web_ripped",
            () -> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "web_ripped")));
    
    public static final RegistryObject<SoundEvent> SPIDER_MAN_GRAPPLE_CATCH = SOUNDS.register("stone_free_grapple_catch",
            () -> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "spiderman_web_hit")));

    public static final RegistryObject<SoundEvent> SPIDER_MAN_WEB_HIT = SOUNDS.register("spiderman_web_hit",
            ()-> new SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID,"spiderman_web_hit")));


    public static final RegistryObject<SoundEvent> SPIDER_SENSE = SOUNDS.register("spider_sense",()->new
            SoundEvent(new ResourceLocation(RotpSpiderManAddon.MOD_ID,"spider_sense")));
    
    static final OstSoundList SPIDER_MAN_OST = new OstSoundList(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "spider_man_ost"), SOUNDS);

}

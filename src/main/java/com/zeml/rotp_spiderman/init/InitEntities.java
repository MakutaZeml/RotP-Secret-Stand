package com.zeml.rotp_spiderman.init;

import com.zeml.rotp_spiderman.RotpSpiderManAddon;
import com.zeml.rotp_spiderman.entity.damaging.projectile.WebShootHEntity;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebBarrierEntity;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebGrapplingEntity;
import com.zeml.rotp_spiderman.entity.damaging.projectile.ownerbound.WebStringEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RotpSpiderManAddon.MOD_ID);
    
    

    public static final RegistryObject<EntityType<WebStringEntity>> SF_STRING = ENTITIES.register("sf_string",
            () -> EntityType.Builder.<WebStringEntity>of(WebStringEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setUpdateInterval(20)
            .build(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "sf_string").toString()));
    
    public static final RegistryObject<EntityType<WebGrapplingEntity>> SF_GRAPPLING_STRING = ENTITIES.register("sf_grappling_string",
            () -> EntityType.Builder.<WebGrapplingEntity>of(WebGrapplingEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setUpdateInterval(20)
            .build(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "sf_grappling_string").toString()));
    
    public static final RegistryObject<EntityType<WebBarrierEntity>> SF_BARRIER = ENTITIES.register("sf_barrier",
            () -> EntityType.Builder.<WebBarrierEntity>of(WebBarrierEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setShouldReceiveVelocityUpdates(false).setUpdateInterval(Integer.MAX_VALUE)
            .build(new ResourceLocation(RotpSpiderManAddon.MOD_ID, "sf_barrier").toString()));

    public static final RegistryObject<EntityType<WebShootHEntity>> WEB_SHOT= ENTITIES.register("web_shot",
            ()->EntityType.Builder.<WebShootHEntity>of(WebShootHEntity:: new, EntityClassification.MISC).sized(.15F,.15F)
                    .setUpdateInterval(10).build(new ResourceLocation(RotpSpiderManAddon.MOD_ID,"web_shot").toString()));

    public static final RegistryObject<EntityType<WebShootHEntity>> WEB_SHOT_H = ENTITIES.register("web_shot_h",
            ()->EntityType.Builder.<WebShootHEntity>of(WebShootHEntity :: new, EntityClassification.MISC).sized(.15F,15F)
                    .setUpdateInterval(10).build(new ResourceLocation(RotpSpiderManAddon.MOD_ID,"web_shot").toString()));
}

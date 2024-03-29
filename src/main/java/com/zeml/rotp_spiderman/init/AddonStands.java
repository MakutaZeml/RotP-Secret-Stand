package com.zeml.rotp_spiderman.init;

import com.zeml.rotp_spiderman.entity.stand.stands.SpiderManEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject.EntityStandSupplier;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;

public class AddonStands {

    public static final EntityStandSupplier<EntityStandType<StandStats>, StandEntityType<SpiderManEntity>>
            SPIDER_MAN = new EntityStandSupplier<>(InitStands.STAND_SPIDER_MAN);
}

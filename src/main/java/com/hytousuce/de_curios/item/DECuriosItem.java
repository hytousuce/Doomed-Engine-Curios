package com.hytousuce.de_curios.item;

import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public abstract class DECuriosItem extends Item implements ICurioItem {
    public DECuriosItem(Properties properties) {
        super(properties.stacksTo(1).defaultDurability(0));
    }

    public DECuriosItem() {
        super(new Item.Properties().stacksTo(1).defaultDurability(0));
    }
}

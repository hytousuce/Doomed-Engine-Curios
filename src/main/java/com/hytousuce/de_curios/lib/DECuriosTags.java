package com.hytousuce.de_curios.lib;

import com.hytousuce.de_curios.DECuriosConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DECuriosTags {

    public static class Items {
        private static TagKey<Item> tag(String tag){
            return TagKey.create(Registries.ITEM, new ResourceLocation(DECuriosConstants.MODID, tag));
        }
        public static final TagKey<Item> CURIOS = tag("curios");
    }
}

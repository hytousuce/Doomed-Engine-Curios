package com.hytousuce.de_curios.item.curio;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hytousuce.de_curios.item.DECuriosItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class KimenItem extends DECuriosItem {

    public KimenItem() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = ArrayListMultimap.create();
        if (slotContext.identifier().equals("head")){
            modifierMultimap.put(Attributes.FOLLOW_RANGE, new AttributeModifier(uuid, "kimen:generic.follow_range", -0.3, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return modifierMultimap;
    }
}

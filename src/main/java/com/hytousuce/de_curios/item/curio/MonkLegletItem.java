package com.hytousuce.de_curios.item.curio;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hytousuce.de_curios.DECuriosConstants;
import com.hytousuce.de_curios.item.DECuriosItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class MonkLegletItem extends DECuriosItem {
    private final static int effectGiveCD = 3 * DECuriosConstants.tickPerSecond;
    private final static int effectLast = 4 * DECuriosConstants.tickPerSecond;
    private int effectGiveCount = effectGiveCD;

    public MonkLegletItem() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!(slotContext.entity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;

        if (effectGiveCount >= effectGiveCD) {
            effectGiveCount = 0;
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, effectLast));
        } else effectGiveCount ++;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.getItem() == prevStack.getItem()) return;
        effectGiveCount = effectGiveCD;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = ArrayListMultimap.create();
        if (slotContext.identifier().equals("feet")){
            modifierMultimap.put(Attributes.LUCK, new AttributeModifier(uuid, "monk_leglet:generic.luck", 1, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "monk_leglet:generic.movement_speed", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return modifierMultimap;
    }
}

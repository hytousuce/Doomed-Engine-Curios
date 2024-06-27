package com.hytousuce.de_curios.item.curio;

import com.hytousuce.de_curios.DECuriosConstants;
import com.hytousuce.de_curios.event.LivingEntityFoodEatenEvent;
import com.hytousuce.de_curios.item.DECuriosItem;
import com.hytousuce.de_curios.registry.DECuriosItems;
import com.hytousuce.de_curios.utils.EntityUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class GluttonyNecklaceItem extends DECuriosItem {
    private static final int absorptionEffectLast = 15 * DECuriosConstants.tickPerSecond;
    private static final int regenerationEffectLast = 5 * DECuriosConstants.tickPerSecond;

    public GluttonyNecklaceItem() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Mod.EventBusSubscriber(modid = DECuriosConstants.MODID)
    public static class EventsHandler {
        @SubscribeEvent
        public static void onFoodEaten(LivingEntityFoodEatenEvent event){
            if(!(event.getEntity() instanceof Player player)) return;
            ItemStack curio = EntityUtils.findEquippedCurio(player, DECuriosItems.GLUTTONY_NECKLACE.get());
            if (!(curio.getItem() instanceof GluttonyNecklaceItem)) return;
            if (!player.level().isClientSide()) {
                if (event.getFoodItem().getItem() == Items.GOLDEN_APPLE) {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, regenerationEffectLast, 2));
                } else if(event.getFoodItem().getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 4 * regenerationEffectLast, 2));
                } else {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, regenerationEffectLast, 1));
                }
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, absorptionEffectLast));
            }
        }
    }
}

package com.hytousuce.de_curios.item.curio;

import com.hytousuce.de_curios.DECuriosConstants;
import com.hytousuce.de_curios.registry.DECuriosItems;
import com.hytousuce.de_curios.item.DECuriosItem;
import com.hytousuce.de_curios.utils.EntityUtils;
import com.hytousuce.de_curios.utils.NBTUtils;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;

import static com.hytousuce.de_curios.DECuriosConstants.tickPerSecond;


public class PhantasmalHoodItem extends DECuriosItem {

    private final static int initialCD = 30 * tickPerSecond;
    private final static int effectReloadCD = 3 * tickPerSecond;

    private final static int effectLastTicks = 4 * tickPerSecond;

    private static final String INITIAL_COUNT = "initial_count";
    private static final String EFFECT_RELOAD_COUNT = "effect_reload_count";


    public PhantasmalHoodItem() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!(slotContext.entity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;
        int initialCount = NBTUtils.getInt(stack, INITIAL_COUNT, 0);
        int effectReloadCount = NBTUtils.getInt(stack, EFFECT_RELOAD_COUNT, effectReloadCD);

        // 若 initialCount 小于 initialCD（即初始冷却还未到）则递增
        if (initialCount < initialCD) {
            NBTUtils.setInt(stack, INITIAL_COUNT, initialCount + 1);
            return;
        }
        // initialCount 达标，开始循环给效果
        if (effectReloadCount >= effectReloadCD) {
            // 此时给予效果并将计数器清零
            player.addEffect(new MobEffectInstance(MobEffectRegistry.TRUE_INVISIBILITY.get(), effectLastTicks));
            NBTUtils.setInt(stack, EFFECT_RELOAD_COUNT, 0);
        } else NBTUtils.setInt(stack, EFFECT_RELOAD_COUNT, effectReloadCount + 1);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.getItem() == prevStack.getItem()) return;
        NBTUtils.clearTag(stack, INITIAL_COUNT);
        NBTUtils.clearTag(stack, EFFECT_RELOAD_COUNT);
    }

    @Mod.EventBusSubscriber(modid = DECuriosConstants.MODID)
    public static class EventsHandler {
        private static void clearCurioTags(Player player) {
            ItemStack curio = EntityUtils.findEquippedCurio(player, DECuriosItems.PHANTASMAL_HOOD.get());
            if (!(curio.getItem() instanceof PhantasmalHoodItem)) return;

            // 将物品的两个冷却时间重置
            NBTUtils.clearTag(curio, INITIAL_COUNT);
            NBTUtils.clearTag(curio, EFFECT_RELOAD_COUNT);
            MobEffect trueInvisibility = MobEffectRegistry.TRUE_INVISIBILITY.get();
            if (player.hasEffect(trueInvisibility)) player.removeEffect(trueInvisibility);
        }

        @SubscribeEvent
        public static void onPlayerHurt(LivingHurtEvent event) {
            if (!(event.getEntity() instanceof Player player)) return;
            if (player.level().isClientSide()) return;
            clearCurioTags(player);
        }

        @SubscribeEvent
        public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
            if (event.getEntity().level().isClientSide()) return;
            clearCurioTags(event.getEntity());
        }
    }
}

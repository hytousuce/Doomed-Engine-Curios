package com.hytousuce.de_curios.utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class EntityUtils {
    public static ItemStack findEquippedCurio(Entity entity, Item item) {
        AtomicReference<ItemStack> result = new AtomicReference<>(ItemStack.EMPTY);
        if(!(entity instanceof Player player)) return result.get();

        LazyOptional<ICuriosItemHandler> handlerOptional = CuriosApi.getCuriosInventory(player);

        handlerOptional.ifPresent(handler -> {
            Optional<SlotResult> optional = handler.findFirstCurio(item);
            optional.ifPresent(slotResult -> result.set(slotResult.stack()));
        });

        return result.get();
    }
}

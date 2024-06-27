package com.hytousuce.de_curios.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class LivingEntityFoodEatenEvent extends LivingEvent {
    private final ItemStack foodItem;
    public LivingEntityFoodEatenEvent(LivingEntity entity, ItemStack food) {
        super(entity);
        this.foodItem = food;
    }

    public ItemStack getFoodItem() {
        return foodItem;
    }
}

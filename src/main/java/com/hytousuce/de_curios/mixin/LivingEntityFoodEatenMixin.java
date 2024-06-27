package com.hytousuce.de_curios.mixin;

import com.hytousuce.de_curios.event.LivingEntityFoodEatenEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityFoodEatenMixin {
    @Inject(method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"))
    public void eat(Level p_21067_, ItemStack p_21068_, CallbackInfoReturnable<ItemStack> cir){
        LivingEntityFoodEatenEvent event = new LivingEntityFoodEatenEvent((LivingEntity) (Object) this, p_21068_);
        MinecraftForge.EVENT_BUS.post(event);
    }
}

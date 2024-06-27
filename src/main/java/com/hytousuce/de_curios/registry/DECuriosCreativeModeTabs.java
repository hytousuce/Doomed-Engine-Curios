package com.hytousuce.de_curios.registry;

import com.hytousuce.de_curios.DECuriosConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = DECuriosConstants.MODID)
public class DECuriosCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DECuriosConstants.MODID);

    public static final RegistryObject<CreativeModeTab> DE_CURIOS_TAB = CREATIVE_MODE_TABS.register("de_curios", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.de_curios"))
            .icon(() -> DECuriosItems.PHANTASMAL_HOOD.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                // 在此加入物品
                output.accept(DECuriosItems.PHANTASMAL_HOOD.get());
                output.accept(DECuriosItems.KIMEN.get());
                output.accept(DECuriosItems.MONK_LEGLET.get());
                output.accept(DECuriosItems.GLUTTONY_NECKLACE.get());
            })
            .build());

    public static void register() {
        CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }
}

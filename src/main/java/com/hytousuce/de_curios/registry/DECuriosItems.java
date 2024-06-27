package com.hytousuce.de_curios.registry;

import com.hytousuce.de_curios.DECuriosConstants;
import com.hytousuce.de_curios.item.curio.GluttonyNecklaceItem;
import com.hytousuce.de_curios.item.curio.KimenItem;
import com.hytousuce.de_curios.item.curio.MonkLegletItem;
import com.hytousuce.de_curios.item.curio.PhantasmalHoodItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = DECuriosConstants.MODID)
public class DECuriosItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DECuriosConstants.MODID);

    // 所有的物品如下
    public static final RegistryObject<Item> PHANTASMAL_HOOD = ITEMS.register("phantasmal_hood", PhantasmalHoodItem::new);
    public static final RegistryObject<Item> KIMEN = ITEMS.register("kimen", KimenItem::new);
    public static final RegistryObject<Item> MONK_LEGLET = ITEMS.register("monk_leglet", MonkLegletItem::new);
    public static final RegistryObject<Item> GLUTTONY_NECKLACE = ITEMS.register("gluttony_necklace", GluttonyNecklaceItem::new);
    // 注册
    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}

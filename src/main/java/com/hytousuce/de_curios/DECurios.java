package com.hytousuce.de_curios;

import com.hytousuce.de_curios.data.DECuriosAdvancementProvider;
import com.hytousuce.de_curios.data.DECuriosCuriosDataProvider;
import com.hytousuce.de_curios.data.DECuriosItemModelProvider;
import com.hytousuce.de_curios.data.DECuriosItemTagsProvider;
import com.hytousuce.de_curios.registry.DECuriosCreativeModeTabs;
import com.hytousuce.de_curios.registry.DECuriosItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DECuriosConstants.MODID)
public class DECurios {

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // 主类
    public DECurios() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // 注册
        DECuriosItems.register();
        DECuriosCreativeModeTabs.register();

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("[DECurios] Server side starting...");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = DECuriosConstants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("[DECurios] HELLO FROM CLIENT SETUP");
        }
    }
    @Mod.EventBusSubscriber(modid = DECuriosConstants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class DataGenerateEvent {

        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            ExistingFileHelper efh = event.getExistingFileHelper();
            CompletableFuture<HolderLookup.Provider> lup = event.getLookupProvider();

            // 仅客户端生成的数据，包括基础物品模型、语言文件
            generator.addProvider(event.includeClient(), (DataProvider.Factory<DECuriosItemModelProvider>) output -> new DECuriosItemModelProvider(output, DECuriosConstants.MODID, efh));

            // 仅服务端生成的数据，包括进度、饰品数据
            generator.addProvider(event.includeServer(), (DataProvider.Factory<DECuriosItemTagsProvider>) output -> new DECuriosItemTagsProvider(output, lup, DECuriosConstants.MODID, efh));
            generator.addProvider(event.includeServer(), (DataProvider.Factory<ForgeAdvancementProvider>) output-> DECuriosAdvancementProvider.create(output,lup, efh));
            generator.addProvider(event.includeServer(), (DataProvider.Factory<DECuriosCuriosDataProvider>) output -> new DECuriosCuriosDataProvider(DECuriosConstants.MODID, output, efh, lup));
        }
    }
}

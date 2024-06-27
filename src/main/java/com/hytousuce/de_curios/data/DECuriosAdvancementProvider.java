package com.hytousuce.de_curios.data;

import com.hytousuce.de_curios.DECuriosConstants;
import com.hytousuce.de_curios.lib.DECuriosTags;
import com.hytousuce.de_curios.registry.DECuriosItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosTriggers;
import top.theillusivec4.curios.api.SlotPredicate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DECuriosAdvancementProvider {

    public static ForgeAdvancementProvider create(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        return new ForgeAdvancementProvider(packOutput, lookupProvider, existingFileHelper ,List.of(new DECuriosAdvancements()));
    }

    public static class DECuriosAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {

        private String getNameId(String id) {
            return DECuriosConstants.MODID + ":" + id;
        }
        @Override
        public void generate(HolderLookup.@NotNull Provider registries, @NotNull Consumer<Advancement> saver, @NotNull ExistingFileHelper existingFileHelper) {
            Advancement root = Advancement.Builder.advancement()
                    .addCriterion("get_a_de_curio", onPickUp(DECuriosTags.Items.CURIOS))
                    .display(DECuriosItems.PHANTASMAL_HOOD.get(), Component.translatable("advancement.de_curios.root"), Component.translatable("advancement.de_curios.root.desc"), new ResourceLocation("minecraft:textures/block/bricks.png"), FrameType.TASK, true, true, false)
                    .save(saver, getNameId("root"));

            Advancement.Builder.advancement()
                    .addCriterion("get_phantasmal_hood", onPickUp(DECuriosItems.PHANTASMAL_HOOD.get()))
                    .display(DECuriosItems.PHANTASMAL_HOOD.get(), Component.translatable("advancement.de_curios.phantasmal_hood"), Component.translatable("advancement.de_curios.phantasmal_hood.desc"),null , FrameType.TASK ,true, true, false)
                    .parent(root)
                    .save(saver, getNameId("phantasmal_hood"));

            Advancement.Builder.advancement()
                    .addCriterion("get_kimen", onPickUp(DECuriosItems.KIMEN.get()))
                    .display(DECuriosItems.KIMEN.get(), Component.translatable("advancement.de_curios.kimen"), Component.translatable("advancement.de_curios.kimen.desc"),null , FrameType.TASK ,true, true, false)
                    .parent(root)
                    .save(saver, getNameId("kimen"));

            Advancement.Builder.advancement()
                    .addCriterion("get_monk_leglet", onPickUp(DECuriosItems.MONK_LEGLET.get()))
                    .display(DECuriosItems.MONK_LEGLET.get(), Component.translatable("advancement.de_curios.monk_leglet"), Component.translatable("advancement.de_curios.monk_leglet.desc"),null , FrameType.TASK ,true, true, false)
                    .parent(root)
                    .save(saver, getNameId("monk_leglet"));

            Advancement getGluttonyNecklace = Advancement.Builder.advancement()
                    .addCriterion("get_gluttony_necklace", onPickUp(DECuriosItems.GLUTTONY_NECKLACE.get()))
                    .display(DECuriosItems.GLUTTONY_NECKLACE.get(), Component.translatable("advancement.de_curios.gluttony_necklace"), Component.translatable("advancement.de_curios.gluttony_necklace.desc"),null , FrameType.TASK ,true, true, false)
                    .parent(root)
                    .save(saver, getNameId("gluttony_necklace"));

//            Advancement.Builder.advancement()
//                    .addCriterion("wearing_gluttony_necklace", onEquipCurio("necklace", DECuriosItems.GLUTTONY_NECKLACE.get()))
//                    .addCriterion("eat_golden_apple", onEat(Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE))
//                    .requirements(RequirementsStrategy.AND)
//                    .display(Items.GOLDEN_APPLE, Component.translatable("advancement.de_curios.eat_golden_apple_with_gluttony_necklace"), Component.translatable("advancement.de_curios.eat_golden_apple_with_gluttony_necklace.desc"), null, FrameType.CHALLENGE, true, true, true)
//                    .parent(getGluttonyNecklace)
//                    .save(saver, getNameId("eat_golden_apple_with_gluttony_necklace"));
        }

        protected InventoryChangeTrigger.TriggerInstance onPickUp(TagKey<Item> tag) {
            return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(tag).build());
        }

        protected InventoryChangeTrigger.TriggerInstance onPickUp(ItemLike... items) {
            return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items).build());
        }

        protected ConsumeItemTrigger.TriggerInstance onEat(ItemLike... foods){
            return ConsumeItemTrigger.TriggerInstance.usedItem(ItemPredicate.Builder.item().of(foods).build());
        }
    }
}

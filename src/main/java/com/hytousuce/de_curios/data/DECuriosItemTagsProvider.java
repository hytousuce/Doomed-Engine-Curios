package com.hytousuce.de_curios.data;

import com.hytousuce.de_curios.lib.DECuriosTags;
import com.hytousuce.de_curios.registry.DECuriosItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DECuriosItemTagsProvider extends TagsProvider<Item> {

    public DECuriosItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lup, String modId, @Nullable ExistingFileHelper efh) {
        super(output, Registries.ITEM, lup, modId, efh);
    }


    @Override
    protected void addTags(HolderLookup.@NotNull Provider lookupProvider) {

        List<RegistryObject<Item>> curioRegObs =
                Arrays.asList(DECuriosItems.PHANTASMAL_HOOD,
                        DECuriosItems.KIMEN, DECuriosItems.MONK_LEGLET, DECuriosItems.GLUTTONY_NECKLACE);
        TagAppender<Item> curiosTag = this.tag(DECuriosTags.Items.CURIOS);
        for (RegistryObject<Item> curio : curioRegObs) {
            ResourceKey<Item> key = curio.getKey();
            if (key != null) curiosTag.add(key);
        }
    }
}

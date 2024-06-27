package com.hytousuce.de_curios.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;

import java.util.concurrent.CompletableFuture;

public class DECuriosCuriosDataProvider extends CuriosDataProvider {

    public DECuriosCuriosDataProvider(String modId, PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        this.createSlot("feet")
                .icon(new ResourceLocation("curios:slot/empty_feet_slot"))
                .size(2);

        this.createEntities("modifier")
                .addPlayer()
                .addSlots("head", "necklace", "feet");
    }
}

package com.hytousuce.de_curios.data;

import com.hytousuce.de_curios.registry.DECuriosItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DECuriosItemModelProvider extends ItemModelProvider {
    public DECuriosItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.basicItem(DECuriosItems.PHANTASMAL_HOOD.get());
        this.basicItem(DECuriosItems.KIMEN.get());
        this.basicItem(DECuriosItems.MONK_LEGLET.get());
        this.basicItem(DECuriosItems.GLUTTONY_NECKLACE.get());
    }
}

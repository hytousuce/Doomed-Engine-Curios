package com.hytousuce.de_curios.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class NBTUtils {
    public static void setBoolean(ItemStack stack, String tag, boolean value) {
        stack.getOrCreateTag().putBoolean(tag, value);
    }

    public static void setInt(ItemStack stack, String tag, int value) {
        stack.getOrCreateTag().putInt(tag, value);
    }

    public static void setLong(ItemStack stack, String tag, long value) {
        stack.getOrCreateTag().putLong(tag, value);
    }

    public static void setFloat(ItemStack stack, String tag, float value) {
        stack.getOrCreateTag().putFloat(tag, value);
    }

    public static void setDouble(ItemStack stack, String tag, double value) {
        stack.getOrCreateTag().putDouble(tag, value);
    }

    public static void setString(ItemStack stack, String tag, String value) {
        stack.getOrCreateTag().putString(tag, value);
    }

    public static void setCompound(ItemStack stack, String tag, CompoundTag value) {
        stack.getOrCreateTag().put(tag, value);
    }

    public static boolean getBoolean(ItemStack stack, String tag, boolean defaultValue) {
        return safeCheck(stack, tag) ? stack.getTag().getBoolean(tag) : defaultValue;
    }

    public static int getInt(ItemStack stack, String tag, int defaultValue) {
        return safeCheck(stack, tag) ? stack.getTag().getInt(tag) : defaultValue;
    }

    public static long getLong(ItemStack stack, String tag, long defaultValue) {
        return safeCheck(stack, tag) ? stack.getTag().getLong(tag) : defaultValue;
    }

    public static float getFloat(ItemStack stack, String tag, float defaultValue) {
        return safeCheck(stack, tag) ? stack.getTag().getFloat(tag) : defaultValue;
    }

    public static double getDouble(ItemStack stack, String tag, double defaultValue) {
        return safeCheck(stack, tag) ? stack.getTag().getDouble(tag) : defaultValue;
    }

    public static String getString(ItemStack stack, String tag, String defaultValue) {
        return safeCheck(stack, tag) ? stack.getTag().getString(tag) : defaultValue;
    }

    public static CompoundTag getCompound(ItemStack stack, String tag, CompoundTag defaultValue) {
        return safeCheck(stack, tag) ? stack.getTag().getCompound(tag) : defaultValue;
    }

    public static boolean safeCheck(ItemStack stack, String tag) {
        return !stack.isEmpty() && stack.getTag() != null && stack.getTag().contains(tag);
    }

    public static void clearTag(ItemStack stack, String tag) {
        stack.getOrCreateTag().remove(tag);
    }
}

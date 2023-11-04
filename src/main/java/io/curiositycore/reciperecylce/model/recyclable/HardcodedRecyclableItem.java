package io.curiositycore.reciperecylce.model.recyclable;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HardcodedRecyclableItem implements RecyclableItem{
    private Material itemType;
    private Material returnItemType;
    public HardcodedRecyclableItem(Material itemType, Material returnItemType) {
        this.itemType = itemType;
        this.returnItemType = returnItemType;
    }
    @Override
    public boolean isItemRecyclable(ItemStack itemToCheck) {
        return itemToCheck != null && itemToCheck.getType().equals(this.itemType);
    }

    @Override
    public ItemStack getReturnItem() {
        return new ItemStack(this.returnItemType);
    }
}

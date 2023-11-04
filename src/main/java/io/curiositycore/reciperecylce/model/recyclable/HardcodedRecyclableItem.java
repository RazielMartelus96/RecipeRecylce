package io.curiositycore.reciperecylce.model.recyclable;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * <p>A {@linkplain RecyclableItem Recyclable Item} that is initiated via a hardcoded material type for both the item and
 * its return item.</p>
 * <i>Utilised for the sake of example during the lesson, would usually be constructed from sort of
 * values within a config file.</i>
 */
public class HardcodedRecyclableItem implements RecyclableItem{
    /**
     * The material type of the item that can be recycled.
     */
    private Material itemType;

    /**
     * The material type of the item that is returned when the item is recycled.
     */
    private Material returnItemType;

    /**
     * Constructor that initializes the types for the recyclable item and its return item.
     * @param itemType The material type of the item that can be recycled.
     * @param returnItemType The material type of the item that is returned when the item is recycled.
     */
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

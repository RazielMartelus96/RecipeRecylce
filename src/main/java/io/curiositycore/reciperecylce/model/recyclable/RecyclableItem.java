package io.curiositycore.reciperecylce.model.recyclable;

import org.bukkit.inventory.ItemStack;

/**
 * Interface representing the general functionality of an item that can return a separate, related item if within a
 * recyclable recipe.
 */
public interface RecyclableItem {
    /**
     * Checks to see if the specified ItemStack is an instance of this recyclable item.
     * @param itemToCheck The item to check.
     * @return True if the item is recyclable, false if otherwise.
     */
    boolean isItemRecyclable(ItemStack itemToCheck);

    /**
     * Returns the item that represents the recycled item.
     * @return The return item.
     */
    ItemStack getReturnItem();
}

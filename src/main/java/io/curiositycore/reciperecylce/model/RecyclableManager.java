package io.curiositycore.reciperecylce.model;

import io.curiositycore.reciperecylce.model.recyclable.RecyclableItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * The recyclable manager is responsible for the registration and general functionality of recyclable items.
 */
public class RecyclableManager {
    /**
     * The singleton instance of the recyclable manager.
     */
    private static RecyclableManager instance;

    /**
     * The currently registered recyclable items.
     */
    private Set<RecyclableItem> recyclableItems = new HashSet<>();

    /**
     * Gets the instance of the recyclable manager, as per the singleton pattern.
     * @return
     */
    public static RecyclableManager getInstance(){
        if(instance == null){
            instance = new RecyclableManager();
        }
        return instance;
    }

    /**
     * Registers the given recyclable item.
     * @param recyclableItem The recyclable item to register.
     */
    public void register(RecyclableItem recyclableItem){
        this.recyclableItems.add(recyclableItem);
    }

    /**
     * Returns any items that represent a recyclable item in the given crafting recipe.
     * @param craftingRecipe The recipe to check
     * @return Set of recyclable items
     */
    public Set<ItemStack> recycle(ItemStack[] craftingRecipe){
        Set<ItemStack> recycledItems = new HashSet<>();

        for(ItemStack itemStack : craftingRecipe){
            try{
                ItemStack returnedItem = getPotentialRecyclableItem(itemStack);
                if(returnedItem != null){
                    addItemToSet(recycledItems, returnedItem);
                }
            }
            catch(NoSuchElementException e){
                Bukkit.getLogger().info("No recyclable item found for " + itemStack);
            }

        }
        return recycledItems;
    }

    /**
     * Returns the recyclable item if it exists, otherwise returns null
     * @param itemStack the item to check
     * @return The potential recyclable item.
     */
    private ItemStack getPotentialRecyclableItem(ItemStack itemStack) throws NoSuchElementException {
        RecyclableItem matchingRecyclableItem = this.recyclableItems
                .stream()
                .filter(recyclableItem -> recyclableItem
                        .isItemRecyclable(itemStack))
                .findFirst()
                .orElseThrow();

        return matchingRecyclableItem.getReturnItem();

    }

    /**
     * Adds the given item to the set of recycled items.
     * @param recycledItems The set of recycled items
     * @param itemStackToAdd The item to add to the set
     */
    private void addItemToSet(Set<ItemStack> recycledItems, ItemStack itemStackToAdd){
        if(recycledItems.contains(itemStackToAdd)){
            addExistingItemToStack(itemStackToAdd, recycledItems);
        }
        else{
            recycledItems.add(itemStackToAdd);
        }
    }

    /**
     * Adds to the amount of an existing item in the given set.
     * @param itemStack The item to add to the set
     * @param recycledItems The set of recycled items
     */
    private void addExistingItemToStack(ItemStack itemStack, Set<ItemStack> recycledItems){
        recycledItems
                .stream()
                .filter(recyclableItem -> recyclableItem
                        .isSimilar(itemStack))
                .findFirst()
                .ifPresentOrElse(
                        recyclableItem -> recyclableItem
                                .setAmount(recyclableItem.getAmount() + itemStack.getAmount()),
                        () -> recycledItems.add(itemStack)
                );
    }
}



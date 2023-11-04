package io.curiositycore.reciperecylce.listeners;

import io.curiositycore.reciperecylce.model.RecyclableManager;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

/**
 * Listener responsible for listening to crafting events and executing the appropriate recycling logic.
 */
public class CraftingListener implements Listener {

    /**
     * Listens to inventory click events, executing functionality if the click event results in a craftable item being
     * created.
     * @param event The click event.
     */
    @EventHandler
    public void onCraft(InventoryClickEvent event){
        if(event.getCurrentItem() == null){
            return;
        }

        if(event.getSlot() == 0 && event.getClickedInventory().getType().equals(InventoryType.WORKBENCH)){
            Set<ItemStack> recycledItems = RecyclableManager.getInstance()
                                                            .recycle(event.getClickedInventory().getContents());
            for(ItemStack itemStack : recycledItems){
                event.getWhoClicked().getInventory().addItem(itemStack);
            }
        }
    }
}



package io.curiositycore.reciperecylce;

import io.curiositycore.reciperecylce.listeners.CraftingListener;
import io.curiositycore.reciperecylce.model.RecyclableManager;
import io.curiositycore.reciperecylce.model.recyclable.HardcodedRecyclableItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class RecipeRecylce extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEvents();
        registerRecyclableItems();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Registers the events for the plugin.
     */
    private void registerEvents(){
        Bukkit.getLogger().info("Registering events");
        Bukkit.getPluginManager().registerEvents(new CraftingListener(), this);
        Bukkit.getLogger().info("Events registered successfully");
    }

    /**
     * Registers the recyclable items for the plugin. This registers the hardcoded recyclable items within this
     * class, but then uses a seperate class to grab the custom recyclable items from the config file.
     */
    private void registerRecyclableItems(){
        Bukkit.getLogger().info("Registering recyclable items");
        RecyclableManager.getInstance().register(new HardcodedRecyclableItem(Material.WET_SPONGE, Material.SPONGE));
        RecyclableManager.getInstance().register(new HardcodedRecyclableItem(Material.COBBLESTONE, Material.GRASS_BLOCK));

        //This is where the call to the config class would go.

        Bukkit.getLogger().info("Recyclable items registered successfully");
    }
}

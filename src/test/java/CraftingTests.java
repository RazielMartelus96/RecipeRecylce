import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import be.seeseemelk.mockbukkit.inventory.WorkbenchInventoryMock;
import io.curiositycore.reciperecylce.RecipeRecylce;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the crafting functionality of the plugin.
 */
public class CraftingTests {
    /**
     * Mocks that represents the server.
     */
    private ServerMock serverMock;
    /**
     * Mock that represents the world.
     */
    private WorldMock worldMock;
    /**
     * Mock that represents a player.
     */
    private PlayerMock playerMock;

    /**
     * Sets up testing by initialising a mock server, world, player and initialising the plugin.
     */
    @Before
    public void setUp(){
        this.serverMock = MockBukkit.getOrCreateMock();
        this.worldMock = this.serverMock.addSimpleWorld("world");
        this.playerMock = new PlayerMock(this.serverMock,"TestPlayer");

        MockBukkit.load(RecipeRecylce.class);
    }

    /**
     * Tests that, if a player crafts a recipe utilising a recycable item, the correct return item-type is added to the
     * player's inventory.
     */
    @Test
    public void testFurnaceCraftingEvent() {
        WorkbenchInventoryMock workbenchInventoryMock = new WorkbenchInventoryMock(this.playerMock);
        ItemStack cobblestone = new ItemStack(Material.COBBLESTONE);
        for (int slot = 1; slot <= 9; slot++) {
            if (slot != 5) { // Slot 5 is the center slot in a 3x3 (1-indexed here)
                workbenchInventoryMock.setItem(slot - 1, cobblestone); // -1 because array is 0-indexed
            }
        }
        this.playerMock.openInventory(workbenchInventoryMock);
        this.playerMock.simulateInventoryClick(this.playerMock.getOpenInventory(), ClickType.LEFT, 0);
        this.serverMock.getScheduler().performTicks(40);
        Assert.assertEquals(Material.GRASS_BLOCK, this.playerMock.getInventory().getItem(0).getType());
    }

    /**
     * Tests that, if a player crafts a recipe utilising a recycable item, the correct amount of return items are added
     * to the player's inventory.
     */
    @Test
    public void testFurnaceCraftingEventAmountCheck() {
        WorkbenchInventoryMock workbenchInventoryMock = new WorkbenchInventoryMock(this.playerMock);
        ItemStack cobblestone = new ItemStack(Material.COBBLESTONE);
        for (int slot = 1; slot <= 9; slot++) {
            if (slot != 5) { // Slot 5 is the center slot in a 3x3 (1-indexed here)
                workbenchInventoryMock.setItem(slot - 1, cobblestone); // -1 because array is 0-indexed
            }
        }
        this.playerMock.openInventory(workbenchInventoryMock);
        this.playerMock.simulateInventoryClick(this.playerMock.getOpenInventory(), ClickType.LEFT, 0);
        this.serverMock.getScheduler().performTicks(40);
        Assert.assertEquals(8, this.playerMock.getInventory().getItem(0).getAmount());
    }
}



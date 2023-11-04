import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.block.BlockMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import be.seeseemelk.mockbukkit.inventory.InventoryMock;
import be.seeseemelk.mockbukkit.inventory.WorkbenchInventoryMock;
import io.curiositycore.reciperecylce.RecipeRecylce;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ExampleTest {
    private ServerMock serverMock;
    private WorldMock worldMock;
    private PlayerMock playerMock;

    @Before
    public void setUp(){
        this.serverMock = MockBukkit.getOrCreateMock();
        this.worldMock = this.serverMock.addSimpleWorld("world");
        this.playerMock = new PlayerMock(this.serverMock,"TestPlayer");

        MockBukkit.load(RecipeRecylce.class);
    }

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



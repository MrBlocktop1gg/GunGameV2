package mrblock.gungamev2.lobby.gui;

import mrblock.gungamev2.utils.NameItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuGui {
  public static void openMenuGui(Player player) {
    Inventory menuGui = Bukkit.createInventory(player, 9, "Меню");
    menuGui.setItem(4, NameItems.createItem(new ItemStack(Material.MAGMA), "Классы"));
    player.openInventory(menuGui);
  }

}

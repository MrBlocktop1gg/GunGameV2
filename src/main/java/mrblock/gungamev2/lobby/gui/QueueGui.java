package mrblock.gungamev2.lobby.gui;

import mrblock.gungamev2.utils.NameItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class QueueGui {
  public static void queueOpenGui(Player player) {
    Inventory queueGui = Bukkit.createInventory(player, 9, "Очередь");
    queueGui.setItem(4, NameItems.createItem(new ItemStack(Material.PAPER), "Начать игру?"));
    player.openInventory(queueGui);
  }
}

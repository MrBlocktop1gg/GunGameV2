package mrblock.gungamev2.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * author _Mr_Block
 */
public class NameItems {
  public static ItemStack createItem(ItemStack itemStack, String name, String... lores) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    List<String> lore;

    itemMeta.setDisplayName(name);
    itemMeta.setUnbreakable(true);
    lore = new ArrayList<>();

    for (int i = 0; i < lores.length; i++) {
      lore.add(lores[i]);
    }

    itemMeta.setLore(lore);
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  public static ItemStack createItem(ItemStack itemStack, String name) {
    ItemMeta itemMeta = itemStack.getItemMeta();

    itemMeta.setDisplayName(name);
    itemMeta.setUnbreakable(true);

    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }
}

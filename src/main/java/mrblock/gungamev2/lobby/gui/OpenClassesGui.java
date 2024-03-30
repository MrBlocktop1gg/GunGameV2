package mrblock.gungamev2.lobby.gui;

import mrblock.gungamev2.game.classes.species.Necromancer;
import mrblock.gungamev2.game.classes.species.SlenderMan;
import mrblock.gungamev2.game.classes.species.Zeus;
import mrblock.gungamev2.utils.NameItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OpenClassesGui {
  public static void openClassGui(Player player) {
    Inventory classesGui = Bukkit.createInventory(player, 9, "Классы");

    classesGui.setItem(0, NameItems.createItem(
        new ItemStack(Material.SLIME_BALL),
        Necromancer.NECROMANCER_NAME,
        "§c§lПри активации спавнит 4 зомби рядом с противником!",
        "§c§lПри этом активируется кулдаун 120секунд!"));

    classesGui.setItem(2, NameItems.createItem(
        new ItemStack(Material.CHORUS_FRUIT),
        SlenderMan.SLENDERMAN_NAME,
        "§c§lУ вас есть предмет, нажав на него Вас телепортирует в рандомную точку",
        "§c§lПри этом активируется кулдаун 180секунд!"));

    classesGui.setItem(4, NameItems.createItem(
        new ItemStack(Material.END_ROD),
        Zeus.ZEUS_NAME,
        "§c§lПри активации в точку в которую вы смотрите ударит молния",
        "§c§lПри этом максимальная дистанция 20 блоков!",
        "При этом активируется кулдаун 40секунд!"));

    classesGui.setItem(6, NameItems.createItem(
        new ItemStack(Material.REDSTONE),
        SlenderMan.SLENDERMAN_NAME,
        "Вы вампир,",
        "§c§lПри этом активируется кулдаун 180секунд!"));

    classesGui.setItem(8, NameItems.createItem(
        new ItemStack(Material.END_ROD),
        Zeus.ZEUS_NAME,
        "§c§lПри активации в точку в которую вы смотрите ударит молния",
        "§c§lПри этом максимальная дистанция 20 блоков!",
        "При этом активируется кулдаун 40секунд!"));

    player.openInventory(classesGui);
  }
}

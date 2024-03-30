package mrblock.gungamev2.game.classes.timer;

import mrblock.gungamev2.GunGameV2;
import mrblock.gungamev2.game.State;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SelectClasses {
  public static final int FINAL_TIME = 20;
  public int timeGame;
  private final GunGameV2 gunGameV2;
  private final BossBar bar;
  private int taskid;


  public SelectClasses(GunGameV2 gunGameV2) {
    this.gunGameV2 = gunGameV2;

    bar = Bukkit.getServer().createBossBar("§4§lВыбор классов: " + FINAL_TIME, BarColor.GREEN, BarStyle.SEGMENTED_20);
    for (Player player : Bukkit.getOnlinePlayers()) {
      bar.addPlayer(player);
    }
  }

  public void gameTimer() {
    taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(gunGameV2, () -> {
      timeGame++;

      for (Player player : Bukkit.getOnlinePlayers()) {
        Inventory inventory = player.getInventory();

        if (timeGame >= FINAL_TIME) {
          gunGameV2.gunGameState.setState(State.GAME);

          player.closeInventory();
          player.setGameMode(GameMode.ADVENTURE);

          if (inventory.getItem(4).getType() == Material.ENDER_CHEST) {
            inventory.clear();
          }

          inventory.setItem(0, new ItemStack(Material.WOOD_SWORD));
          inventory.setItem(36, new ItemStack(Material.LEATHER_BOOTS));
          inventory.setItem(37, new ItemStack(Material.LEATHER_LEGGINGS));
          inventory.setItem(38, new ItemStack(Material.LEATHER_CHESTPLATE));

          bar.setProgress(0.0);
          bar.removePlayer(player);
          bar.removeAll();

          player.sendTitle("Игра началась", "Удачной игры!");
          Bukkit.getScheduler().cancelTask(taskid);
        } else {
          bar.setProgress((float) (FINAL_TIME - timeGame) / FINAL_TIME);
          bar.setTitle("§4§lВыбор классов: " + (FINAL_TIME - timeGame));

        }
      }
    }, 20, 20);
  }
}

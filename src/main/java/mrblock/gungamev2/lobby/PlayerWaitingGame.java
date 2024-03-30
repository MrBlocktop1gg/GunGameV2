package mrblock.gungamev2.lobby;

import mrblock.gungamev2.GunGameV2;
import mrblock.gungamev2.game.State;
import mrblock.gungamev2.game.classes.timer.SelectClasses;
import mrblock.gungamev2.utils.NameItems;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerWaitingGame {
  public static final int PLAYERS_MINIMUM = 1;
  private final GunGameV2 gunGameV2;
  private final SelectClasses selectClasses;
  private final PotionEffect effectSlow;
  private final PotionEffect effectBlindness;
  private final Location randomLocation;
  private int online = Bukkit.getOnlinePlayers().size();
  private int taskId;
  private int time = 11;
  private boolean starting = false;

  public PlayerWaitingGame(GunGameV2 gunGameV2) {
    this.gunGameV2 = gunGameV2;
    this.effectSlow = new PotionEffect(PotionEffectType.SLOW, 410, 10, true, false);
    this.effectBlindness = new PotionEffect(PotionEffectType.BLINDNESS, 410, 10, true, false);
    this.randomLocation = new Location(Bukkit.getWorld("world"), -225 + (Math.random() * 30), 115, 259 + (Math.random() * 30));

    selectClasses = new SelectClasses(gunGameV2);
  }

  public void startTimer() {
    taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(gunGameV2, () -> {
      time--;
      gunGameV2.gunGameState.setState(State.WAITING);

      if (Bukkit.getOnlinePlayers().size() < PLAYERS_MINIMUM) {
        for (Player player : Bukkit.getOnlinePlayers()) {
          player.sendTitle("", "Не достаточно игроков!");
          gunGameV2.gunGameState.setState(State.LOBBY);
        }
        starting = false;
        Bukkit.getScheduler().cancelTask(taskId);
        return;
      }

      for (Player player : Bukkit.getOnlinePlayers()) {
        if (time <= 5) {
          player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
          player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Начало игры через " + time + " секунд!"));
        }

        if (time == 0 && !starting) {
          starting = true;

          player.getInventory().clear();
          player.setHealth(player.getMaxHealth());
          player.setGameMode(GameMode.ADVENTURE);
          player.addPotionEffect(effectSlow);
          player.addPotionEffect(effectBlindness);
          player.teleport(randomLocation);
          player.sendTitle("", "Выберите класс!");
          player.getInventory().setItem(4, NameItems.createItem(new ItemStack(Material.ENDER_CHEST), "§c§lВыбор классов!"));

          selectClasses.gameTimer();
          Bukkit.getScheduler().cancelTask(taskId);

        } else {
          player.sendTitle("До начала игры: " + time, "");
        }
      }
    }, 0, 20);
  }
}
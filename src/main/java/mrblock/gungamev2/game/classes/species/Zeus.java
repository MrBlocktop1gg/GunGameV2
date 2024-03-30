package mrblock.gungamev2.game.classes.species;

import mrblock.gungamev2.GunGameV2;
import mrblock.gungamev2.game.classes.Skills;
import mrblock.gungamev2.utils.NameItems;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static org.bukkit.Bukkit.getServer;

public class Zeus extends Skills {
  public static final String ZEUS_NAME = "Зевс";
  private final GunGameV2 gunGameV2;
  private final World world = Bukkit.getWorld("world");
  private final Team teamZeus;
  private int taskid;
  private int time;

  public Zeus(GunGameV2 gunGameV2) {
    this.gunGameV2 = gunGameV2;
    Scoreboard scoreboard = getServer().getScoreboardManager().getMainScoreboard();
    Team teamZeus = scoreboard.getTeam("Zeus");

    if (teamZeus == null)
      teamZeus = scoreboard.registerNewTeam("Zeus");
    teamZeus.setPrefix(ChatColor.WHITE + "§l" + "[ЗЕВС] ");

    this.teamZeus = teamZeus;
  }

  @Override
  public ItemStack itemSkill() {
    return NameItems.createItem(new ItemStack(Material.BLAZE_ROD), "Вызыватель молний!",
        "§c§lНажми на меня и вызовешь молнию!",
        "§c§lкулдаун 40сек!");
  }

  @Override
  public void useSkill(Player player) {
    if (player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
      Location targetLocation = player.getTargetBlock(null, 20).getLocation();

      if (teamZeus.getEntries().contains(player.getName())
          && player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
        coolDown(40);
        world.strikeLightning(targetLocation);
        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
      }
    }
  }

  @Override
  public int coolDown(int seconds) {
    time = seconds;
    taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(gunGameV2, () -> {
      time--;
      for (Player player : Bukkit.getOnlinePlayers()) {
        if (time <= 0 && (teamZeus.getEntries().contains(player.getName()))) {
          player.getInventory().setItem(4, itemSkill());
          Bukkit.getScheduler().cancelTask(taskid);
        }
      }
    }, 0, 20);
    return time;
  }

  @Override
  public void useUltimate(Player player) {

  }
  public void giveItem(Player player) {
    player.getInventory().setItem(4, itemSkill());
  }
  public void addPlayerSpecies(Player player) {
    teamZeus.addEntry(player.getName());
  }
}

package mrblock.gungamev2.game.classes.species;

import mrblock.gungamev2.GunGameV2;
import mrblock.gungamev2.game.classes.Skills;
import mrblock.gungamev2.utils.NameItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static org.bukkit.Bukkit.getServer;

public class SlenderMan extends Skills {
  public static final String SLENDERMAN_NAME = "Слендермен";
  private final GunGameV2 gunGameV2;
  private final Team teamSlenderman;
  private final PotionEffect effectSlow;
  private final PotionEffect effectBlindness;
  private final Location randomTeleportLocation;
  private int taskid;
  private int time;

  public SlenderMan(GunGameV2 gunGameV2) {
    this.gunGameV2 = gunGameV2;
    this.effectSlow = new PotionEffect(PotionEffectType.SLOW, 60, 10, true, false);
    this.effectBlindness = new PotionEffect(PotionEffectType.BLINDNESS, 60, 10, true, false);
    this.randomTeleportLocation = new Location(Bukkit.getWorld("world"), -225 + (Math.random() + 30), 115, 259 + (Math.random() + 30));

    Scoreboard scoreboard = getServer().getScoreboardManager().getMainScoreboard();
    Team teamSlenderman = scoreboard.getTeam("Slenderman");

    if (teamSlenderman == null)
      teamSlenderman = scoreboard.registerNewTeam("Slenderman");
    teamSlenderman.setPrefix(ChatColor.WHITE + "§l" + "[СЛЕНДЕР] ");

    this.teamSlenderman = teamSlenderman;
  }

  @Override
  public ItemStack itemSkill() {
    return NameItems.createItem(new ItemStack(Material.CHORUS_FRUIT_POPPED), "Телепортатор",
        "§c§lНажми на меня и будешь спасён!",
        "§c§lкулдаун 180сек!");
  }

  @Override
  public void useSkill(Player player) {
    if (teamSlenderman.getEntries().contains(player.getName())) {
      coolDown(180);
      player.sendMessage("§4§lВы были оглушены при телепортации!");
      player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
      player.teleport(randomTeleportLocation);
      player.addPotionEffect(effectSlow);
      player.addPotionEffect(effectBlindness);
    }
  }

  @Override
  public int coolDown(int seconds) {
    time = seconds;
    taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(gunGameV2, () -> {
      time--;
      for (Player player : Bukkit.getOnlinePlayers()) {
        if (time <= 0 && (teamSlenderman.getEntries().contains(player.getName()))) {
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
    teamSlenderman.addEntry(player.getName());
  }
}

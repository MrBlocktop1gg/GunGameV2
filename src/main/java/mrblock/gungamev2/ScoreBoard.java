package mrblock.gungamev2;

import mrblock.gungamev2.mongoDB.PlayerData;
import mrblock.gungamev2.mongoDB.PlayerStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoard {
  private final GunGameV2 gunGameV2;
  private final ScoreboardManager scoreboardManager;
  private final PlayerStorage playerStorage;
  public ScoreBoard(GunGameV2 gunGameV2) {
    this.gunGameV2 = gunGameV2;
    this.playerStorage = gunGameV2.getPlayerStorage();

    scoreboardManager = Bukkit.getScoreboardManager();

  //  updateScoreBoard();
  }
  public void createScoreBoard(Player player) {
    Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
    Objective objective = scoreboard.registerNewObjective("GunGame", "dummy");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName("   §b§lGunGame   ");
    Score money = objective.getScore("§6§lМонеты: ");
    money.setScore(0);
    Score wins = objective.getScore("§6§lПобеды: ");
    wins.setScore(0);
    Score kills = objective.getScore("§6§lУбийства: ");
    kills.setScore(0);

    player.setScoreboard(scoreboard);
  }

 /* public void updateScoreBoard() {
    Bukkit.getScheduler().runTaskTimer(gunGameV2, () -> {
      for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        PlayerData playerData = playerStorage.getStats(onlinePlayer.getUniqueId());
        Scoreboard scoreboard = onlinePlayer.getScoreboard();
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        Score money = objective.getScore("§6§lМонеты: ");
        money.setScore(playerData.getMoney());
        Score wins = objective.getScore("§6§lПобеды: ");
        wins.setScore(playerData.getWins());
        Score kills = objective.getScore("§6§lУбийства: ");
        kills.setScore(playerData.getKills());
      }
    }, 0, 20);
  } */
}

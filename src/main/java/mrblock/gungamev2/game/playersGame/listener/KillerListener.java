package mrblock.gungamev2.game.playersGame.listener;

import mrblock.gungamev2.game.playersGame.GameKiller;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillerListener implements Listener {
  private final GameKiller gameKiller;

  public KillerListener() {
    gameKiller = new GameKiller();
  }

  @EventHandler
  public void killerKillPlayer(PlayerDeathEvent event) {
    Player player = event.getEntity().getPlayer();
    gameKiller.giveItem(player);
  }
}

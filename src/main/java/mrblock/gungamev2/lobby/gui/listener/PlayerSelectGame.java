package mrblock.gungamev2.lobby.gui.listener;

import mrblock.gungamev2.GunGameV2;
import mrblock.gungamev2.lobby.PlayerWaitingGame;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerSelectGame implements Listener {
  private final PlayerWaitingGame playerWaitingGame;

  public PlayerSelectGame(GunGameV2 gunGameV2) {
    playerWaitingGame = new PlayerWaitingGame(gunGameV2);
  }

  @EventHandler
  public void playerSelectGame(InventoryClickEvent event) {
    Player player = (Player) event.getWhoClicked();
    if (event.getCurrentItem().getType() == null) return;

    if (event.getCurrentItem().getType() == Material.PAPER) {
      player.closeInventory();
      playerWaitingGame.startTimer();
    }
  }
}

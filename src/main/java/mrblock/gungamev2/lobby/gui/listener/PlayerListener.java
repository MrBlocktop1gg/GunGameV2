package mrblock.gungamev2.lobby.gui.listener;

import mrblock.gungamev2.GunGameV2;
import mrblock.gungamev2.ScoreBoard;
import mrblock.gungamev2.game.State;
import mrblock.gungamev2.lobby.gui.MenuGui;
import mrblock.gungamev2.lobby.gui.QueueGui;
import mrblock.gungamev2.utils.NameItems;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {
  private final GunGameV2 gunGameV2;
  private final ItemStack compass;
  private final ItemStack menu;
  private final ItemStack hub;
  private final Location location;
  private final ScoreBoard scoreBoard;

  public PlayerListener(GunGameV2 gunGameV2) {
    this.gunGameV2 = gunGameV2;
    this.compass = NameItems.createItem(new ItemStack(Material.COMPASS), "§6§lВойти в очередь!");
    this.menu = NameItems.createItem(new ItemStack(Material.CHEST), "§6§lМеню");
    this.hub = NameItems.createItem(new ItemStack(Material.MAGMA_CREAM), "§6§lВыйти в хаб");
    this.location = new Location(Bukkit.getWorld("world"), -237, 90, 113);

    scoreBoard = new ScoreBoard(gunGameV2);
  }

  @EventHandler
  public void playerGiveItem(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    player.getInventory().setItem(0, compass.clone());
    player.getInventory().setItem(4, menu.clone());
    player.getInventory().setItem(8, hub.clone());
  }

  @EventHandler
  public void playerConnect(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    scoreBoard.createScoreBoard(player);

    if (gunGameV2.gunGameState.getState() == State.GAME) {
      player.setGameMode(GameMode.SPECTATOR);
      player.sendMessage("Игра уже начата, вы стали наблюдающим!");
    }

    player.teleport(location);
  }

  @EventHandler
  public void playerClickItem(PlayerInteractEvent event) {
    Player player = event.getPlayer();

    if (player.getInventory().getItemInMainHand().getType() == compass.getType()) {
      QueueGui.queueOpenGui(player);
    }

    if (player.getInventory().getItemInMainHand().getType() == menu.getType()) {
      MenuGui.openMenuGui(player);
    }

    if (player.getInventory().getItemInMainHand().getType() == hub.getType()) {
      player.kickPlayer("§2§lВы вышли в хаб :)");
    }
  }

  @EventHandler
  public void playerLeave(PlayerQuitEvent event) {
    Player player = event.getPlayer();

  }

  @EventHandler
  public void playerClickInventoryItem(InventoryClickEvent event) {

  }
}

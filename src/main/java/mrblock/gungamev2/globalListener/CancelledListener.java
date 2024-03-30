package mrblock.gungamev2.globalListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class CancelledListener implements Listener {
  @EventHandler(priority = EventPriority.LOWEST)
  public void cancelClickItems(InventoryClickEvent event) {
    event.setCancelled(true);
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void cancelDropItems(PlayerDropItemEvent event) {
    event.setCancelled(true);
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void cancelRain(WeatherChangeEvent event) {
    event.setCancelled(true);
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void cancelHunger(FoodLevelChangeEvent event) {
    event.setCancelled(true);
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void cancelMobSpawn(EntitySpawnEvent event) {
    event.setCancelled(true);
  }
  @EventHandler
  public void cancelBlockPlace(BlockPlaceEvent event) {
    event.setCancelled(true);
  }
}

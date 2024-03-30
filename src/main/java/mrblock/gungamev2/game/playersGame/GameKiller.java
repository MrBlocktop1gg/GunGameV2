package mrblock.gungamev2.game.playersGame;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameKiller {
  private final Gson gson = new Gson();
  private final Location playerFinalGameLocation;

  public GameKiller() {
    playerFinalGameLocation = new Location(Bukkit.getWorld("world"), -237, 90, 113);
  }
  public void giveItem(Player player) {
    Player killer = player.getKiller();
    Inventory inventory = killer.getInventory();

    JsonObject json = gson.fromJson("config.yml", JsonObject.class);

    JsonObject levelData = json.getAsJsonObject(Integer.toString(killer.getLevel()));
    JsonArray items = levelData.getAsJsonArray("items");
    for (int i = 0; i < items.size(); i++) {
      JsonObject item = items.get(i).getAsJsonObject();
      int slot = item.get("slot").getAsInt();
      Material material = Material.valueOf(item.get("material").getAsString());
      inventory.setItem(slot, new ItemStack(material));
    }

    if (killer.getLevel() >= 10) {
      for (Player players : Bukkit.getOnlinePlayers()) {
     //   playerData.addMoney();
       // playerData.addWins();
        players.setLevel(0);
        players.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.10);
        players.getInventory().clear();
        players.sendTitle("§4§lИгра была завершена!", "§5§lПобедитель: "
            + killer.getName(), 8, 125, 8);
        players.teleport(playerFinalGameLocation);
        Bukkit.getScheduler().cancelAllTasks();
      }
    }
  }
}

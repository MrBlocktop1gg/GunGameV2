package mrblock.gungamev2.game.classes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class Skills {
  public abstract ItemStack itemSkill();
  public abstract void useSkill(Player player);
  public abstract int coolDown(int seconds);
  public abstract void useUltimate(Player player);

}

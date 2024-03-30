package mrblock.gungamev2.globalListener;

import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FastPvp implements Listener {

  @EventHandler
  public void increaseAttackSpeed(EntityDamageByEntityEvent event) {
    Player player = ((Player) event.getDamager());
    if (EnchantmentTarget.TOOL.includes(player.getItemInHand()) || EnchantmentTarget.WEAPON.includes(player.getItemInHand()))
      player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(15);
  }
}

package com.stelch.games2.Handlers;

import com.stelch.games2.Util.Item;
import com.stelch.games2.Util.Item.click;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class onClick
        implements Listener {
  public static HashMap < ItemStack, Item.click > Actions = new HashMap();
  public static HashMap < Entity, Item.click > Entities = new HashMap();

  @EventHandler
  public void InventoryClickEvent(InventoryClickEvent e) {
    for (Map.Entry < ItemStack, Item.click > is: Actions.entrySet()) {
      if (((ItemStack) is.getKey()).equals(e.getCurrentItem())) {
        ((Item.click) is.getValue()).run((Player) e.getWhoClicked());
        e.setCancelled(true);
        return;
      }
    }
  }

  @EventHandler
  public void PlayerClickEvent(PlayerInteractEvent e) {
    if (Actions.containsKey(e.getPlayer().getItemInHand())) {
      ((Item.click) Actions.get(e.getPlayer().getItemInHand())).run(e.getPlayer());
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void PlayerClickEntityEvent(PlayerInteractEntityEvent e) {
    if (Entities.containsKey(e.getRightClicked())) {
      ((Item.click) Entities.get(e.getRightClicked())).run(e.getPlayer());
      e.setCancelled(true);
    }
  }
}


/* Location:              C:\Users\Ryan Wood\Desktop\StelchCore.jar!\com\stelch\games2\Handlers\onClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
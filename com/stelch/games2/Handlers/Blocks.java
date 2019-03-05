package com.stelch.games2.Handlers;

import com.stelch.games2.Game.Game;
import com.stelch.games2.Varables.GameState;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class Blocks
        implements Listener {
  public static Map < Location, Material > blocks = new HashMap();
  public static Map < Location, Byte > block_data = new HashMap();

  @EventHandler
  public void onEntityExplode(EntityExplodeEvent e) {
    if (Game.Game.getGameState() == GameState.IN_GAME) {
      for (Block b: e.blockList()) {
        if (blocks.containsKey(b.getLocation())) {
          b.setType(Material.AIR);
          blocks.put(b.getLocation(), Material.AIR);
        }
      }
      e.setCancelled(true);
    } else {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onBlockplace(BlockPlaceEvent e) {
    if (Game.Game.getGameState() == GameState.IN_GAME) {
      Block b = e.getBlock();
      blocks.put(b.getLocation(), Material.AIR);
      if (e.getBlock().getType() == Material.TNT) {
        e.getBlock().setType(Material.AIR);
        e.getPlayer().getWorld().spawn(e.getBlock().getLocation(), TNTPrimed.class);
      }
    } else if (!e.getPlayer().hasPermission("stelch.world.modify")) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onBlockbreak(BlockBreakEvent e) {
    if (Game.Game.getGameState() == GameState.IN_GAME) {
      Block b = e.getBlock();
      if (blocks.containsKey(b.getLocation())) {
        return;
      }
      blocks.put(b.getLocation(), b.getType());
      block_data.put(b.getLocation(), Byte.valueOf(b.getData()));
    } else if (!e.getPlayer().hasPermission("stelch.world.modify")) {
      e.setCancelled(true);
    }
  }
}


/* Location:              C:\Users\Ryan Wood\Desktop\StelchCore.jar!\com\stelch\games2\Handlers\Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
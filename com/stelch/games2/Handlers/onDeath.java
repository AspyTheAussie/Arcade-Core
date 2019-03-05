package com.stelch.games2.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Game.Game;
import com.stelch.games2.Main;
import com.stelch.games2.Varables.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.PluginManager;

public class onDeath implements Listener
{
  List messages = new ArrayList();
  
  public onDeath(Main main) {
    messages.add("%player% was fucked by %killer%");
    messages.add("%player% was brutally raped by %killer%");
  }
  
  @EventHandler
  public void death(EntityDamageByEntityEvent e) {
    if (!Game.Game.getGameState().equals(GameState.IN_GAME)) { e.setCancelled(true);return; }
    if (((e.getEntity() instanceof Player)) && 
      (((Player)e.getEntity()).getPlayer().getHealth() - e.getDamage() < 1.0D)) {
      Bukkit.getServer().getPluginManager().callEvent(new PlayerPostDeathEvent(((Player)e.getEntity()).getPlayer(), e.getDamager()));
      ((Player)e.getEntity()).setHealthScale(20.0D);
      ((Player)e.getEntity()).setGameMode(GameMode.SPECTATOR);
      if (Game.deaths.containsKey(((Player)e.getEntity()).getPlayer())) {
        Game.deaths.replace(((Player)e.getEntity()).getPlayer(), Integer.valueOf(Game.deaths.size() + 1));
      } else {
        Game.deaths.put(((Player)e.getEntity()).getPlayer(), Integer.valueOf(1));
      }
      if (Game.kills.containsKey(((Player)e.getDamager()).getPlayer())) {
        Game.kills.replace(((Player)e.getDamager()).getPlayer(), Integer.valueOf(Game.deaths.size() + 1));
      } else {
        Game.kills.put(((Player)e.getDamager()).getPlayer(), Integer.valueOf(1));
      }
      e.setCancelled(true);
      Text.sendAll("%player% was killed by %killer%".replaceAll("%player%", ((Player)e.getEntity()).getDisplayName()).replaceAll("%killer%", ((Player)e.getDamager()).getDisplayName()), Text.MessageType.TEXT_CHAT);
    }
  }
  

  @EventHandler
  public void onDeath(EntityDamageEvent e)
  {
    if (e.getEntityType() != EntityType.PLAYER) return;
    if ((e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) && 
      (((Player)e.getEntity()).getPlayer().getHealth() - e.getDamage() < 1.0D)) {
      Bukkit.getServer().getPluginManager().callEvent(new PlayerPostDeathEvent(((Player)e.getEntity()).getPlayer(), null));
      ((Player)e.getEntity()).setHealthScale(20.0D);
      ((Player)e.getEntity()).setGameMode(GameMode.SPECTATOR);
      if (Game.deaths.containsKey(((Player)e.getEntity()).getPlayer())) {
        Game.deaths.replace(((Player)e.getEntity()).getPlayer(), Integer.valueOf(Game.deaths.size() + 1));
      } else {
        Game.deaths.put(((Player)e.getEntity()).getPlayer(), Integer.valueOf(1));
      }
      e.getEntity().teleport(Bukkit.getServer().getWorld("world").getSpawnLocation().add(0.0D, -5.0D, 0.0D));
      e.setCancelled(true);
    }
  }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Handlers\onDeath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
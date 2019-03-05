package com.stelch.games2.Handlers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerPostDeathEvent
  extends Event
{
  private Player player;
  private Entity attacker;
  
  public PlayerPostDeathEvent(Player p, Entity attacker)
  {
    this.player = p;this.attacker = attacker;
  }
  
  public Player getPlayer()
  {
    return this.player;
  }
  
  public Entity getAttacker()
  {
    return this.attacker;
  }
  
  private static final HandlerList handlers = new HandlerList();
  
  public HandlerList getHandlers()
  {
    return handlers;
  }
  
  public static HandlerList getHandlerList()
  {
    return handlers;
  }
}


/* Location:              C:\Users\Ryan Wood\Desktop\StelchCore.jar!\com\stelch\games2\Handlers\PlayerPostDeathEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
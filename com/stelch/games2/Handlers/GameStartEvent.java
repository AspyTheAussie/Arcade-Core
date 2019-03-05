package com.stelch.games2.Handlers;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStartEvent
  extends Event
{
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


/* Location:              C:\Users\Ryan Wood\Desktop\StelchCore.jar!\com\stelch\games2\Handlers\GameStartEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
package com.stelch.games2.Handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class onFoodLevelChange implements Listener {
  @EventHandler
  public void onFoodLevelChange(FoodLevelChangeEvent e)
  {
    e.setCancelled(true);
  }
}
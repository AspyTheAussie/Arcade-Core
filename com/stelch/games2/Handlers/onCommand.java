package com.stelch.games2.Handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;





public class onCommand
{
  private static enum Commands
  {
    reload, 
    help;
    
    private Commands() {}
  }
  
  @EventHandler
  public void onCmd(PlayerCommandPreprocessEvent e) {
    if (e.getPlayer().hasPermission("stelch.access.admin")) {}
  }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Handlers\onCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
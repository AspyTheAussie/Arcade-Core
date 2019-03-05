package com.stelch.games2.Handlers;

import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Game.Game;
import com.stelch.games2.Varables.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;






public class CommandPreProcessEvent
  implements Listener
{
  @EventHandler
  public void CommandPreProcessEvent(PlayerCommandPreprocessEvent e)
  {
    if (((e.getMessage().startsWith("/gamemode")) || 
      (e.getMessage().startsWith("/rl")) || 
      (e.getMessage().startsWith("/reload")) || 
      (e.getMessage().startsWith("/stop")) || 
      (e.getMessage().startsWith("/minecraft:")) ||
      (e.getMessage().startsWith("/bukkit:"))) &&
      (Game.Game.getGameState() == GameState.IN_GAME))
    {
      e.setCancelled(true);
      e.getPlayer().sendMessage(Text.format("&9Warn> &7That command has been be disabled by the Mini-Games"));
    }
  }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Handlers\CommandPreProcessEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
package com.stelch.games2.Handlers;

import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Formatting.Text.MessageType;
import com.stelch.games2.Game.Countdown;
import com.stelch.games2.Game.Game;
import com.stelch.games2.Main;
import com.stelch.games2.Varables.GameState;
import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin
        implements Listener {
  Main plugin;
  FileConfiguration config;

  public onJoin(Main main) {
    this.plugin = main;
    this.config = main.getConfig();
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    String spawnWorld = this.config.getString("lobby.spawn.world");
    int spawnX = this.config.getInt("lobby.spawn.x");
    int spawnY = this.config.getInt("lobby.spawn.y");
    int spawnZ = this.config.getInt("lobby.spawn.z");

    Location spawn = new Location(Bukkit.getServer().getWorld(spawnWorld), spawnX, spawnY, spawnZ);

    e.setJoinMessage(null);
    Game game = Game.Game;

    e.getPlayer().setGameMode(GameMode.ADVENTURE);
    if (game.getGameState() == GameState.IN_LOBBY) {
      Text.sendAll("&eJoin> &7" + e.getPlayer().getName() + " has joined. (&6" + Bukkit.getOnlinePlayers().size() + "&7/&6" + game.getMinPlayers() + "&7)", Text.MessageType.TEXT_CHAT);
      e.getPlayer().teleport(spawn);
      if (Bukkit.getServer().getOnlinePlayers().size() >= game.getMinPlayers()) {
        Countdown.start(game, false);
      }
    }
  }
}


/* Location:              C:\Users\Ryan Wood\Desktop\StelchCore.jar!\com\stelch\games2\Handlers\onJoin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
package com.stelch.games2.Game;

import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Formatting.Text.MessageType;
import com.stelch.games2.Handlers.GameStartEvent;
import com.stelch.games2.Main;
import com.stelch.games2.Varables.GameState;
import com.stelch.games2.Varables.GameType;
import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown {
  private static Main plugin;
  private static int counter;

  public Countdown(Main main) {
    plugin = main;
  }

  public static void start(Game game, boolean ignore_min_players) {
    final int min_players = ignore_min_players ? 0 : game.getMinPlayers();
    counter = game.getCountdownTime();
    Text.sendAll("&eGame> &7The game will start in &a" + counter + "&7 seconds", Text.MessageType.TEXT_CHAT);
    game.setGameState(GameState.STARTING);
    new BukkitRunnable() {
      public void run() {
        if (Countdown.counter <= 0) {
          Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent());
          game.setGameState(GameState.IN_GAME);
          Text.sendAll("&eThe game will start now.", Text.MessageType.ACTION_BAR);
          cancel();
          return;
        }
        if (game.getGameType() == GameType.CUSTOM) {
          Countdown.counter = (game.getCountdownTime());
          cancel();
          return;
        }
        if (min_players > Bukkit.getOnlinePlayers().size()) {
          Countdown.counter = (game.getCountdownTime());
          Text.sendAll("&cGame> &7The countdown has been canceled, not enough players.", Text.MessageType.TEXT_CHAT);
          game.setGameState(GameState.IN_LOBBY);
          cancel();
          return;
        }
        if (game.getGameState() != GameState.STARTING) {
          Countdown.counter = (game.getCountdownTime());
          Text.sendAll("&cGame> &7The countdown has been canceled, stopped by Admin.", Text.MessageType.TEXT_CHAT);
          game.setGameState(GameState.IN_LOBBY);
          cancel();
          return;
        }
        Text.sendAll("&eThe game will start in " + (Countdown.counter <= Countdown.counter / 3 ? "&6" + Countdown.counter : Countdown.counter <= Countdown.counter / 2 ? "&c" + Countdown.counter : new StringBuilder().append("&a").append(Countdown.counter).toString()) + "&e seconds.", Text.MessageType.ACTION_BAR);
        Countdown.counter = (Countdown.counter - 1);
      }
    }.runTaskTimer(plugin, 0, 20);
  }
}


/* Location:              C:\Users\Ryan Wood\Desktop\StelchCore.jar!\com\stelch\games2\Game\Countdown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
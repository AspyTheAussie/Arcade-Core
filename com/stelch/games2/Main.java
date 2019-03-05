package com.stelch.games2;

import com.stelch.games2.Game.Countdown;
import com.stelch.games2.Game.Game;
import com.stelch.games2.Handlers.Blocks;
import com.stelch.games2.Handlers.onClick;
import com.stelch.games2.Handlers.onDeath;
import com.stelch.games2.Handlers.onJoin;
import com.stelch.games2.Util.Effects;
import com.stelch.games2.commands.admin;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
        extends JavaPlugin {
  public void onEnable() {
    PluginManager pm = Bukkit.getServer().getPluginManager();
    pm.registerEvents(new onJoin(this), this);
    pm.registerEvents(new onClick(), this);
    pm.registerEvents(new onDeath(this), this);
    pm.registerEvents(new Blocks(), this);

    getCommand("admin").setExecutor(new admin());

    new Countdown(this);
    new Effects(this);
    new Game(this);
  }
}


/* Location:              C:\Users\Ryan Wood\Desktop\StelchCore.jar!\com\stelch\games2\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
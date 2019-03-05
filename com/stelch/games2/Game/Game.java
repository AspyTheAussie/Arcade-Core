package com.stelch.games2.Game;

import com.stelch.ctf.Varables.EndCondition;
import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Formatting.Text.MessageType;
import com.stelch.games2.Handlers.Blocks;
import com.stelch.games2.Handlers.GameStopEvent;
import com.stelch.games2.Handlers.onClick;
import com.stelch.games2.Main;
import com.stelch.games2.Util.Item;
import com.stelch.games2.Varables.GameState;
import com.stelch.games2.Varables.GameType;
import com.stelch.games2.Varables.TeamHandlers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.server.v1_8_R3.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Game
{
    private static Main plugin;
    public static Game Game;
    private GameType type;
    private String GameTitle = "UNKNOWN";
    private int MaxPlayers = 4;
    private int MinPlayers = 2;
    private int countdown_time = 10;
    private boolean AllowSpectators = true;
    private Scoreboard scoreboard;
    private boolean doDaylightCycle = true;
    private boolean AutoStart = true;
    private boolean FreezePlayers = false;
    private com.stelch.ctf.Varables.EndCondition EndCondition = com.stelch.ctf.Varables.EndCondition.SCORE;
    private TeamHandlers teamHandler = TeamHandlers.CORE;
    private GameState GameState = com.stelch.games2.Varables.GameState.IN_LOBBY;
    public static HashMap<Player, Integer> kills = new HashMap();
    public static HashMap<Player, Integer> deaths = new HashMap();

    public Game(Main main)
    {
        plugin = main;
    }

    public Game(Plugin plugin, GameType gameType)
    {
        if (Game != null)
        {
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        Game = this;
        this.type = gameType;
    }

    public void setMaxPlayers(int PlayerLimit)
    {
        this.MaxPlayers = PlayerLimit;
    }

    public int getMaxPlayers()
    {
        return this.MaxPlayers;
    }

    public void setMinPlayers(int PlayerRequirments)
    {
        this.MinPlayers = PlayerRequirments;
    }

    public int getMinPlayers()
    {
        return this.MinPlayers;
    }

    public void setName(String name)
    {
        this.GameTitle = name;
    }

    public String getName()
    {
        return this.GameTitle;
    }

    public void setGameType(GameType type)
    {
        this.type = type;
    }

    public GameType getGameType()
    {
        return this.type;
    }

    public void setCountdownTime(int time)
    {
        this.countdown_time = time;
    }

    public int getCountdownTime()
    {
        return this.countdown_time;
    }

    public void setAllowSpectators(boolean AllowSpectators)
    {
        this.AllowSpectators = AllowSpectators;
    }

    public boolean getAllowSpectators()
    {
        return this.AllowSpectators;
    }

    public void setGameState(GameState GameState)
    {
        this.GameState = GameState;
    }

    public GameState getGameState()
    {
        return this.GameState;
    }

    public void setDaylightCycle(boolean doDaylightCycle)
    {
        this.doDaylightCycle = doDaylightCycle;
    }

    public boolean getDaylightCyble()
    {
        return this.doDaylightCycle;
    }

    public void setTeamHandler(TeamHandlers handler)
    {
        this.teamHandler = handler;
    }

    public TeamHandlers getTeamHandler()
    {
        return this.teamHandler;
    }

    public void forceStart(boolean ignore_min_players)
    {
        Countdown.start(this, ignore_min_players);
    }

    public void forceStop()
    {
        end();
    }

    public void setAutoStart(boolean AutoStart)
    {
        this.AutoStart = AutoStart;
    }

    public boolean getAutoStart()
    {
        return this.AutoStart;
    }

    public void setEndConditions(EndCondition condition)
    {
        this.EndCondition = condition;
    }

    public EndCondition getEndConditions()
    {
        return this.EndCondition;
    }

    public void setFreezePlayers(boolean Freeze)
    {
        this.FreezePlayers = Freeze;
    }

    public boolean getFreezePlayers()
    {
        return this.FreezePlayers;
    }

    private void end()
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            FileConfiguration config = plugin.getConfig();
            String spawnWorld = config.getString("maps.default.locations.lobby.world");
            int spawnX = config.getInt("maps.default.locations.lobby.x");
            int spawnY = config.getInt("maps.default.locations.lobby.y");
            int spawnZ = config.getInt("maps.default.locations.lobby.z");
            Location spawn = new Location(Bukkit.getServer().getWorld(spawnWorld), spawnX, spawnY, spawnZ);
            p.setGameMode(GameMode.ADVENTURE);
            p.teleport(spawn);
        }
        for (Map.Entry<Location, Material> obj : Blocks.blocks.entrySet())
        {
            Material m = (Material)obj.getValue();
            Location l = (Location)obj.getKey();
            Bukkit.broadcastMessage(m.name());
            l.getWorld().getBlockAt(l).setType(m);
            if (Blocks.block_data.get(l) != null) {
                l.getWorld().getBlockAt(l).setData(((Byte)Blocks.block_data.get(l)).byteValue());
            }
        }
        for (Map.Entry< Entity, Item.click > e : onClick.Entities.entrySet()) {
            e.getKey().remove();
        }
        Bukkit.getServer().getPluginManager().callEvent(new GameStopEvent());

        Blocks.blocks.clear();
        Text.sendAll("&eThe game has been stopped.", Text.MessageType.ACTION_BAR);
        Game.setGameState(GameState.IN_LOBBY);
    }
}

package com.stelch.games2.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Game.Game;
import com.stelch.games2.Util.Inventory;
import com.stelch.games2.Util.Item;
import com.stelch.games2.Varables.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class admin
        implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(Text.format("&9Admin> &7That command can only be executed by in-game users."));
      return false;
    }
    Player p = (Player) sender;
    p.openInventory(AdminMenu(p).getInventory());
    return false;
  }

  public static Inventory AdminMenu(Player sender) {
    Inventory inv = new Inventory("&cAdmin Menu", 3);
    inv.addItem(new Item(Material.LAVA_BUCKET, "&6Punish", false, 0, new Item.click() {
      public void run(Player p) {
        p.openInventory(admin.punish(p).getInventory());
      }
    }).getItemStack(), 3, 2);

    inv.addItem(new Item(Material.COMMAND, "&eGame Management", false, 0, new Item.click() {
      public void run(Player p) {
        p.openInventory(admin.GameMgr(p).getInventory());
      }
    }).getItemStack(), 7, 2);

    return inv;
  }

  public static Inventory punish(Player p) {
    Inventory inv = new Inventory("&cPunish", Bukkit.getOnlinePlayers().size() / 7 + 2);
    inv.addItem(new Item(Material.BARRIER, "&cBack", false, 0, new Item.click() {
      public void run(Player p1) {
        p1.openInventory(admin.AdminMenu(p1).getInventory());
      }
    }).getItemStack(), 1, 1);
    int count = 2;
    int count_y = 2;
    for (Player target: Bukkit.getOnlinePlayers()) {
      ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
      SkullMeta meta = (SkullMeta) skull.getItemMeta();
      meta.setOwner(target.getName());
      List lore = new ArrayList();
      lore.add("");
      lore.add("Health: " + target.getHealthScale());
      lore.add("Ping: " + ((CraftPlayer) target).getHandle().ping);
      lore.add("Kills: " + (Game.kills.containsKey(target) ? ((Integer) Game.kills.get(target)).intValue() : 0));
      lore.add("Deaths: " + (Game.deaths.containsKey(target) ? ((Integer) Game.deaths.get(target)).intValue() : 0));
      meta.setLore(lore);
      skull.setItemMeta(meta);
      inv.addItem(new Item(skull, ChatColor.LIGHT_PURPLE + target.getName(), false, new Item.click() {
        public void run(Player p1) {
          p1.sendMessage("Function unset.");
        }
      }).getItemStack(), count, count_y);
      count++;
      if (count >= 7) {
        count = 2;
        count_y += 1;
      }
    }
    return inv;
  }

  public static Inventory GameMgr(Player p) {
    Inventory inv = new Inventory("&eGame Managment", 3);
    inv.addItem(new Item(Material.BARRIER, "&cBack", false, 0, new Item.click() {
      public void run(Player p1) {
        admin.AdminMenu(p1);
      }
    }).getItemStack(), 1, 1);
    if (Game.Game.getGameState() == GameState.IN_GAME) {
      inv.addItem(stopGame().getItemStack(), 2, 2);
    } else if (Game.Game.getGameState() == GameState.IN_LOBBY) {
      inv.addItem(startGame().getItemStack(), 2, 2);
    } else {
      inv.addItem(new Item(Material.COMMAND, "&eNo action available. - " + Game.Game.getGameState(), false, 0, new Item.click() {
        public void run(Player p) {}
      }).getItemStack(), 2, 2);
    }
    inv.addItem(config().getItemStack(), 4, 2);
    return inv;
  }

  private static Item config() {
    return new Item(Material.REDSTONE_COMPARATOR, "&eConfig", false, 0, new Item.click() {
      public void run(Player p) {
        new com.stelch.games2.Inventories.GameConfig(p);
      }
    });
  }

  private static Item startGame() {
    return new Item(Material.REDSTONE_BLOCK, "&aStart Game", false, 0, new Item.click() {
      public void run(Player p) {
        Text.sendMessage(p, "You have force started the game", Text.NotifyType.ADMIN);
        Game.Game.forceStart(true);
      }
    });
  }

  private static Item stopGame() {
    return new Item(Material.REDSTONE_BLOCK, "&cStop Game", false, 0, new Item.click() {
      public void run(Player p) {
        Text.sendMessage(p, "You have stopped the game.", Text.NotifyType.ADMIN);
        Game.Game.forceStop();
      }
    });
  }
}
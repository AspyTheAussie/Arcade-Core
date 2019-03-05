package com.stelch.games2.Inventories;

import com.stelch.games2.Game.Game;
import com.stelch.games2.Util.Item;
import com.stelch.games2.Util.Item.click;
import com.stelch.games2.commands.admin;
import java.util.Collection;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

public class GameConfig
{
  public GameConfig(final Player p)
  {
    com.stelch.games2.Util.Inventory inv = new com.stelch.games2.Util.Inventory("&eGame Configuration", 3);
    
    Dye green_dye = new Dye();green_dye.setColor(DyeColor.LIME);
    Dye red_dye = new Dye();red_dye.setColor(DyeColor.RED);
    
    inv.addItem(new Item(Material.BARRIER, "&cBack", false, 0, new Item.click() {
      public void run(Player p1) { p1.openInventory(admin.AdminMenu(p1).getInventory()); }
    }).getItemStack(), 1, 1);
    
    inv.addItem(new Item(Game.Game.getAutoStart() ? green_dye.toItemStack() : red_dye.toItemStack(), (Game.Game.getAutoStart() ? "&a" : "&c") + "AutoStart", false, new Item.click() {
      public void run(Player p1) { Game.Game.setAutoStart(!Game.Game.getAutoStart());new GameConfig(p); } })
      .addline("").addline(Game.Game.getAutoStart() ? "&aEnabled" : "&cDisabled").addline("Specify if the game should").addline("use it's own starting mechanics").addline("or if it should be manual").getItemStack(), 2, 2);
    
    inv.addItem(new Item(Game.Game.getAllowSpectators() ? green_dye.toItemStack() : red_dye.toItemStack(), (Game.Game.getAllowSpectators() ? "&a" : "&c") + "Allow Spectators", false, new Item.click() {
      public void run(Player p1) { Game.Game.setAllowSpectators(!Game.Game.getAllowSpectators());new GameConfig(p); } })
      .addline("").addline(Game.Game.getAllowSpectators() ? "&aEnabled" : "&cDisabled").addline("Specify if the game should").addline("allow for players to").addline("spectate the running game.").getItemStack(), 4, 2);
    
    inv.addItem(new Item(new ItemStack(Material.HOPPER), "&eAuto End Type", false, new Item.click() {
      public void run(Player p1) { Game.Game.setEndConditions(Game.Game.getEndConditions() == com.stelch.ctf.Varables.EndCondition.SCORE ?com.stelch.ctf.Varables.EndCondition.TIME :com.stelch.ctf.Varables.EndCondition.SCORE);new GameConfig(p); } })
      .addline("").addline(Game.Game.getEndConditions() ==com.stelch.ctf.Varables.EndCondition.SCORE ? "&aScore Cap" : "&bTime Limit").addline("Specify if the game should").addline("stop once the score cap is").addline("reached, or have a countdown.").getItemStack(), 6, 2);
    
    inv.addItem(new Item(Game.Game.getFreezePlayers() ? green_dye.toItemStack() : red_dye.toItemStack(), (Game.Game.getFreezePlayers() ? "&a" : "&c") + "Free Players", false, new Item.click() {
      public void run(Player p1) {
        Game.Game.setFreezePlayers(!Game.Game.getFreezePlayers());new GameConfig(p);
        if (Game.Game.getFreezePlayers()) { Player p; Location e1; for (Iterator localIterator = Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext(); p.teleport(e1)) { p = (Player)localIterator.next();e1 = p.getLocation();e1.setY(e1.getY() + 0.5D);p.setAllowFlight(true);p.setFlying(true);p.setFlySpeed(0.0F);
          } } else { p.setAllowFlight(false);p.setFlying(false);p.setFlySpeed(0.1F);p.setWalkSpeed(0.2F); } } })
    
      .addline("").addline(Game.Game.getFreezePlayers() ? "&aEnabled" : "&cDisabled").addline("Specify if the game should").addline("prevent players from").addline("being able to move.").getItemStack(), 8, 2);
    
    inv.addItem(new Item(Material.ENDER_PEARL, "Next Page", false, 0, new Item.click()
    {
      public void run(Player p) { p.openInventory(GameConfig.pg2(p)); }
    }).getItemStack(), 9, 3);
    p.openInventory(inv.getInventory());
  }
  
  private static org.bukkit.inventory.Inventory pg2(Player p) {
    com.stelch.games2.Util.Inventory inv = new com.stelch.games2.Util.Inventory("&eGame Configuration", 3);
    
    Dye green_dye = new Dye();green_dye.setColor(DyeColor.LIME);
    Dye red_dye = new Dye();red_dye.setColor(DyeColor.RED);
    
    inv.addItem(new Item(Material.BARRIER, "&cBack", false, 0, new Item.click() {
      public void run(Player p1) { p1.openInventory(admin.AdminMenu(p1).getInventory()); }
    }).getItemStack(), 1, 1);
    
    inv.addItem(new Item(p.getWorld().getPVP() ? green_dye.toItemStack() : red_dye.toItemStack(), (p.getWorld().getPVP() ? "&a" : "&c") + "PvP", false, new Item.click() {
      public void run(Player p1) { p1.getWorld().setPVP(!p1.getWorld().getPVP()); } })
      .addline("").addline(p.getWorld().getPVP() ? "&aEnabled" : "&cDisabled").addline("Specify if the server should").addline("allow/deny pvp").getItemStack(), 2, 2);
    
    inv.addItem(new Item(Bukkit.getServer().hasWhitelist() ? green_dye.toItemStack() : red_dye.toItemStack(), (Bukkit.getServer().hasWhitelist() ? "&a" : "&c") + "Whitelist", false, new Item.click() {
      public void run(Player p1) { Bukkit.getServer().setWhitelist(!Bukkit.getServer().hasWhitelist()); } })
      .addline("").addline(Bukkit.getServer().hasWhitelist() ? "&aEnabled" : "&cDisabled").addline("Specify if the game should").addline("filter for players to").addline("whilelist only.").getItemStack(), 4, 2);
    
    inv.addItem(new Item(Material.ENDER_PEARL, "Previous Page", false, 0, new Item.click()
    {
      public void run(Player p) { new GameConfig(p); }
    }).getItemStack(), 1, 3);
    return inv.getInventory();
  }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Inventories\GameConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
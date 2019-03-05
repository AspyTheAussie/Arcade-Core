package com.stelch.games2.Inventories;

import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Teams.TeamBuilder;
import com.stelch.games2.Util.Inventory;
import com.stelch.games2.Util.Item;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class shop
{
  public org.bukkit.inventory.Inventory Item(Player p, TeamBuilder.Teams t)
  {
    Inventory inv = new BuyMenu(p, t, BuyMenu.menu.MAIN, "Item Shop").fetch();
    
    ItemStack bow_is = new ItemStack(Material.BOW);
    ItemMeta bow_meta = bow_is.getItemMeta();bow_meta.addEnchant(Enchantment.KNOCKBACK, 1, true);bow_is.setItemMeta(bow_meta);
    
    inv.addItem(new Item(new ItemStack(Material.WOOL, 16), "&fx16 Wool", false, new Item.click() {
      public void run(Player p) { TeamBuilder.Teams team = TeamBuilder.getTeam(p);shop.buyItem(new ItemStack(Material.WOOL, 16, (short)(byte)(team == TeamBuilder.Teams.BLUE ? 11 : team == TeamBuilder.Teams.RED ? 14 : 0)), new ItemStack(Material.IRON_INGOT), 4, p); } })
      .addline("&7Cost: &f4 Iron").addline("").addline("&eClick to purchase!").getItemStack(), 2, 3);
    
    inv.addItem(new Item(new ItemStack(Material.WOOD, 16), "&fx16 Wood", false, new Item.click() {
      public void run(Player p) { shop.buyItem(new ItemStack(Material.WOOD, 16), new ItemStack(Material.GOLD_INGOT), 5, p); } })
      .addline("&7Cost: &65 Gold").addline("").addline("&eClick to purchase!").getItemStack(), 2, 4);
    


    inv.addItem(new Item(new ItemStack(Material.STONE_SWORD, 1), "&7Stone Sword", false, new Item.click() {
      public void run(Player p) { shop.buyItem(new ItemStack(Material.STONE_SWORD, 1), new ItemStack(Material.IRON_INGOT), 30, p); } })
      .addline("&7Cost: &f30 Iron").addline("").addline("&eClick to purchase!").getItemStack(), 3, 3);
    
    inv.addItem(new Item(new ItemStack(Material.IRON_SWORD, 1), "&7Iron Sword", false, new Item.click() {
      public void run(Player p) { shop.buyItem(new ItemStack(Material.IRON_SWORD, 1), new ItemStack(Material.GOLD_INGOT), 7, p); } })
      .addline("&7Cost: &67 Gold").addline("").addline("&eClick to purchase!").getItemStack(), 3, 4);
    

    inv.addItem(new Item(new ItemStack(Material.SHEARS, 1), "&fShears", false, new Item.click() {
      public void run(Player p) { shop.buyItem(new ItemStack(Material.SHEARS, 1), new ItemStack(Material.IRON_INGOT), 20, p); } })
      .addline("&7Cost: &f20 Iron").addline("").addline("&eClick to purchase!").getItemStack(), 5, 4);
    
    inv.addItem(new Item(new ItemStack(Material.BOW, 1), "&bBow", true, new Item.click() {
      public void run(Player p) {
        ItemStack bow_is = new ItemStack(Material.BOW);
        ItemMeta bow_meta = bow_is.getItemMeta();
        bow_meta.setDisplayName(Text.format("&bBow"));
        bow_meta.addEnchant(Enchantment.KNOCKBACK, 2, true);
        bow_is.setItemMeta(bow_meta);
        shop.buyItem(bow_is, new ItemStack(Material.GOLD_INGOT), 24, p); } })
    
      .addline("&7Cost: &624 Gold").addline("").addline("&eClick to purchase!").getItemStack(), 6, 3);
    
    inv.addItem(new Item(new ItemStack(Material.ARROW, 8), "&7x8 Arrow", false, new Item.click() {
      public void run(Player p) { shop.buyItem(new ItemStack(Material.ARROW, 8), new ItemStack(Material.IRON_INGOT), 24, p); } })
      .addline("&7Cost: &f24 Iron").addline("").addline("&eClick to purchase!").getItemStack(), 6, 4);
    
    inv.addItem(new Item(new ItemStack(Material.TNT, 1), "&cTNT", false, new Item.click() {
      public void run(Player p) { shop.buyItem(new ItemStack(Material.TNT, 1), new ItemStack(Material.GOLD_INGOT), 5, p); } })
      .addline("&7Cost: &65 Gold").addline("").addline("&eClick to purchase!").getItemStack(), 8, 3);
    
    inv.addItem(new Item(new ItemStack(Material.WATER_BUCKET, 1), "&9Water", false, new Item.click() {
      public void run(Player p) { shop.buyItem(new ItemStack(Material.WATER_BUCKET, 1), new ItemStack(Material.GOLD_INGOT), 5, p); } })
      .addline("&7Cost: &65 Gold").addline("").addline("&eClick to purchase!").getItemStack(), 8, 4);
    
    return inv.getInventory();
  }
  
  static void buyItem(ItemStack item, ItemStack cost, int count, Player p) {
    if (p.getInventory().containsAtLeast(cost, count)) {
      for (int i = 0; i < count; i++) p.getInventory().removeItem(new ItemStack[] { cost });
      p.getInventory().addItem(new ItemStack[] { item });
      p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.0F, 2.0F);
    } else {
      p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2.0F, 2.0F);
    }
  }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Inventories\shop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
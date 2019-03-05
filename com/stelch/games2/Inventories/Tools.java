package com.stelch.games2.Inventories;

import com.stelch.games2.Teams.TeamBuilder;
import com.stelch.games2.Teams.TeamBuilder.Teams;
import com.stelch.games2.Util.Item;
import com.stelch.games2.Util.Item.click;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;






public class Tools
{
  public static org.bukkit.inventory.Inventory getIvnentory(Player p, TeamBuilder.Teams team)
  {
    com.stelch.games2.Util.Inventory inv = new BuyMenu(p, team, BuyMenu.menu.BLOCKS, "Tools").fetch();
    
    inv.addItem(new Item(Material.WOOD_AXE, "Wooden Axe", false, 0, new Item.click()
    {

      public void run(Player p) { shop.buyItem(new ItemStack(Material.WOOD_AXE), new ItemStack(Material.GOLD_INGOT), 24, p); } })
    
      .addline("No matter if you want to beak a block").addline("or dominate the battlefield,").addline("the Axe will always be there.").addline("").addline("&7Price: &624 Gold").addline("&eClick to purchase")
      .getItemStack(), 2, 4);
    

    inv.addItem(new Item(Material.WOOD_PICKAXE, "Wooden Pickaxe", false, 0, new Item.click()
    {

      public void run(Player p) { shop.buyItem(new ItemStack(Material.WOOD_PICKAXE), new ItemStack(Material.GOLD_INGOT), 24, p); } })
    
      .addline("No matter if you want to beak a block").addline("or mine to safety,").addline("a pick will get you there.").addline("").addline("&7Price: &624 Gold").addline("&eClick to purchase")
      .getItemStack(), 2, 5);
    
    return inv.getInventory();
  }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Inventories\Tools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
package com.stelch.games2.Inventories;

import com.stelch.games2.Teams.TeamBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;






public class Weapons
{
  public static Inventory getIvnentory(Player p, TeamBuilder.Teams team)
  {
    Inventory inv = new com.stelch.games2.Inventories.BuyMenu(p, team, com.stelch.games2.Inventories.BuyMenu.menu.BLOCKS, "Weapons").build();
    
    return inv;
  }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Inventories\Weapons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
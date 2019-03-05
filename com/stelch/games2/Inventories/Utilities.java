package com.stelch.games2.Inventories;

import com.stelch.games2.Teams.TeamBuilder;
import com.stelch.games2.Teams.TeamBuilder.Teams;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;






public class Utilities
{
  public static Inventory getIvnentory(Player p, TeamBuilder.Teams team)
  {
    Inventory inv = new BuyMenu(p, team, BuyMenu.menu.BLOCKS, "Utilities").build();
    
    return inv;
  }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Inventories\Utilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
package com.stelch.games2.Teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.stelch.games2.Formatting.Text;
import org.bukkit.entity.Player;








public class TeamBuilder
{
  public static enum Teams
  {
    RED,  BLUE;
    private Teams() {} }
  public static List teams = new ArrayList();
  public static List teams_alive = new ArrayList();
  
  public static HashMap<Player, Teams> Players = new HashMap();
  
  public static HashMap<Player, Teams> living_players = new HashMap();
  
  public static void setTeam(Player p, Teams Team) {
    if (Players.containsKey(p)) Players.replace(p, Team); else
      Players.put(p, Team);
    switch (Team) {
    case BLUE: 
      p.setDisplayName(Text.format("&b&lB &f" + p.getName()));
      p.setPlayerListName(Text.format("&b" + p.getName()));
      break;
    case RED: 
      p.setDisplayName(Text.format("&c&lR &f" + p.getName()));
      p.setPlayerListName(Text.format("&c" + p.getName()));
    }
  }
  
  public static Teams getTeam(Player p)
  {
    return (Teams)Players.get(p);
  }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Teams\TeamBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
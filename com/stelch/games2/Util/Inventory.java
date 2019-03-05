package com.stelch.games2.Util;

import com.stelch.games2.Formatting.Text;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class Inventory
{
  private org.bukkit.inventory.Inventory inv;

  public Inventory(String title)
  {
    new Inventory(title, 3);
  }

  public Inventory(String title, int Columns)
  {
    this.inv = Bukkit.getServer().createInventory(null, Columns * 9, Text.format(title));
  }

  public void addItem(ItemStack is)
  {
    this.inv.addItem(new ItemStack[] { is });
  }

  public void addItem(ItemStack is, int x, int y)
  {
    this.inv.setItem(x - 1 + (y - 1) * 9, is);
  }

  public org.bukkit.inventory.Inventory getInventory()
  {
    return this.inv;
  }

  public ItemStack[] getItems()
  {
    return this.inv.getContents();
  }
}

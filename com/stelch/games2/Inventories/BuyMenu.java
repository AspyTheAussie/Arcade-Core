package com.stelch.games2.Inventories;

import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Teams.TeamBuilder;
import com.stelch.games2.Util.Inventory;
import com.stelch.games2.Util.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BuyMenu {
    Inventory inv;

    public static enum menu {
        MAIN,
        BLOCKS,
        WEAPONS,
        ARMOR,
        TOOLS,
        BOWS,
        POTIONS,
        UTILITIES;

        private menu() {}
    }

    public BuyMenu(Player p, final TeamBuilder.Teams t, menu Menu, String name) {
        Inventory inv = new Inventory(Text.format(name), 6);

        if (Menu != menu.MAIN) {
            inv.addItem(new Item(Material.NETHER_STAR, "&cBack&r", true, 0, new Item.click() {
                public void run(Player p) {
                    p.openInventory(new com.stelch.games2.Inventories.shop().Item(p, t));
                }
            }).getItemStack(), 1, 1);
        }

        inv.addItem(new Item(Material.STAINED_CLAY, "&aBlocks&r", false, 1, new Item.click() {

            public void run(Player p) {
                p.openInventory(com.stelch.games2.Inventories.Blocks.getIvnentory(p, t));
            }
        })

                .addline("&eClick to view.&r").getItemStack(), 2, 1);

        inv.addItem(new Item(Material.GOLD_SWORD, "&cWeapons", false, 0, new Item.click() {

            public void run(Player p) {
                p.openInventory(Weapons.getIvnentory(p, t));
            }
        })

                .addline("&eClick to view.").getItemStack(), 3, 1);

        inv.addItem(new Item(Material.CHAINMAIL_BOOTS, "&bArmor", false, 0, new Item.click() {

            public void run(Player p) {
                p.openInventory(com.stelch.games2.Inventories.Armor.getIvnentory(p, t));
            }
        })

                .addline("&eClick to view.").getItemStack(), 4, 1);

        inv.addItem(new Item(Material.STONE_PICKAXE, "&7Tools", false, 0, new Item.click() {

            public void run(Player p) {
                p.openInventory(com.stelch.games2.Inventories.Tools.getIvnentory(p, t));
            }
        })

                .addline("&eClick to view.").getItemStack(), 5, 1);

        inv.addItem(new Item(Material.BOW, "&3Bows", false, 0, new Item.click() {

            public void run(Player p) {
                p.openInventory(Bows.getIvnentory(p, t));
            }
        })

                .addline("&eClick to view.").getItemStack(), 6, 1);

        inv.addItem(new Item(Material.BREWING_STAND_ITEM, "&9Potions", false, 0, new Item.click() {

            public void run(Player p) {
                p.openInventory(Potions.getIvnentory(p, t));
            }
        })

                .addline("&eClick to view.").getItemStack(), 7, 1);

        inv.addItem(new Item(Material.TNT, "&5Utilities", false, 0, new Item.click() {

            public void run(Player p) {
                p.openInventory(Utilities.getIvnentory(p, t));
            }
        })

                .addline("&eClick to view.").getItemStack(), 8, 1);




        Item catitem = new Item(Material.STAINED_GLASS_PANE, "&7↑ Categories", false, 15, new Item.click() {
            public void run(Player p) {}
        })


                .addline("&7↓ Items");

        inv.addItem(catitem.setData((byte)(Menu == menu.BLOCKS ? 5 : 15)).getItemStack(), 2, 2);
        inv.addItem(catitem.setData((byte)(Menu == menu.WEAPONS ? 5 : 15)).getItemStack(), 3, 2);
        inv.addItem(catitem.setData((byte)(Menu == menu.ARMOR ? 5 : 15)).getItemStack(), 4, 2);
        inv.addItem(catitem.setData((byte)(Menu == menu.TOOLS ? 5 : 15)).getItemStack(), 5, 2);
        inv.addItem(catitem.setData((byte)(Menu == menu.BOWS ? 5 : 15)).getItemStack(), 6, 2);
        inv.addItem(catitem.setData((byte)(Menu == menu.POTIONS ? 5 : 15)).getItemStack(), 7, 2);
        inv.addItem(catitem.setData((byte)(Menu == menu.UTILITIES ? 5 : 15)).getItemStack(), 8, 2);

        this.inv = inv;
    }


    public org.bukkit.inventory.Inventory build() {
        return inv.getInventory();
    }

    public Inventory fetch() {
        return inv;
    }
}


/* Location:              C:\Users\homen\Desktop\server\servers\ctf\plugins\ctf.jar!\com\stelch\ctf\Inventories\BuyMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
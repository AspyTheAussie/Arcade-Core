package com.stelch.games2.Util;


import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Handlers.onClick;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private ItemStack is;
    private ItemMeta im;
    private List lore = new ArrayList();

    public Item() {}

    public Item(Material material) {
        new Item(material, null, false);
    }

    public Item(Material material, String name) {
        new Item(material, name, false);
    }

    public Item(Material material, String name, boolean Enchanted) {
        new Item(material, name, Enchanted);
    }

    public Item(Material material, String name, boolean Enchanted, int data, click onClick) {
        this.is = new ItemStack(material, 1, (short)(byte) data);
        this.im = this.is.getItemMeta();
        if (Enchanted) {
            this.im.addEnchant(Enchantment.PROTECTION_FIRE, 0, false);
        }
        if (name != null) {
            this.im.setDisplayName(Text.format(name));
        }
        this.is.setItemMeta(this.im);
        com.stelch.games2.Handlers.onClick.Actions.put(this.is, onClick);
    }

    public Item(ItemStack is, String name, boolean Enchanted, click onClick) {
        this.is = is;
        this.im = is.getItemMeta();
        if (Enchanted) {
            this.im.addEnchant(Enchantment.PROTECTION_FIRE, 0, false);
        }
        if (name != null) {
            this.im.setDisplayName(Text.format(name));
        }
        is.setItemMeta(this.im);
        com.stelch.games2.Handlers.onClick.Actions.put(is, onClick);
    }

    public Item setData(byte data) {
        this.is.setData(new MaterialData(data));
        return this;
    }

    public Item addline(String s) {
        this.lore.add(Text.format(s));
        this.im.setLore(this.lore);
        this.is.setItemMeta(this.im);
        return this;
    }

    public ItemStack getItemStack() {
        return this.is;
    }

    public static interface click {
        public abstract void run(Player paramPlayer);
    }
}
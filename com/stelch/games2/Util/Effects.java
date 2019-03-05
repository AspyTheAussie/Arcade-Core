package com.stelch.games2.Util;

import com.stelch.games2.Formatting.Text;
import com.stelch.games2.Game.Game;
import com.stelch.games2.Main;
import com.stelch.games2.Varables.GameState;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Effects {
  private static Main plugin;

  public Effects(Main main) {
    plugin = main;
  }

  public static class SpinningBlock {
    public SpinningBlock(Location location, Material material) {
      Create(location, material, 0);
    }

    public SpinningBlock(Location location, Material material, int data) {
      Create(location, material, data, true, null);
    }

    public SpinningBlock(Location location, Material material, int data, boolean small) {
      Create(location, material, data, small, null);
    }

    public SpinningBlock(Location location, Material material, int data, String text) {
      Create(location, material, data, true, text);
    }

    private ArmorStand e = null;
    private boolean deleted = false;

    private void Create(Location location, Material material, int data) {
      Create(location, material, data, true, null);
    }

    private void Create(Location location, Material material, int data, boolean is_Small, String text) {
      ItemStack skull = new ItemStack(material, 1, (short)(byte) data);
      this.e = ((ArmorStand) Bukkit.getWorld(location.getWorld().getName()).spawnEntity(location, EntityType.ARMOR_STAND));
      this.e.setVisible(false);
      this.e.setGravity(false);
      this.e.teleport(this.e.getLocation());
      this.e.setCanPickupItems(false);
      this.e.setRemoveWhenFarAway(false);
      this.e.getEquipment().setHelmet(skull);
      this.e.setCustomName(Text.format("&a&lEmerald &c&lGenerator"));
      this.e.setCustomNameVisible(true);
      this.e.setSmall(is_Small);

      new BukkitRunnable() {
        double ticks = 0.0;
        Location loc = Effects.SpinningBlock.this.e.getLocation();

        public void run() {
          if (Effects.SpinningBlock.this.deleted) {
            cancel();
          }
          this.ticks += 1.0;
          double change = Math.cos(this.ticks / 10.0);

          PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook packet = new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(Effects.SpinningBlock.this.e.getEntityId(), (byte) 0, (byte)(int)(change * 2.0), (byte) 0, (byte)(int)(this.loc.getYaw() + 0.0), (byte)(int)((this.loc.getPitch() + 180.0) / 360.0), true);
          this.loc.setYaw(this.loc.getYaw() + 7);
          this.loc.setY(change);
          Player p;
          for (Iterator localIterator = Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext();
               ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet)) {
            p = (Player) localIterator.next();
          }
        }
      }.runTaskTimerAsynchronously(Effects.plugin, 0, 1);
    }

    public void teleport(Location l) {
      this.e.teleport(l);
    }

    public void stop() {
      this.e.remove();
    }
  }

  public static class spawnner {
    private boolean stopped = false;

    public spawnner(Location l, ItemStack m, Long seconds) {
      new spawnner(l, m, seconds, null, false);
    }

    public spawnner(final Location l, final ItemStack m, Long seconds, Effects.SpinningBlock b, boolean Spin) {
      if (Spin) {
        Location to = l.add(0.0, 2.0, 0.0);
        b.teleport(to);
      }
      new BukkitRunnable() {
        public void run() {
          if ((Effects.spawnner.this.stopped) || (Game.Game.getGameState() != GameState.IN_GAME)) {
            cancel();
          }
          l.getWorld().dropItem(l, m);
        }
      }.runTaskTimer(Effects.plugin, 0,seconds*20);
    }

    public void stop() {
      this.stopped = true;
    }
  }
}


/* Location:              C:\Users\Ryan Wood\Desktop\StelchCore.jar!\com\stelch\games2\Util\Effects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
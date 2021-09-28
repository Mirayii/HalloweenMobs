package cz.lukynka.halloweenmobs.mobs;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import cz.lukynka.halloweenmobs.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import cz.lukynka.halloweenmobs.Utils.chat;
import cz.lukynka.halloweenmobs.Utils.math;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class Reaper implements Listener {

    Main main;

    public Reaper(Main plugin) {
        this.main = plugin;
    }



    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        final int chance = this.main.ReaperSpawnChance;
        Entity entity = event.getEntity();
        World world = event.getEntity().getWorld();
        Location loc = event.getEntity().getLocation();
        int random = math.randomNumberInRange(1, 100);

        if(entity.getType() == EntityType.SKELETON) {
            if(random <= chance) {
                if(main.isDev) {
                    chat.broadcast("&a[DEBUG] &9&l[ENTITY_REAPER] &7" +random +"-" +chance +" | " +loc);
                }
                Skeleton reaper = (Skeleton) entity;

                ItemStack ghostplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta gp = (LeatherArmorMeta) ghostplate.getItemMeta();
                gp.setColor(Color.fromRGB(18, 18, 18));
                gp.displayName(Component.text(chat.translated(main.getConfig().getString("Items.reaperTorso"))));
                ghostplate.setItemMeta(gp);

                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) skull.getItemMeta();
                PlayerProfile skin1720084343 = Bukkit.createProfile(UUID.fromString("538e4a32-7e54-4f5e-8e39-09ab6de0b657"), "skin1720084343");
                skin1720084343.setProperty(new ProfileProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYzMjgzNzkzNjI1OSwKICAicHJvZmlsZUlkIiA6ICI3NTE0NDQ4MTkxZTY0NTQ2OGM5NzM5YTZlMzk1N2JlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGFua3NNb2phbmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODEwYWEyM2I0NDgwNjVlYmZiOWY4MjM2YjkwZWU3ZmZkYjg5NmY0OWMwNGE2MTk4MThkOTRjZDc4ZWEzZTI5ZiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", "aeWgpG6lwV11E9Nm5PYwriVQPGqYXFRM2TbGrrJbWxgmevWpj34hMB3M5PvI4m2nHNnmEN1uJNtfZhgnA108Wdn++EUHq2neUShd0tFmN61LtBqNFS8S8EDRWeCvdCxSCaEFfePEQUxKrBDHL6eDXfEYIEiVRyJTSRUuTinIcTHHp1cppfGTA5C27GdLaOjEyYyAz4gnCOzBAzVLi7oti/CL5NCQcDXeEFv5hsFBP9nzvkEFKyzA1fZcCQ2y205IioXXApoboR3RT+jXpOShqCe2x8bKmBDcik5HoQMd+zdCWlC84s28cJl+OzqFEGkzNcKPP6H6nkPQXixZjULHYm12idSoqxGXdeZo4h8smuUkh49ds/OW6l/bdNXcaboWmqxnTjU4pRRo0Bz0/NTzZ2PfC6JznOPXiMA9XYtdsafIIPYtONR5VdJfhIOXTokOeMcTmtkihbrZi6WgDGS1izJCMUmuzmK+HOkHwd4eyoHKDEEBneHcBGmZQBTVbnCVcXEj3LZbc9w5HxJtd4tdPtL2flxXUIdkIbqpR6Dr8qUFmF/NTF/MIn1/hKqyLXrC2y81cHVpXhpTqwFuUd/6i2p3VN4VJE3hhw/O3rsRgX4bRQzrLoMS7XglcvPo6zCwVi46LmWKXBR3cwA1BEtlB5ovGYT4wCYQDLJrhfpQ5TA="));
                sm.setPlayerProfile(skin1720084343);
                /*int calliSecret = math.randomNumberInRange(1, 1000);
                if(main.isDev) {
                    calliSecret = math.randomNumberInRange(1, 5);
                }
                if(calliSecret == 1) {
                    sm.setOwner("moricalliopeEN");
                }*/
                sm.displayName(Component.text(chat.translated(main.getConfig().getString("Items.reaperHead"))));
                skull.setItemMeta(sm);

                ItemStack scythe = new ItemStack(Material.IRON_HOE);
                ItemMeta scm = scythe.getItemMeta();
                scm.displayName(Component.text(chat.translated(main.getConfig().getString("Items.reaperScythe"))));
                scythe.setItemMeta(scm);

                NamespacedKey key = new NamespacedKey(main, "mobType");
                reaper.setInvisible(true);
                reaper.setSilent(true);
                reaper.getPersistentDataContainer().set(key, PersistentDataType.STRING, "Reaper");

                reaper.getEquipment().setChestplate(ghostplate);
                reaper.getEquipment().setHelmet(skull);
                reaper.getEquipment().setItemInMainHand(scythe);
                world.playSound(loc, Sound.ENTITY_WITHER_SKELETON_AMBIENT, 2, 0);


            }
        }

    }




}

package cz.lukynka.halloweenmobs.mobs;

import cz.lukynka.halloweenmobs.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.*;
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

public class HeadlessHorseman implements Listener {

    Main main;

    public HeadlessHorseman(Main plugin) {
        this.main = plugin;
    }


    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        final int chance = this.main.HorsemanSpawnChance;
        Entity entity = event.getEntity();
        World world = event.getEntity().getWorld();
        Location loc = event.getEntity().getLocation();
        int random = math.randomNumberInRange(1, 100);
        int random2 = math.randomNumberInRange(1, 5);

        if(entity.getType() == EntityType.HORSE) {
            if(random <= chance) {
                if(main.isDev) {
                    chat.broadcast("&a[DEBUG] &f&l[ENTITY_HEADLESS_HORSEMAN] &7" +random +"-" +chance +" | " +loc);
                }

                event.setCancelled(true);


                Skeleton horseman = (Skeleton) world.spawnEntity(loc, EntityType.SKELETON);

                ItemStack horseman_chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta gp = (LeatherArmorMeta) horseman_chestplate.getItemMeta();
                gp.setColor(Color.fromRGB(255, 255, 255));
                gp.displayName(Component.text(chat.translated(main.getConfig().getString("Items.horsemanTorso"))));
                horseman_chestplate.setItemMeta(gp);

                ItemStack horseman_leggins = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta gl = (LeatherArmorMeta) horseman_chestplate.getItemMeta();
                gl.setColor(Color.fromRGB(255, 255, 255));
                gl.displayName(Component.text(chat.translated(main.getConfig().getString("Items.horsemanLegs"))));
                horseman_leggins.setItemMeta(gl);

                ItemStack horseman_boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta gb = (LeatherArmorMeta) horseman_chestplate.getItemMeta();
                gb.setColor(Color.fromRGB(255, 255, 255));
                gb.displayName(Component.text(chat.translated(main.getConfig().getString("Items.horsemanBoots"))));
                horseman_boots.setItemMeta(gb);


                ItemStack sword = new ItemStack(Material.IRON_SWORD);
                ItemMeta scm = sword.getItemMeta();
                scm.displayName(Component.text(chat.translated(main.getConfig().getString("Items.horsemanSword"))));
                sword.setItemMeta(scm);

                NamespacedKey key = new NamespacedKey(main, "mobType");
                horseman.setInvisible(true);
                horseman.setSilent(true);

                horseman.getEquipment().setChestplate(horseman_chestplate);
                horseman.getEquipment().setItemInMainHand(sword);
                horseman.getEquipment().setLeggings(horseman_leggins);
                horseman.getEquipment().setBoots(horseman_boots);
                if(!(random2 == 1)) {
                    ZombieHorse horse = (ZombieHorse) world.spawnEntity(loc, EntityType.ZOMBIE_HORSE);
                    horse.addPassenger(horseman);
                } else {
                    horseman.getEquipment().setItemInMainHand(new ItemStack(Material.SKELETON_SKULL));
                }
                world.playSound(loc, Sound.ENTITY_SKELETON_AMBIENT, 2, 0);
                horseman.getEquipment().setHelmet(new ItemStack(Material.AIR));
                horseman.setFireTicks(-1);
                horseman.setVisualFire(false);
                // No need to remove PersistentData, if this was Reaper, since it's overwriting
                horseman.getPersistentDataContainer().set(key, PersistentDataType.STRING, "Horseman");


            }
        }

    }




}

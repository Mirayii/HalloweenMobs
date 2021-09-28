package cz.lukynka.halloweenmobs.Sounds;

import cz.lukynka.halloweenmobs.Main;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class Damage implements Listener {

    Main main;

    public Damage(Main plugin) {
        main = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        NamespacedKey key = new NamespacedKey(main, "mobType");
        Entity entity = event.getEntity();
        Location loc = entity.getLocation();
        World world = entity.getWorld();

        if (entity.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {

            if (Objects.equals(entity.getPersistentDataContainer().get(key, PersistentDataType.STRING), "Ghost")) {
                world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 0);
            } else
                if (Objects.equals(entity.getPersistentDataContainer().get(key, PersistentDataType.STRING), "Reaper")) {
                world.playSound(loc, Sound.ENTITY_WITHER_SKELETON_HURT, 1, 0);
            } else
                if (Objects.equals(entity.getPersistentDataContainer().get(key, PersistentDataType.STRING), "Horseman")) {
                    if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
                        event.setCancelled(true);
                        entity.setVisualFire(false);
                        entity.setFireTicks(-1);
                        } else {
                    world.playSound(loc, Sound.ENTITY_SKELETON_HURT, 1, 0);
                }
            }
        }

    }




    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        NamespacedKey key = new NamespacedKey(main, "mobType");
        Entity entity = event.getEntity();
        Location loc = entity.getLocation();
        World world = entity.getWorld();
        EntityType type = entity.getType();

            if(Objects.equals(entity.getPersistentDataContainer().get(key, PersistentDataType.STRING), "Ghost")) {
                world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1, 0);
            }

            if(Objects.equals(entity.getPersistentDataContainer().get(key, PersistentDataType.STRING), "Reaper")) {
                world.playSound(loc, Sound.ENTITY_WITHER_SKELETON_DEATH, 1, 0);
            }
            if(Objects.equals(entity.getPersistentDataContainer().get(key, PersistentDataType.STRING), "Horseman")) {
                world.playSound(loc, Sound.ENTITY_SKELETON_DEATH, 1, 0);
            }
    }


}

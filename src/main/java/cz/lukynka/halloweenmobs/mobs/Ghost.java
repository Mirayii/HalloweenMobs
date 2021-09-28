package cz.lukynka.halloweenmobs.mobs;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import cz.lukynka.halloweenmobs.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import cz.lukynka.halloweenmobs.Utils.chat;
import cz.lukynka.halloweenmobs.Utils.math;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class Ghost implements Listener {

    Main main;


    public Ghost(Main plugin) {
        this.main = plugin;
    }





    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        final int chance = this.main.GhostSpawnChance;
        Entity entity = event.getEntity();
        World world = event.getEntity().getWorld();
        Location loc = event.getEntity().getLocation();
        int random = math.randomNumberInRange(1, 100);

        if(entity.getType() == EntityType.ZOMBIE) {
            if(random <= chance) {
                if(main.isDev) {
                    chat.broadcast("&a[DEBUG] &e&l[ENTITY_GHOST] &7" +random +"-" +chance +" | " +loc);
                }
                Zombie ghost = (Zombie) entity;

                ItemStack ghostplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta gp = (LeatherArmorMeta) ghostplate.getItemMeta();
                gp.setColor(Color.fromRGB(227, 227, 227));
                gp.displayName(Component.text(chat.translated(main.getConfig().getString("Items.ghostTorso"))));
                ghostplate.setItemMeta(gp);

                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) skull.getItemMeta();
                PlayerProfile skin182422855 = Bukkit.createProfile(UUID.fromString("67875d29-0b94-4f13-9462-bac023fead73"), "skin182422855");
                skin182422855.setProperty(new ProfileProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYzMjgzODQ2MTY5MywKICAicHJvZmlsZUlkIiA6ICJkODAwZDI4MDlmNTE0ZjkxODk4YTU4MWYzODE0Yzc5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aGVCTFJ4eCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMTEwN2Y4NGJjMTYwOWM5MDJjYTQ3NDc5ZjQ5ZTFjYTdmMTI1YjIxMTg5NDRkNzYyMWM0NWIxY2MyY2I4M2ZhIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=", "Ht1pFE4iYtAKJa7ZxGM51oc56WVKoMA6Fx2QG/yeaXSI1G7Ez6YFTPzUiIoW5Wo7YvJHkkmpmxADtNAufBCogoii4x4w9lLhZ4GW2Wuehtw9nrPOa/lb5woJU5rCO+MNWTnXW4D5UQ/Hl2H+evCkNrq2qU3Lvvvz+PhB4H2AVqUuUeP+XSim/TKWzXrnv1tIsrvnf1DDk+jxSEu6EUmpkYH3hFH4iR5bcOlmKj8L2x74ad2n/mQYlcc7I45/e1K8cDrcrheKZm2Z4W/CMpkFgOE+gZH/Xy+GY3hfe1dm9XxzAEz1bUp/uUAbj3SdYTbWVn42f6HIbrODt+sCOObn4W3pyo+9BLtzXJVaEFeHlPkL3S8619oiJW9wmal1uEB/1Wzdo7fDIZJk5ifpp2wGWIXSBrWkBqMB4vB4K4xZYO4hfgq8PnFWzB0T7j8B3g73+0vjvADSAzsNdoV65uox9jz0MB0DnXKBENL54OfxNK/Ulq8fJO47mchjP22VTYzCt3MqQRDHQ5nBfkcljkSZwOqsU9RjbJelIsRRE7DrEOdq8S6L61WxyAmABcPaWYA6NuXuSgsY/t5pM/Fl/dtBaHulDCG750FoOkZU5du/TXZkBWC1MzgjRcK26reNMURr5daHpmyM8MFX7ZximJPP7l/K/oqPGV7txTiL3Hv8ElA="));
                sm.setPlayerProfile(skin182422855);
                sm.displayName(Component.text(chat.translated(main.getConfig().getString("Items.ghostHead"))));
                skull.setItemMeta(sm);


                NamespacedKey key = new NamespacedKey(main, "mobType");
                ghost.setInvisible(true);
                ghost.setSilent(true);
                ghost.getPersistentDataContainer().set(key, PersistentDataType.STRING, "Ghost");

                ghost.getEquipment().setChestplate(ghostplate);
                ghost.getEquipment().setHelmet(skull);
                if(!ghost.isAdult()) {
                    world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_HURT, 2, 1);
                } else {
                    world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_HURT, 2, 0);
                }




            }
        }

    }




}

package org.htilssu.fuelfly.handles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.htilssu.fuelfly.FuelFly;
import org.htilssu.fuelfly.events.TimeUpEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class TimeTask extends BukkitRunnable {

    HashMap<UUID, Long> flyContainer;
    FileConfiguration config;

    int time;

    public TimeTask(HashMap<UUID, Long> flyContainer) {
        this.flyContainer = flyContainer;

        FuelFly fuelFly = JavaPlugin.getPlugin(FuelFly.class);

        if (fuelFly.getConfig() != null) {

            int fuelTime = 0;
            try {
                config = fuelFly.getConfig();
                fuelTime = config.getInt("fuel.time");

            } catch (Exception e) {

                Bukkit.getLogger().warning(e.getMessage());
                throw new RuntimeException(e);
            }

            if (fuelTime > 0) {

                time = fuelTime * 60 * 1000;
            }
        }
    }

    @Override
    public void run() {

        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();


        for (Player p : onlinePlayers
        ) {

            if (flyContainer.containsKey(p.getUniqueId())) {

                Long activeTime = flyContainer.get(p.getUniqueId());
                Long elapsed = System.currentTimeMillis() - activeTime;

                if (elapsed > time) {

                    p.setAllowFlight(false);
                    flyContainer.remove(p.getUniqueId());

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("message.time-up")));
                    TimeUpEvent event = new TimeUpEvent(p);
                    // Call the event

                    Bukkit.getServer().getPluginManager().callEvent(event);



                }
            }

        }

    }
}

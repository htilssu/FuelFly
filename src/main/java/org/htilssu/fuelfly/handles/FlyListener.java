package org.htilssu.fuelfly.handles;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.htilssu.fuelfly.utils.FuelUtil;

public class FlyListener implements Listener {
    JavaPlugin plugin;
    FuelUtil fuel;

    public FlyListener(JavaPlugin plugin) {

        this.plugin = plugin;
        fuel = new FuelUtil(plugin);

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){



    }


}






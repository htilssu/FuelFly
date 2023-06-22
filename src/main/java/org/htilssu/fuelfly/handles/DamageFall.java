package org.htilssu.fuelfly.handles;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.htilssu.fuelfly.events.TimeUpEvent;

public class DamageFall implements Listener {
    JavaPlugin plugin;
    boolean checked = false;

    public DamageFall(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onDisableFLy(TimeUpEvent event) {

        Player player = event.getPlayer();
        player.setFlying(false);
        player.setFallDistance(-9999);

    }

}

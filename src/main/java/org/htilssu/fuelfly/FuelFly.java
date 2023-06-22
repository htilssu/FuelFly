package org.htilssu.fuelfly;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.htilssu.fuelfly.cmds.FlyCommand;
import org.htilssu.fuelfly.cmds.FlyTAB;
import org.htilssu.fuelfly.configfile.ConfigFile;
import org.htilssu.fuelfly.handles.DamageFall;
import org.htilssu.fuelfly.handles.FlyListener;

public final class FuelFly extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        printInfo();
        register();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    private void register(){

        new ConfigFile(this);
        getServer().getPluginManager().registerEvents(new DamageFall(this),this);
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("fly").setTabCompleter(new FlyTAB());

    }

    public void printInfo(){
        getLogger().info("Author:");
        getLogger().info(" _   _  _             ");
        getLogger().info("| | | |(_)            ");
        getLogger().info("| |_| | _  ___  _   _ ");
        getLogger().info("|  _  || |/ __|| | | |");
        getLogger().info("| | | || |\\__ \\| |_| |");
        getLogger().info("\\_| |_/|_||___/ \\__,_|");
    }
}

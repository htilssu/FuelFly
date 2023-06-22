package org.htilssu.fuelfly.configfile;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.htilssu.fuelfly.FuelFly;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
    JavaPlugin plugin;
    FileConfiguration config;

    public ConfigFile(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
        checkVersion();
    }


    public void loadConfig() {
        String dataPath = plugin.getDataFolder().getAbsolutePath();
        File file = new File(dataPath, "config.yml");


        if (!file.exists()) {
            plugin.saveDefaultConfig();
            config = plugin.getConfig();
        } else {
            config = plugin.getConfig();

        }

    }

    public void checkVersion() {

        String configVersion = config.getString("version");
        String pluginVersion = plugin.getDescription().getVersion();

        String dataPath = plugin.getDataFolder().getAbsolutePath();
        File file = new File(dataPath, "config.yml");


        //Check
        if (!pluginVersion.equalsIgnoreCase(configVersion)) {

            config.set("version", pluginVersion);
            plugin.saveConfig();
        }
    }

    public  FileConfiguration getConfig(){
       return plugin.getConfig();
    }


}

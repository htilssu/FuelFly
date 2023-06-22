package org.htilssu.fuelfly.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.htilssu.fuelfly.configfile.ConfigFile;

import java.util.ArrayList;
import java.util.List;

public class FuelUtil {
    FileConfiguration config;
    String name;
    int price;
    Material material;
    List<String> lore;
    int time;


    public FuelUtil(JavaPlugin plugin) {

        this.config = plugin.getConfig();

        //set information
        try {

            name = getName();
            material = getMaterial();
            price = getPrice();
            time = getTime();
            lore = getLore();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    public List<String> getLore() {

        List<String> listLore = new ArrayList<>();
        List<String> rawLore;
        try {

            rawLore = (List<String>) config.getList("fuel.lore");

        } catch (Exception e) {

            throw new RuntimeException(e);
        }


        for (String lore : rawLore) {

            listLore.add(ChatColor.translateAlternateColorCodes('&', lore));
        }


        return listLore;
    }

    public String getName() {
        String name = config.getString("fuel.name");
        if (name == null) {

            Bukkit.getLogger().warning("Name's fuel can't be null");
            Bukkit.getLogger().warning(name);

        } else {

            return name;
        }

        return "Fuel fly";
    }


    public Material getMaterial() {
        String material = config.getString("fuel.material");

        if (material == null) {

            return Material.FEATHER;

        } else {

            return Material.matchMaterial(material);
        }

    }

    public int getTime() {

        return config.getInt("fuel.time");
    }

    public int getPrice() {
        return config.getInt("fuel.price");
    }
}

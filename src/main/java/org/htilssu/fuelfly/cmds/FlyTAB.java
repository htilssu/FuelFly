package org.htilssu.fuelfly.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.htilssu.fuelfly.FuelFly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FlyTAB implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> listTab = new ArrayList<>();


        if (strings.length == 1){

            listTab.add("give");

        } else {

            if (strings.length == 2) {

                if(strings[0].equalsIgnoreCase("give")){

                   Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

                    for (Player p : onlinePlayers
                         ) {

                        listTab.add(p.getDisplayName());

                    }
                }

            }
        }

        return listTab;
    }
}

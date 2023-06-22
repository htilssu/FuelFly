package org.htilssu.fuelfly.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.htilssu.fuelfly.handles.TimeTask;
import org.htilssu.fuelfly.utils.FuelUtil;

import java.util.HashMap;
import java.util.UUID;

public class FlyCommand implements CommandExecutor {

    FileConfiguration config;
    HashMap<UUID, Long> flyContainer = new HashMap<UUID, Long>();

    FuelUtil fuelUtil;


    JavaPlugin plugin;

    public FlyCommand(JavaPlugin plugin) {
        this.plugin = plugin;

        config = plugin.getConfig();
        fuelUtil = new FuelUtil(plugin);

        //Check time-out
        TimeTask task = new TimeTask(flyContainer);
        task.runTaskTimer(plugin, 1, 100);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        if (commandSender instanceof Player p) {


            //Toggle fly
            if (p.hasPermission("fuel.fly")) {

                //on-off fly
                if (strings.length == 0) {

                    if (flyContainer.containsKey(p.getUniqueId())) {

                        if (p.getAllowFlight()) {

                            p.setAllowFlight(false);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("message.fly-off")));

                        } else {

                            p.setAllowFlight(true);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("message.fly-on")));

                        }

                    } else {

                        //Check has fuel items
                        Inventory inv = p.getInventory();
                        ItemStack fuel = getFuelItem();
                        boolean hasFuel = false;
                        if (inv.isEmpty() || inv.getContents().length > 0) {
                            for (ItemStack item : inv
                            ) {

                                if (item != null) {
                                    if (item.isSimilar(fuel)) {

                                        item.setAmount(item.getAmount() - 1);
                                        flyContainer.put(p.getUniqueId(), System.currentTimeMillis());

                                        p.setAllowFlight(true);
                                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("message.fly-on")));
                                        hasFuel = true;

                                        break;
                                    }
                                }
                            }
                        }

                        if (!hasFuel) {

                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("message.no-fuel")));
                        }

                    }

                } else if (strings[0].equalsIgnoreCase("give")) { //Give fuel

                    if (strings.length < 2) {

                        p.sendMessage(ChatColor.RED + "Invalid player");

                    } else {

                        Player targetPlayer = Bukkit.getPlayer(strings[1]);

                        if (strings.length < 3) {

                            p.sendMessage(ChatColor.RED + "must have value of fuel");

                        } else {

                            if (strings.length == 3) {

                                if (Bukkit.getPlayer(strings[1]) == null) {

                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("message.player-offline")));

                                } else {

                                    p.getInventory().addItem(getFuelItem(Integer.parseInt(strings[2])));
                                }
                            }
                        }
                    }
                }
            }
        } else {

            //Console
            if (commandSender instanceof ConsoleCommandSender cm) {

                if (strings.length == 0) {

                    Bukkit.getLogger().warning(ChatColor.RED + "This command only can run by player");
                }
            }
        }
        return true;
    }

    private ItemStack getFuelItem() {

        ItemStack fuel = new ItemStack(fuelUtil.getMaterial(), 1);

        ItemMeta fuelMeta = fuel.getItemMeta();

        fuelMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("fuel.name")));

        if (fuelUtil.getLore() != null) {

            fuelMeta.setLore(fuelUtil.getLore());

        }

        fuelMeta.addEnchant(Enchantment.DURABILITY, 10, true);

        fuel.setItemMeta(fuelMeta);
        return fuel;
    }


    private ItemStack getFuelItem(int value) {

        ItemStack fuel = new ItemStack(fuelUtil.getMaterial(), value);

        ItemMeta fuelMeta = fuel.getItemMeta();

        fuelMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("fuel.name")));

        if (fuelUtil.getLore() != null) {

            fuelMeta.setLore(fuelUtil.getLore());

        }

        fuelMeta.addEnchant(Enchantment.DURABILITY, 10, true);

        fuel.setItemMeta(fuelMeta);

        return fuel;
    }

}





package com.meteoritepvp.colorplugin.commands;

import com.meteoritepvp.api.MeteoritePlugin;
import com.meteoritepvp.api.command.Command;
import com.meteoritepvp.api.command.CommandClass;
import com.meteoritepvp.api.command.DefaultCommand;
import com.meteoritepvp.api.inventory.MeteoriteInventory;
import com.meteoritepvp.api.inventory.presets.BasicInventory;
import com.meteoritepvp.api.utils.CC;
import com.meteoritepvp.api.utils.Message;
import com.meteoritepvp.colorplugin.ColorPlugin;
import com.meteoritepvp.colorplugin.Colors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

@Command(name = "color", description = "Store color", aliases = {"c"})
public class MainCommand implements CommandClass {
    @Command(description = "add color for a player",
    args = {"add"},params = "@colors"
    )
    public void add(CommandSender sender,String [] params){
        if(sender instanceof Player&&params.length>=1){
            String name=params[0];
            Colors color=null;
            try {
                color=Colors.valueOf(name.toUpperCase());
            }
            catch (Exception e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cColor not exists"));
                return;
            }
            ColorPlugin.plugin.addColor((Player) sender,color);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aSuccess adding color"));
        }
    }

    @Command(description = "remove oolor for a player",
    args={"remove"},params = "@playerColor"
    )
    public void remove(CommandSender sender,String [] params){
        if(sender instanceof Player&&params.length>=1){
            String name=params[0];
            Colors color=null;
            try {
                color=Colors.valueOf(name.toUpperCase());
            }
            catch (Exception e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cColor not exists"));
                return;
            }

            ColorPlugin.plugin.removeColor((Player) sender,color);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aSuccess removing color"));
        }
    }
    @Command(description = "show colors")
    public void mainCommand(CommandSender sender){
        if(sender instanceof Player){
            Player player=(Player)sender;
            BasicInventory page = new BasicInventory();

            ArrayList<Colors> list=ColorPlugin.plugin.getColor(player);
            for (int i = 0; i < list.size(); i++) {
                page.setItem(i,new ItemStack(Material.valueOf(list.get(i).name()+"_WOOL")));
            }
            page.update();
            MeteoriteInventory inventory=new MeteoriteInventory(ColorPlugin.plugin,"Show all selected colors",9,6,true);
            inventory.applyPage(page);
            inventory.show(player);
        }
    }
}
package com.meteoritepvp.colorplugin;

import com.meteoritepvp.api.MeteoritePlugin;
import org.bukkit.entity.Player;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ColorPlugin extends MeteoritePlugin {

    private final HashMap<Player,ArrayList<Colors>> playerColors=new HashMap<>();

    public void addColor(Player player, Colors color){
        ArrayList<Colors> list=playerColors.getOrDefault(player,new ArrayList<Colors>());
        if(!list.contains(color))list.add(color);
        playerColors.put(player,list);
    }
    public void removeColor(Player player, Colors color){
        ArrayList<Colors> list=playerColors.getOrDefault(player,new ArrayList<Colors>());
        if(!list.contains(color))list.remove(color);
        playerColors.put(player,list);
    }
    public ArrayList<Colors> getColor(Player player){
        return playerColors.getOrDefault(player,new ArrayList<>());
    }
    @Override
    protected void onRegisterMainCommand(String description) {

    }


    @Override
    protected void onRegisterCommands(String... aliases) {
        super.onRegisterCommands("c","colors");
    }

    @Override
    protected void onInit() {
        super.onInit();
        registerCommandClass(com.meteoritepvp.colorplugin.commands.MainCommand.class);

    }

    public static ColorPlugin plugin;
    @Override
    public void onEnable() {
        super.onEnable();
        plugin=this;
        registerPlaceholderParameter("colors", sender ->
                Arrays.stream(Colors.values()).map(Colors::name).collect(Collectors.toList()));

        registerPlaceholderParameter("playerColor", sender ->
                {
                    if(sender instanceof  Player){
                        return playerColors.getOrDefault(sender,new ArrayList<>()).stream().map(Colors::name).collect(Collectors.toList());
                    }
                    else {
                        return new ArrayList<String>();
                    }
                }
                );
    }



}
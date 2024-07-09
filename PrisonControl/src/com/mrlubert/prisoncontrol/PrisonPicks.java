package com.mrlubert.prisoncontrol; 

import java.io.File;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import tech.mcprison.prison.Prison;
import tech.mcprison.prison.mines.PrisonMines;
import tech.mcprison.prison.ranks.PrisonRanks;


@SuppressWarnings("unused")
public class PrisonPicks extends JavaPlugin 
{
    private static PrisonPicks instance; 
    static File customYml;
    static FileConfiguration customConfig;
    PrisonMines prisonMines = Util.getPrisonMines(); 
    PrisonRanks prisonRanks = Util.getPrisonRanks(); 
    Prison prison = Util.getPrison();
    
    @Override
    public void onLoad()
    {
        
        Config.setConfigFolder(this.getDataFolder().getAbsolutePath()); 
        Config.reloadConfigs(); 

        if (prisonMines != null)
        {
            try 
            {
                this.getLogger().info("Loading PrisonControl Pickaxes"); 
                
            }catch (Exception e)
            {
            	this.getLogger().info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            	this.getLogger().info("-=-=-=-=-=-=-=PrisonControlPickaxes=-=-=-=-=-=-");
            	this.getLogger().severe("ERROR With Plugin!");
                this.getLogger().severe("Unable to load plugin! Are you running at least Prison, LuckPerms, Citizens, AreaShop, EssentialsX & AutoPickup?"); 
                this.getLogger().info("-=-=-=-=-=-=-=PrisonControlPickaxes=-=-=-=-=-=-");
                this.getLogger().info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                e.printStackTrace(); 
                this.onDisable(); 
            }
        }
    }
    
    @Override
    public void onEnable()
    {
        instance = this; 

        this.getServer().getPluginManager().registerEvents(new Events(), this);
        this.getCommand("pick").setExecutor(new PickCommands()); 
    }

    @Override
    public void onDisable()
    {
    }
    
    public static PrisonPicks getInstance()
    {
        return instance; 
    }
    
}
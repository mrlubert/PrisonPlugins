package com.mrlubert.prisoncontrol; 

import java.util.ArrayList;
import org.bukkit.Bukkit; 
import org.bukkit.Location; 
import org.bukkit.Material;
import org.bukkit.block.Block; 
import org.bukkit.entity.Player; 
import org.bukkit.inventory.ItemStack; 
import org.bukkit.inventory.meta.ItemMeta; 
import org.bukkit.plugin.Plugin;

import com.philderbeast.autopickup.AutoPickupPlugin;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import tech.mcprison.prison.Prison;
import tech.mcprison.prison.PrisonAPI;
import tech.mcprison.prison.mines.PrisonMines;
import tech.mcprison.prison.ranks.PrisonRanks;


class Util 
{

    static /* varargs */ ItemStack createItemStack(String... lores)
    {
        ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta im = stack.getItemMeta(); 
        im.setDisplayName("");
        ArrayList < String > lore = new ArrayList<>();
        int n = lores.length;
        int n2 = 0; 
        while (n2 < n)
        {
            String str = lores[n2];
            lore.add(str);  ++n2; 
        }
        im.setLore(lore); 
        stack.setItemMeta(im); 
        return stack; 
    }

    static boolean isSpaceAvailable(Player player, ItemStack item)
    {
        //Exclude armor slots - ids 100, 101, 102, 103 - Normal Inventory is slots 0-35
        for (int i = 0; i <= 35; i++ )
        {
            ItemStack slotItem = player.getInventory().getItem(i); 
            if (slotItem == null || (slotItem.getType() == item.getType() 
                && item.getAmount() + slotItem.getAmount() <= slotItem.getMaxStackSize()))
            {
                return true; 
            }
        }
        return false; 
    }

    static int calculateExperienceForBlock(Block block)
    {
        Material mat = block.getType(); 
        int min, max; 

        switch (mat)
        {
            case COAL_ORE:
            case DEEPSLATE_COAL_ORE:
                min = 0; 
                max = 2; 
                break; 
            case DIAMOND_ORE:
            case EMERALD_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case DEEPSLATE_EMERALD_ORE:
                min = 3; 
                max = 7; 
                break; 
            case REDSTONE_ORE:
            case DEEPSLATE_REDSTONE_ORE:
                min = 1; 
                max = 5; 
                break; 
            case LAPIS_ORE:
            case DEEPSLATE_LAPIS_ORE:
                min = 2; 
                max = 5; 
                break; 
            default:
                min = 0; 
                max = 0; 
                break; 
        }


        return min + (int)(Math.random() * ((max - min) + 1)); 
    }


    static boolean canBuild(Player player, Location location)
    {
    	RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(player.getLocation());
        com.sk89q.worldedit.world.World world = BukkitAdapter.adapt(player.getWorld());
        if (!WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(WorldGuardPlugin.inst().wrapPlayer(player), world)) {
            return query.testState(loc, WorldGuardPlugin.inst().wrapPlayer(player), Flags.BUILD);
        } else 
        	return true;
    }

    static WorldGuardPlugin getWorldGuard()
    {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard"); 
        if (plugin == null ||  ! (plugin instanceof WorldGuardPlugin))
        {
            return null; 
        }
        return (WorldGuardPlugin)plugin; 
    }
    
    
    static AutoPickupPlugin getAutoPickup()
    {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("AutoPickup"); 
        if (plugin == null ||  ! (plugin instanceof AutoPickupPlugin))
        {
            return null; 
        }
        return (AutoPickupPlugin)plugin; 
    }
    
    static Prison getPrison() 
    {
    	Plugin plugin = Bukkit.getPluginManager().getPlugin("Prison"); 
        if (plugin == null ||  ! (plugin instanceof Prison))
        {
            return null; 
        }
        return (Prison)plugin; 
    }
    
    static PrisonAPI getPrisonAPI() 
    {
    	Plugin plugin = Bukkit.getPluginManager().getPlugin("Prison"); 
        if (plugin == null ||  ! (plugin instanceof Prison))
        {
            return null; 
        }
        return (PrisonAPI)plugin; 
    }
    
    static PrisonRanks getPrisonRanks() 
    {
    	Plugin plugin = Bukkit.getPluginManager().getPlugin("Prison"); 
        if (plugin == null ||  ! (plugin instanceof Prison))
        {
            return null; 
        }
        return (PrisonRanks)plugin; 
    }
    
    static PrisonMines getPrisonMines() 
    {
    	Plugin plugin = Bukkit.getPluginManager().getPlugin("Prison"); 
        if (plugin == null ||  ! (plugin instanceof Prison))
        {
            return null; 
        }
        return (PrisonMines)plugin; 
    }

}
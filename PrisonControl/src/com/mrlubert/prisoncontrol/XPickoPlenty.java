package com.mrlubert.prisoncontrol;

import java.util.ArrayList;
import java.util.Map;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.Material;

public class XPickoPlenty extends Pick
{
    
    private Material mat = Priority.NONE.mat;

    public static boolean isPick(ItemStack item)
    {
        return (Pick.isPick(item)
                && item.getItemMeta().getLore()
                    .contains(Config.EXPLOSIVE_COLOR + "Explosive" + Config.PICK_O_PLENTY_COLOR + " Pick o'Plenty"));
    }

    public void breakBlock(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ArrayList<Location> locations = new ArrayList<>();

        if (Util.canBuild(player, event.getBlock().getLocation()))
        {
            Location center = event.getBlock().getLocation();

            int radius = 2;
            int bX = center.getBlockX();
            int bY = center.getBlockY();
            int bZ = center.getBlockZ();
            int x = bX - radius;

            while (x < bX + radius)
            {
                int y = bY - radius;
                while (y < bY + radius)
                {
                    int z = bZ - radius;
                    while (z < bZ + radius)
                    {
                        //TODO: this math is painful
                        double distance = (bX - x) * (bX - x) + (bZ - z) * (bZ - z) + (bY - y) * (bY - y);
                        Location block = new Location(center.getWorld(), (double)x, (double)y, (double)z);
                        if (distance < (double)(radius * radius)
                            && Util.canBuild(player, block)
                            && (block.getBlock().getType() != Material.BEDROCK)
                            && (block.getBlock().getType() != Material.AIR))
                        {

                            //set the block to the highest value material
                            block.getWorld().getBlockAt(block).setType(getMaterial(block, player));
                            locations.add(block);

                        } ++ z;
                    } ++ y;
                } ++ x;
            }

            Map<String, Boolean> enchants = getEnchantments(item);
            for (Location l : locations) {
                doDamage(enchants.get(Pick.UNBREAKING), player);
                if(player.getInventory().getItemInMainHand() != null)
                {
                    Block block = player.getWorld().getBlockAt(l);
                    doBreak(block, enchants, player, null);
                    if (!l.equals(event.getBlock().getLocation()))
                    {
                        BlockBreakEvent newEvent = new BlockBreakEvent(block, player);
                        Bukkit.getPluginManager().callEvent(newEvent);
                    }
                }
            }
        }
    }

    private Material getMaterial(Location center, Player player)
    {
        int radius = 2;
        int bX = center.getBlockX();
        int bY = center.getBlockY();
        int bZ = center.getBlockZ();
        int level = 0;
        int x = bX - radius;

        while (x < bX + radius)
        {
            int y = bY - radius;
            while (y < bY + radius)
            {
                int z = bZ - radius;
                while (z < bZ + radius)
                {

                    double distance = (bX - x) * (bX - x) + (bZ - z) * (bZ - z) + (bY - y) * (bY - y);
                    Location block = new Location(center.getWorld(), (double)x, (double)y, (double)z);
                    if (distance < (double)(radius * radius))
                    {
                        if ((level < Priority.getPriority(block.getWorld().getBlockAt(block).getType()).level)
                                &&  ! block.getBlock().hasMetadata("blockBreaker"))
                        {

                            mat = block.getWorld().getBlockAt(block).getType();
                            level = Priority.getPriority(mat).level;
                            
                        }
                    }
                    if (distance < (double)(radius * radius)
                            && (block.getBlock().getType() != Material.BEDROCK)
                            && (block.getBlock().getType() != Material.AIR))
                    {
                        if ((level < Priority.getPriority(block.getWorld().getBlockAt(block).getType()).level)
                                &&  ! block.getBlock().hasMetadata("blockBreaker"))
                        {

                            mat = block.getWorld().getBlockAt(block).getType();
                            level = Priority.getPriority(mat).level;
                            
                        }
                    } ++ z;
                } ++ y;
            } ++ x;
        }
        return mat;
    }

    @Override
    protected char[] parseInt(String string) {
        // TODO Auto-generated method stub
        return null;
    }

}
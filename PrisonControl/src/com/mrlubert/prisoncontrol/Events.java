package com.mrlubert.prisoncontrol;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import java.util.ArrayList;
import java.util.List;

public class Events implements Listener
{

    public static boolean aoepick = false;

    static final List< String > hideRepair = new ArrayList<>();

    Events(){}

	@EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (aoepick)
        {
            //stop our event recurring
            return;
        }
        //Are we using one of our picks
        if (Xpick.isPick(item))
        {
            aoepick = true;
            //this is an Xpick
            Xpick x = new Xpick();
            x.breakBlock(event);
        }else if (Pickoplenty.isPick(item))
        {
            Pickoplenty pop = new Pickoplenty();
            pop.breakBlock(event);
        }else if (XPickoPlenty.isPick(item))
        {
            aoepick = true;
            XPickoPlenty xpop = new XPickoPlenty();
            xpop.breakBlock(event);
        }

        aoepick = false;

    }
    
    @SuppressWarnings("static-access")
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if ((event.getAction() == Action.RIGHT_CLICK_BLOCK)) 
        {
        	if (player.getInventory().getItemInMainHand().getType() == Material.ACACIA_BOAT) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}
        	if (player.getInventory().getItemInMainHand().getType() == Material.BIRCH_BOAT) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}
        	if (player.getInventory().getItemInMainHand().getType() == Material.CHERRY_BOAT) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}
        	if (player.getInventory().getItemInMainHand().getType() == Material.DARK_OAK_BOAT) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}
        	if (player.getInventory().getItemInMainHand().getType() == Material.JUNGLE_BOAT) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}
        	if (player.getInventory().getItemInMainHand().getType() == Material.MANGROVE_BOAT) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}
        	if (player.getInventory().getItemInMainHand().getType() == Material.OAK_BOAT) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}
        	if (player.getInventory().getItemInMainHand().getType() == Material.SPRUCE_BOAT) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}      
        	if (player.getInventory().getItemInMainHand().getType() == Material.WATER_BUCKET && player.getGameMode() != player.getGameMode().CREATIVE) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}  
        	if (player.getInventory().getItemInMainHand().getType() == Material.BUCKET && player.getGameMode() != player.getGameMode().CREATIVE) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}  
        	if (player.getInventory().getItemInMainHand().getType() == Material.LAVA_BUCKET && player.getGameMode() != player.getGameMode().CREATIVE) {
        		player.sendMessage("No *judge*");
        		event.setCancelled(true);
        	}  
        }
        //are they using one of our picks
        ItemMeta itemMeta = item.getItemMeta();
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
            && (Xpick.isPick(item) || Pickoplenty.isPick(item) || XPickoPlenty.isPick(item))
            && ((Damageable) itemMeta).getDamage() > 0)
        {
            if (!hideRepair.contains(player.getName()))
            {
                if (Pickoplenty.isPick(item))
                {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Config.CHAT_POP_REPAIR + "[Pickaxe Repaired]"));
                } else if (XPickoPlenty.isPick(item))
                {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Config.CHAT_XPOP_REPAIR + "[Pickaxe Repaired]"));
                } else
                {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Config.CHAT_EXPLOSIVE_REPAIR + "[Pickaxe Repaired]"));
                }
            }
            //short s = 0;
            
            ((Damageable) itemMeta).setDamage(0);
            item.setItemMeta(itemMeta);
        }
    }

	@EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
    	Player player = event.getPlayer();
    	Material i = event.getBlockPlaced().getType();
        Block b = event.getBlock();
        b.setMetadata("blockBreaker", new FixedMetadataValue(PrisonPicks.getInstance(), player.getUniqueId()));
        if (i == Material.SHULKER_BOX || i == Material.BLACK_SHULKER_BOX || i == Material.RED_SHULKER_BOX || i == Material.BLUE_SHULKER_BOX || i == Material.BROWN_SHULKER_BOX || i == Material.CYAN_SHULKER_BOX || i == Material.GRAY_SHULKER_BOX || i == Material.GREEN_SHULKER_BOX || i == Material.LIGHT_BLUE_SHULKER_BOX || i == Material.LIGHT_GRAY_SHULKER_BOX || i == Material.LIME_SHULKER_BOX || i == Material.MAGENTA_SHULKER_BOX || i == Material.ORANGE_SHULKER_BOX || i == Material.PINK_SHULKER_BOX || i == Material.PURPLE_SHULKER_BOX || i == Material.WHITE_SHULKER_BOX || i == Material.YELLOW_SHULKER_BOX) {
        	player.sendMessage(ChatColor.DARK_PURPLE + "ShulkerBox: " + ChatColor.DARK_RED + "Shift + Drop Shulker To Open It");
        	event.setCancelled(true);
        }
        }
    }
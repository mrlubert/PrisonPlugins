/*     */ package com.mrlubert.prisoncontrol;
/*     */ import net.citizensnpcs.api.event.NPCRightClickEvent;
/*     */ import net.citizensnpcs.api.npc.NPC;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener; 

/*     */ public class NPCRightClick
/*     */   implements Listener
/*     */ {
/*     */   @SuppressWarnings("unused")
            private Gamble2Utils gamble2Utils;
/*     */   private PrisonPicks main;
/*     */   
/*     */   public NPCRightClick(PrisonPicks plugin) {
/*  49 */     this.main = plugin;
/*  50 */     this.gamble2Utils = this.main.gamble2Utils;
              
/*     */   }
/*     */   public static String format(String text) {
/*  56 */     return ChatColor.translateAlternateColorCodes('&', text);
/*     */   }
/*     */   
@EventHandler
/*     */   public void onRightClick(NPCRightClickEvent e) {
	         if(!e.getClicker().isHandRaised()) {
              e.setCancelled(true);
	         }
/*  60 */     Player p = e.getClicker();
/*  62 */     NPC npc = e.getNPC();

            if (npc.getName().equalsIgnoreCase("Netherite") && p.isSneaking()) {
/* 192 */       String name = p.getName();
/*  81 */         ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
/*  82 */         String command = "crazycrates preview Netherite " + name;
/*  83 */         Bukkit.dispatchCommand((CommandSender)console, command);
/*     */     } else
	         if (npc.getName().equalsIgnoreCase("TestuRLuck") && p.isSneaking()) {
/* 192 */       String name = p.getName();
/*  81 */         ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
/*  82 */         String command = "crazycrates preview Emerald " + name;
/*  83 */         Bukkit.dispatchCommand((CommandSender)console, command);
/*     */     } else

/*  63 */     if (npc.getName().equalsIgnoreCase("Netherite") && !p.isSneaking()) {
/*  64 */       if (!(p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_INGOT)) {
/*  65 */         p.sendMessage(format("&0[&5Gambling&0]&r &4You Need An Netherite Ingot In Your Hand To Use This!"));
/*     */       }
                 else if (p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_INGOT) {
/*  70 */           if (p.getGameMode() == GameMode.CREATIVE) {
	                String name = p.getName();
/*  81 */         ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
/*  82 */         String command = "crazycrates forceopen Netherite " + name;
/*  83 */         Bukkit.dispatchCommand((CommandSender)console, command);
/*     */           }
/*  76 */         else if (p.getGameMode() != GameMode.CREATIVE) {
/*  77 */           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                    p.updateInventory();
                    String name = p.getName();
  /*  81 */         ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
  /*  82 */         String command = "crazycrates forceopen Netherite " + name;
  /*  83 */         Bukkit.dispatchCommand((CommandSender)console, command);
                       }
/*     */         } 
/*  79 */         p.updateInventory();
/*     */       } 
/*  85 */      else if (npc.getName().equalsIgnoreCase("TestuRLuck") && !p.isSneaking()) {
	/*  64 */       if (!(p.getInventory().getItemInMainHand().getType() == Material.EMERALD)) {
		/*  65 */         p.sendMessage(format("&0[&5Gambling&0]&r &4You Need An Emerald In Your Hand To Use This!"));
	/*     */       }
	                 else if (p.getInventory().getItemInMainHand().getType() == Material.EMERALD) {
		/*  70 */           if (p.getGameMode() == GameMode.CREATIVE) {
			                String name = p.getName();
		/*  81 */         ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		/*  82 */         String command = "crazycrates forceopen Emerald " + name;
		/*  83 */         Bukkit.dispatchCommand((CommandSender)console, command);
		/*     */           }
		/*  76 */         else if (p.getGameMode() != GameMode.CREATIVE) {
		/*  77 */           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
		                    p.updateInventory();
		                    String name = p.getName();
		  /*  81 */         ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		  /*  82 */         String command = "crazycrates forceopen Emerald " + name;
		  /*  83 */         Bukkit.dispatchCommand((CommandSender)console, command);
		                       }
		/*     */         } 
		/*  79 */         p.updateInventory();
		/*     */       } 
		/*  85 */     }
/*     */   }
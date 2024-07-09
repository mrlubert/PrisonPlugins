package com.mrlubert.prisoncontrol;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import me.wiefferink.areashop.AreaShop;
/*    */ import me.wiefferink.areashop.regions.BuyRegion;
/*    */ import me.wiefferink.areashop.regions.RentRegion;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class CellCommand
/*    */   implements CommandExecutor
/*    */ {
/* 43 */   private AreaShop as = getAreaShop();
/*    */ 
/*    */   
/*    */   private static AreaShop getAreaShop() {
/* 47 */     Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("AreaShop");
/* 48 */     if (plugin == null || !(plugin instanceof AreaShop)) {
/* 49 */       return null;
/*    */     }
/* 51 */     return (AreaShop)plugin;
/*    */   }
/*    */   public CellCommand(PrisonPicks plugin) {}
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
/* 55 */     if (!(sender instanceof Player)) {
/* 56 */       return false;
/*    */     }
/* 58 */     Player p = (Player)sender;
/* 59 */     HashSet<RentRegion> rentRegions = new HashSet<>();
/* 60 */     HashSet<BuyRegion> buyRegions = new HashSet<>();
/* 61 */     List<RentRegion> lr = this.as.getFileManager().getRents();
/* 62 */     List<BuyRegion> br = this.as.getFileManager().getBuys();
/*    */     
/* 64 */     if (arguments.length > 0) {
/* 65 */       for (BuyRegion region : br) {
/* 66 */         if (!region.isOwner((OfflinePlayer)p))
/*    */           continue; 
/* 68 */         buyRegions.add(region);
/*    */       } 
/* 70 */       Iterator<BuyRegion> iterator = buyRegions.iterator();
/* 71 */       if (iterator.hasNext()) {
/* 72 */         BuyRegion r = iterator.next();
/* 73 */         r.getTeleportFeature().teleportPlayer(p);
/* 74 */         return false;
/*    */       } 
/*    */     } else {
/* 77 */       for (RentRegion region : lr) {
/* 78 */         if (!region.isOwner((OfflinePlayer)p))
/*    */           continue; 
/* 80 */         rentRegions.add(region);
/*    */       } 
/* 82 */       Iterator<RentRegion> iterator = rentRegions.iterator();
/* 83 */       if (iterator.hasNext()) {
/* 84 */         RentRegion r = iterator.next();
/*    */         
/* 86 */         @SuppressWarnings("unused")
List<Location> sign = r.getSignsFeature().getSignLocations();
/* 87 */         r.getTeleportFeature().teleportPlayer(p);
/* 88 */         return false;
/*    */       } 
/*    */     } 
/* 91 */     p.sendMessage(ChatColor.RED + "You don't have a cell to go to.");
/* 92 */     return false;
/*    */   }
/*    */ }
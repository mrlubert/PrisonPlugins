/*    */ package com.mrlubert.prisoncontrol;
/*    */ 
/*    */ import com.earth2me.essentials.api.NoLoanPermittedException;
/*    */ import com.earth2me.essentials.api.UserDoesNotExistException;
/*    */ import java.math.BigDecimal;
/*    */ import net.ess3.api.events.UserBalanceUpdateEvent;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ public class UserBalanceUpdate
/*    */   implements Listener
/*    */ {
/*    */   private PrisonPicks main;
/*    */   private MaxCashUtils mcUtils;
/*    */   
/*    */   public UserBalanceUpdate(PrisonPicks plugin) {
/* 33 */     this.main = plugin;
/* 34 */     this.mcUtils = new MaxCashUtils(this.main);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onBalanceUpdate(UserBalanceUpdateEvent e) throws NoLoanPermittedException, UserDoesNotExistException {
/* 39 */     Player player = e.getPlayer();
/* 40 */     BigDecimal newBal = e.getNewBalance();
/* 41 */     Object[] prestiges = this.mcUtils.prestiges.toArray();
/* 42 */     if (this.mcUtils.hasPermission(player, "ezprestige.prestige.20")) {
/*    */       return;
/*    */     }
/* 45 */     for (int n = 19; n > 0; ) {
/* 46 */       if (!this.mcUtils.hasPermission(player, "ezprestige.prestige." + n)) {
/* 47 */         n--; continue;
/* 48 */       }  if (newBal.compareTo((BigDecimal)prestiges[n]) == 1) {
/* 49 */         e.setNewBalance((BigDecimal)prestiges[n]);
/*    */       }
/*    */       return;
/*    */     } 
/* 53 */     if (newBal.compareTo((BigDecimal)prestiges[0]) == 1) {
/* 54 */       e.setNewBalance((BigDecimal)prestiges[0]);
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


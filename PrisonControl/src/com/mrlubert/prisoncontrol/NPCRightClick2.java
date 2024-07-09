/*     */ package com.mrlubert.prisoncontrol;
/*     */ import net.citizensnpcs.api.event.NPCRightClickEvent;
/*     */ import net.citizensnpcs.api.npc.NPC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta; 

/*     */ public class NPCRightClick2
/*     */   implements Listener
/*     */ {
/*     */   @SuppressWarnings("unused")
            private Gamble2Utils gamble2Utils;
/*     */   private PrisonPicks main;
            private List<ItemStack> hoes;
            public HashMap<String, String> commands;
            public HashMap<String, ItemStack> items;
/*     */   
/*     */   public NPCRightClick2(PrisonPicks plugin) {
/*  49 */     this.main = plugin;
/*  50 */     this.gamble2Utils = this.main.gamble2Utils;
              this.items = new HashMap<>();
              commands = new HashMap<>();
              loadRewardMaps();
              this.hoes = Arrays.asList(new ItemStack[] { (ItemStack)this.items.get("Phoenix_Hoe"), 
            		  /*  51 */           (ItemStack)this.items.get("Turtle_Hoe"), (ItemStack)this.items.get("Rabbit_Hoe"), 
            		  /*  52 */           (ItemStack)this.items.get("Seagull_Hoe"), (ItemStack)this.items.get("Myra_Hoe") });
              
/*     */   }
/*     */   public static String format(String text) {
/*  56 */     return ChatColor.translateAlternateColorCodes('&', text);
/*     */   }
/*     */   
/*     */   public void loadRewardMaps() {
	/* 194 */     items.clear();
	              ItemStack mHoe = new ItemStack(Material.GOLDEN_HOE);
	              ItemStack pHoe = new ItemStack(Material.GOLDEN_HOE);
	/* 196 */     ItemStack tHoe = new ItemStack(Material.GOLDEN_HOE);
	/* 197 */     ItemStack rHoe = new ItemStack(Material.GOLDEN_HOE);
	/* 198 */     ItemStack sHoe = new ItemStack(Material.GOLDEN_HOE);
	/* 199 */     ItemStack prestigeToken = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
	              ItemMeta mM = mHoe.getItemMeta();
	              ItemMeta pM = pHoe.getItemMeta();
	/* 200 */     ItemMeta tM = tHoe.getItemMeta();
	/* 201 */     ItemMeta rM = rHoe.getItemMeta();
	/* 202 */     ItemMeta sM = sHoe.getItemMeta();
	/* 203 */     ItemMeta prestigeM = prestigeToken.getItemMeta();
	/* 204 */     mM.setDisplayName(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Myra" + ChatColor.DARK_AQUA + 
	/* 205 */         "]" + ChatColor.RESET + " UPGRADE");
	/* 206 */     pM.setDisplayName(ChatColor.GOLD + "[" + ChatColor.DARK_RED + "Phoenix" + 
	/* 207 */         ChatColor.GOLD + "]" + ChatColor.RESET + " UPGRADE");
	/* 204 */     sM.setDisplayName(ChatColor.GRAY + "[" + ChatColor.GOLD + "Seagull" + ChatColor.GRAY + 
	/* 205 */         "]" + ChatColor.RESET + " UPGRADE");
	/* 206 */     rM.setDisplayName(ChatColor.LIGHT_PURPLE + "[" + ChatColor.WHITE + "Rabbit" + 
	/* 207 */         ChatColor.LIGHT_PURPLE + "]" + ChatColor.RESET + " UPGRADE");
	/* 208 */     tM.setDisplayName(ChatColor.GOLD + "[" + ChatColor.YELLOW + "Turtle" + ChatColor.GOLD + 
	/* 209 */         "]" + ChatColor.RESET + " UPGRADE");
	/* 210 */     prestigeM.setDisplayName(ChatColor.GREEN + "Prestige Token");
	/* 211 */     ArrayList<String> Lore = new ArrayList<>();
	/* 212 */     ArrayList<String> prestigeLore = new ArrayList<>();
	/* 213 */     Lore.add(ChatColor.GOLD + "Right click on Sketch in the pagoda to claim this.");
	/* 214 */     prestigeLore.add(ChatColor.GOLD + "If you hold this when you prestige,");
	/* 215 */     prestigeLore.add(ChatColor.GOLD + "you won't lose your cell.");
	              mM.setLore(Lore);
	              pM.setLore(Lore);
	/* 216 */     tM.setLore(Lore);
	/* 217 */     rM.setLore(Lore);
	/* 218 */     sM.setLore(Lore);
	/* 219 */     prestigeM.setLore(prestigeLore);
	              mHoe.setItemMeta(mM);
	              pHoe.setItemMeta(pM);
	/* 220 */     tHoe.setItemMeta(tM);
	/* 221 */     rHoe.setItemMeta(rM);
	/* 222 */     sHoe.setItemMeta(sM);
	/* 223 */     prestigeM.addEnchant(Enchantment.UNBREAKING, 1, true);
	/* 224 */     prestigeToken.removeEnchantment(Enchantment.UNBREAKING);
	/* 225 */     prestigeToken.setItemMeta(prestigeM);
	              items.put("Myra_Hoe", mHoe);
	              items.put("Phoenix_Hoe", pHoe);
	/* 239 */     items.put("Turtle_Hoe", tHoe);
	/* 240 */     items.put("Rabbit_Hoe", rHoe);
	/* 241 */     items.put("Seagull_Hoe", sHoe);
	/* 242 */     items.put("Prestige_Token", prestigeToken);
	/* 247 */     commands.put("Myra", "lp user [user] parent add myra");
	/* 248 */     commands.put("Phoenix", "lp user [user] parent add phoenix");
	/* 249 */     commands.put("Turtle", "lp user [user] parent add turtle");
	/* 250 */     commands.put("Rabbit", "lp user [user] parent add rabbit");
	/* 251 */     commands.put("Seagull", "lp user [user] parent add seagull");
	/*     */   }
public static boolean isPlayerInGroup(Player player, String group) {
    return player.hasPermission("group." + group);
}
@EventHandler
/*     */   public void onRightClick(NPCRightClickEvent e) {
	              Player p = e.getClicker();
	/*  62 */     NPC npc = e.getNPC();
	              @SuppressWarnings("unused")
				boolean groups = isPlayerInGroup(p, null);
               if (npc.getName().equalsIgnoreCase("oisketchio")) {
/*  82 */       if (p.getInventory().contains(this.hoes.get(3))) {
/*  83 */         if (p.hasPermission("group." + "Myra") || p.hasPermission("group." + "Phoenix") || p.hasPermission("group." + "Turtle") || 
/*  84 */           p.hasPermission("group." + "Rabbit") || p.hasPermission("group." + "Seagull")) {
/*  85 */           p.sendMessage(ChatColor.RED + "You already have Seagull or higher.");
/*     */           return;
/*     */         } 
/*  88 */         int slot = p.getInventory().first(this.hoes.get(3));
/*  89 */         if (p.getInventory().getItem(slot).getAmount() == 1) {
/*  90 */           p.getInventory().setItem(slot, null);
/*     */         } else {
/*  92 */           p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
/*     */         } 
/*  94 */         p.updateInventory();
/*  97 */         this.main.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), 
/*  98 */             "lp user " + p.getName() + " parent add Seagull");
/*  99 */         p.sendMessage(ChatColor.GREEN + "You have claimed your Seagull rank.");
/*     */         return;
/*     */       } 
/* 102 */       if (p.getInventory().contains(this.hoes.get(2))) {
/* 103 */         if (p.hasPermission("group." + "Myra") || p.hasPermission("group." + "Phoenix") || p.hasPermission("group." + "Turtle") || 
/* 104 */           p.hasPermission("group." + "Rabbit")) {
/* 105 */           p.sendMessage(ChatColor.RED + "You already have Rabbit or higher.");
/*     */           return;
/*     */         } 
/* 108 */         int slot = p.getInventory().first(this.hoes.get(2));
/* 109 */         if (p.getInventory().getItem(slot).getAmount() == 1) {
/* 110 */           p.getInventory().setItem(slot, null);
/*     */         } else {
/* 112 */           p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
/*     */         } 
/* 114 */         p.updateInventory();
/* 115 */         String s = ((String)this.commands.get("Rabbit")).replace("[user]", p.getName());
/* 116 */         this.main.getServer().dispatchCommand((CommandSender)this.main.getServer().getConsoleSender(), s);
                  this.main.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), 
/*     */             "lp user " + p.getName() + " parent remove Seagull");
/* 119 */         p.sendMessage(ChatColor.GREEN + "You have claimed your Rabbit rank.");
/*     */         return;
/*     */       } 
/* 122 */       if (p.getInventory().contains(this.hoes.get(1))) {
/* 123 */         if (p.hasPermission("group." + "Myra") || p.hasPermission("group." + "Phoenix") || p.hasPermission("group." + "Turtle")) {
/* 124 */           p.sendMessage(ChatColor.RED + "You already have Turtle or higher.");
/*     */           return;
/*     */         } 
/* 127 */         int slot = p.getInventory().first(this.hoes.get(1));
/* 128 */         if (p.getInventory().getItem(slot).getAmount() == 1) {
/* 129 */           p.getInventory().setItem(slot, null);
/*     */         } else {
/* 131 */           p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
/*     */         } 
/* 133 */         p.updateInventory();
/* 134 */         String s = ((String)this.commands.get("Turtle")).replace("[user]", p.getName());
/* 135 */         this.main.getServer().dispatchCommand((CommandSender)this.main.getServer().getConsoleSender(), s);
                  this.main.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), 
/*     */             "lp user " + p.getName() + " parent remove Rabbit");
/* 138 */         p.sendMessage(ChatColor.GREEN + "You have claimed your Turtle rank.");
/*     */         return;
/*     */       } 
/* 141 */       if (p.getInventory().contains(this.hoes.get(0))) {
/* 142 */         if (p.hasPermission("group." + "Myra") || p.hasPermission("group." + "Phoenix")) {
/* 143 */           p.sendMessage(ChatColor.RED + "You already have Phoenix or Higher.");
/*     */           return;
/*     */         } 
/* 146 */         int slot = p.getInventory().first(this.hoes.get(0));
/* 147 */         if (p.getInventory().getItem(slot).getAmount() == 1) {
/* 148 */           p.getInventory().setItem(slot, null);
/*     */         } else {
/* 150 */           p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
/*     */         } 
/* 152 */         p.updateInventory();
/* 153 */         String s = ((String)this.commands.get("Phoenix")).replace("[user]", p.getName());
/* 154 */         this.main.getServer().dispatchCommand((CommandSender)this.main.getServer().getConsoleSender(), s);
                  this.main.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), 
/*     */             "lp user " + p.getName() + " parent remove Turtle");
/* 157 */         p.sendMessage(ChatColor.GREEN + "You have claimed your Phoenix rank.");
/*     */         return;
/*     */       } 
/* 160 */       if (p.getInventory().contains(this.hoes.get(4))) {
/* 161 */         if (p.hasPermission("group." + "Myra")) {
/* 162 */           p.sendMessage(ChatColor.RED + "You already have Myra.");
/*     */           return;
/*     */         } 
/* 165 */         int slot = p.getInventory().first(this.hoes.get(4));
/* 166 */         if (p.getInventory().getItem(slot).getAmount() == 1) {
/* 167 */           p.getInventory().setItem(slot, null);
/*     */         } else {
/* 169 */           p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
/*     */         } 
/* 171 */         p.updateInventory();
/* 172 */         String s = ((String)this.commands.get("Myra")).replace("[user]", p.getName());
/* 173 */         this.main.getServer().dispatchCommand((CommandSender)this.main.getServer().getConsoleSender(), s);
                  this.main.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), 
/*     */             "lp user " + p.getName() + " parent remove Phoenix");
/* 176 */         p.sendMessage(ChatColor.GREEN + "You have claimed your Myra rank.");
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */

} 
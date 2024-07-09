package com.mrlubert.prisoncontrol;

import java.util.ArrayList;
import java.util.HashMap;

/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class KappaKCommand
/*     */   implements CommandExecutor {
/*     */   private PrisonPicks main;
           public HashMap<String, ItemStack> items;
/*     */   
/*     */   public KappaKCommand(PrisonPicks plugin) {
/*  19 */     this.main = plugin;
              this.items = new HashMap<>();
              loadRewardMaps();
/*     */   }
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
	/*     */   }

/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
/*  24 */     if (!(sender instanceof Player)) {
/*  25 */       if (arguments.length != 2) {
/*  26 */         this.main.getLogger().info(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]");
/*  27 */         return false;
/*     */       } 
/*  29 */       if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(arguments[0].toLowerCase())))
/*  30 */       { String str; switch ((str = arguments[1].toLowerCase()).hashCode()) { case -1276224445: if (!str.equals("prestige")) {
/*     */               break;
/*     */             }
/*  52 */             Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
/*  53 */               .addItem(new ItemStack[] { (ItemStack)this.items.get("Prestige_Token") });
/*     */ 
/*  62 */             return false;case -938645478: if (!str.equals("rabbit")) break;  Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Rabbit_Hoe") }); return false;case -862422724: if (!str.equals("turtle")) break;  Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Turtle_Hoe") }); return false;case -595742321: if (!str.equals("phoenix")) break;  Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Phoenix_Hoe") }); return false;case 3367131: if (!str.equals("myra")) break;  Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Myra_Hoe") }); return false;case 1968018365: if (!str.equals("seagull")) break;  Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Seagull_Hoe") }); return false; }  this.main.getLogger().info(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]"); } else { this.main.getLogger().info(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]"); }  return false;
/*     */     } 
/*  64 */     Player player = (Player)sender;
/*  65 */     if (player.isOp())
/*  66 */     { if (arguments.length != 2) {
/*  67 */         player.sendMessage(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]");
/*  68 */         return false;
/*     */       } 
/*  70 */       if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(arguments[0].toLowerCase())))
/*  71 */       { String str; switch ((str = arguments[1].toLowerCase()).hashCode()) { case -1276224445: if (!str.equals("prestige")) {
/*     */               break;
/*     */             }
/*  89 */             Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
/*  90 */               .addItem(new ItemStack[] { (ItemStack)this.items.get("Prestige_Token") });
/* 102 */             return false;case -938645478: if (!str.equals("rabbit")) break;  Bukkit.getPlayer(arguments[0]).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Rabbit_Hoe") }); return false;case -862422724: if (!str.equals("turtle")) break;  Bukkit.getPlayer(arguments[0]).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Turtle_Hoe") }); return false;case -595742321: if (!str.equals("phoenix")) break;  Bukkit.getPlayer(arguments[0]).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Phoenix_Hoe") }); return false;case 3367131: if (!str.equals("myra")) break;  Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Myra_Hoe") }); return false;case 1968018365: if (!str.equals("seagull")) break;  Bukkit.getPlayer(arguments[0]).getInventory().addItem(new ItemStack[] { (ItemStack)this.items.get("Seagull_Hoe") }); return false; }  player.sendMessage(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]"); } else { player.sendMessage(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]"); }  } else { player.sendMessage("You are not OP."); }  return false;
/*     */   }
/*     */ }

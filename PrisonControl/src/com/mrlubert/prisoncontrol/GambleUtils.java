package com.mrlubert.prisoncontrol;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;

import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.GroupManager;
/*     */ 
/*     */ 
/*     */ public class GambleUtils
/*     */ {
            private LuckPerms groupManager;
/*     */   public HashMap<String, ItemStack> items;
/*     */   public HashMap<String, String> commands;
/*  45 */   public Set<String> ranks = new HashSet<>(Arrays.asList(new String[] { "Myra", "Phoenix", "Turtle", "Rabbit", "Seagull" }));
            public Set<String> prestigeTokened;
/*     */   
/*     */   public GambleUtils() {
/*  57 */     items = new HashMap<>();
/*  58 */     commands = new HashMap<>();
/*  66 */     loadRewardMaps();
/*  67 */     Bukkit.getLogger().info("[Kappa] Loaded successfully.");
              this.prestigeTokened = new HashSet<>(Arrays.asList(new String[0]));
/*  68 */     
/*     */   }
/*     */   
/*     */   
/*     */   public void loadRewardMaps() {
/* 194 */     items.clear();
/* 195 */     commands.clear();
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
/*     */   
/*     */   public List<String> getGroups(Player base) {
/* 279 */     GroupManager handler = groupManager.getGroupManager();
/* 280 */     if (handler == null) {
/* 281 */       return null;
/*     */     }
/* 283 */     return Arrays.asList(handler.getGroup(base.getName()).toString());
/*     */   }
/*     */ }

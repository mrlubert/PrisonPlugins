/*     */ package com.mrlubert.prisoncontrol;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ThreadLocalRandom;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ //import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.server.PluginDisableEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginManager;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.GroupManager;
/*     */ 
/*     */ public class Gamble2Utils
/*     */ {
/*     */   private PrisonPicks main;
/*     */   private GroupManager groupManager;
/*     */   public int totalPossible;
/*     */   public HashMap<String, Integer> totals;
/*     */   public HashMap<String, ItemStack> items;
/*     */   public HashMap<String, String> commands;
/*     */   public Set<String> prestigeTokened;
/*     */   public Map<String, Object> rewards;
/*     */   public Map<String, Object> tot;
/*  48 */   public Set<String> ranks = new HashSet<>(Arrays.asList(new String[] { "Myra", "Phoenix", "Turtle", "Rabbit", "Seagull" }));
/*  49 */   public Set<String> picks = new HashSet<>(Arrays.asList(new String[] { "XPick", "PickoPlenty" }));
/*     */   public static HashMap<String, String> suffixes;
/*     */   File customYml;
/*     */   File prestigeTokens;
/*     */   FileConfiguration customConfig;
/*     */   FileConfiguration prestigeTokensConfig;
/*     */   
/*     */   public Gamble2Utils(PrisonPicks plugin) {
/*  57 */     this.main = plugin;
/*  58 */     this.totalPossible = 0;
/*  59 */     this.totals = new HashMap<>();
/*  60 */     this.items = new HashMap<>();
/*  61 */     this.commands = new HashMap<>();
/*  62 */     suffixes = new HashMap<>();
/*  63 */     this.prestigeTokened = new HashSet<>(Arrays.asList(new String[0]));
/*  64 */     this.customYml = new File(this.main.getDataFolder() + "/totals.yml");
/*  65 */     this.customConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.customYml);
/*  66 */     this.prestigeTokens = new File(this.main.getDataFolder() + "/prestigeTokens.yml");
/*  67 */     this.prestigeTokensConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.prestigeTokens);
/*  68 */     //loadConfiguration();
/*  69 */     loadRewardMaps();
/*  70 */     this.main.getLogger().info("[KappaGamble] Loaded successfully.");
/*  71 */     PluginManager pluginManager = plugin.getServer().getPluginManager();
/*  72 */     Plugin GMplugin = pluginManager.getPlugin("GroupManager");
/*  73 */     if (GMplugin != null && GMplugin.isEnabled()) {
/*  74 */       this.groupManager = (GroupManager)GMplugin;
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadConfiguration() {
/*  79 */     FileConfiguration config = this.main.getConfig();
/*  80 */     this.rewards = config.getConfigurationSection("Gamble.rewards").getValues(false);
/*  81 */     //ConfigurationSection x = this.customConfig.getConfigurationSection("Totals");
/*  82 */     //this.tot = x.getValues(false);
/*  83 */     for (String key22 : this.rewards.keySet()) {
/*  84 */       this.totalPossible += ((Integer)this.rewards.get(key22)).intValue();
/*     */     }
/*  86 */     for (String key22 : this.tot.keySet()) {
/*  87 */       this.totals.put(key22, (Integer)this.tot.get(key22));
/*     */     }
/*  89 */     this.prestigeTokened.addAll(this.prestigeTokensConfig.getStringList("Tokened"));
/*     */   }
/*     */   
/*     */   public void saveCustomYml() {
/*  93 */     for (String s : this.totals.keySet()) {
/*  94 */       this.customConfig.set("Totals." + s, this.totals.get(s));
/*     */     }
/*  96 */     ArrayList<String> tokenedList = new ArrayList<>();
/*  97 */     tokenedList.addAll(this.prestigeTokened);
/*  98 */     this.prestigeTokensConfig.set("Tokened", tokenedList);
/*     */     try {
/* 100 */       this.customConfig.save(this.customYml);
/* 101 */       this.prestigeTokensConfig.save(this.prestigeTokens);
/* 102 */     } catch (IOException e) {
/* 103 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String randomReward() {
/* 108 */     int rand = getRandom(1, this.totalPossible);
/* 109 */     int currentMax = this.totalPossible;
/* 110 */     int currentMin = 0;
/* 111 */     int nextMax = 0;
/* 112 */     for (String s : this.rewards.keySet()) {
/* 113 */       int i = ((Integer)this.rewards.get(s)).intValue();
/* 114 */       currentMin = (currentMax -= nextMax) - i;
/* 115 */       nextMax = i;
/* 116 */       if (currentMin >= rand || rand > currentMax)
/*     */         continue; 
/*     */       try {
/* 119 */         this.totals.put(s, Integer.valueOf(((Integer)this.totals.get(s)).intValue() + 1));
/* 120 */       } catch (NullPointerException e) {
/* 121 */         this.totals.put(s, Integer.valueOf(1));
/*     */       } 
/*     */       try {
/* 124 */         this.totals.put("NetheriteUsed", Integer.valueOf(((Integer)this.totals.get("NetheriteUsed")).intValue() + 1));
/* 125 */       } catch (NullPointerException e) {
/* 126 */         this.totals.put("NetheriteUsed", Integer.valueOf(1));
/*     */       } 
/* 128 */       return s;
/*     */     } 
/* 130 */     return "";
/*     */   }
/*     */   
/*     */   public void giveReward(Player p) {
/* 134 */     String reward = randomReward();
/* 135 */     String name = p.getName();
/*     */     
/* 137 */     @SuppressWarnings("unused")
              List<String> groups = getGroups(p);
/* 138 */     if (reward.equalsIgnoreCase("soap")) {
/* 139 */       ItemStack map = new ItemStack(Material.MAP);
/* 140 */       Item mapItem = p.getWorld().dropItem(p.getLocation(), map);
/* 141 */       mapItem.setTicksLived(5999);
/* 142 */       mapItem.setPickupDelay(999999);
/* 143 */       this.main.getServer().getPluginManager().callEvent((Event)new PlayerDropItemEvent(p, mapItem));
/* 144 */       mapItem.remove();
/* 145 */     } else if (this.commands.containsKey(reward)) {
/* 146 */       ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
/* 147 */       String command = "crazycrate forceopen Netherite " + name;
/* 148 */       Bukkit.dispatchCommand((CommandSender)console, command);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void loadRewardMaps() {
/* 153 */     this.items.clear();
/* 154 */     this.commands.clear();
/* 155 */     ItemStack mHoe = new ItemStack(Material.GOLDEN_HOE);
/* 156 */     ItemStack pHoe = new ItemStack(Material.GOLDEN_HOE);
/* 157 */     ItemStack tHoe = new ItemStack(Material.GOLDEN_HOE);
/* 158 */     ItemStack rHoe = new ItemStack(Material.GOLDEN_HOE);
/* 159 */     ItemStack sHoe = new ItemStack(Material.GOLDEN_HOE);
/* 160 */     ItemStack prestigeToken = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
/* 161 */     ItemMeta mM = mHoe.getItemMeta();
/* 162 */     ItemMeta pM = pHoe.getItemMeta();
/* 163 */     ItemMeta tM = pHoe.getItemMeta();
/* 164 */     ItemMeta rM = pHoe.getItemMeta();
/* 165 */     ItemMeta sM = pHoe.getItemMeta();
/* 166 */     ItemMeta prestigeM = prestigeToken.getItemMeta();
/* 167 */     mM.setDisplayName(ChatColor.DARK_AQUA + "[" + ChatColor.LIGHT_PURPLE + "Myra" + 
/* 168 */         ChatColor.DARK_AQUA + "]" + ChatColor.RESET + " UPGRADE");
/* 169 */     pM.setDisplayName(ChatColor.BLUE + "[" + ChatColor.DARK_RED + "Phoenix" + 
/* 170 */         ChatColor.BLUE + "]" + ChatColor.RESET + " UPGRADE");
/* 171 */     sM.setDisplayName(ChatColor.GRAY + "[" + ChatColor.GOLD + "Seagull" + ChatColor.GRAY + 
/* 172 */         "]" + ChatColor.RESET + " UPGRADE");
/* 173 */     rM.setDisplayName(ChatColor.LIGHT_PURPLE + "[" + ChatColor.WHITE + "Rabbit" + 
/* 174 */         ChatColor.LIGHT_PURPLE + "]" + ChatColor.RESET + " UPGRADE");
/* 175 */     tM.setDisplayName(ChatColor.GOLD + "[" + ChatColor.YELLOW + "Turtle" + ChatColor.GOLD + 
/* 176 */         "]" + ChatColor.RESET + " UPGRADE");
/* 177 */     prestigeM.setDisplayName(ChatColor.GREEN + "Prestige Token");
/* 178 */     ArrayList<String> Lore = new ArrayList<>();
/* 179 */     ArrayList<String> prestigeLore = new ArrayList<>();
/* 180 */     Lore.add(ChatColor.GOLD + "Right click on oISketcHIo in the pagoda to claim this.");
/* 181 */     prestigeLore.add(ChatColor.GOLD + "If you hold this when you prestige,");
/* 182 */     prestigeLore.add(ChatColor.GOLD + "you won't lose your cell.");
/* 183 */     mM.setLore(Lore);
/* 184 */     pM.setLore(Lore);
/* 185 */     tM.setLore(Lore);
/* 186 */     rM.setLore(Lore);
/* 187 */     sM.setLore(Lore);
/* 188 */     prestigeM.setLore(prestigeLore);
/* 189 */     mHoe.setItemMeta(mM);
/* 190 */     pHoe.setItemMeta(pM);
/* 191 */     tHoe.setItemMeta(tM);
/* 192 */     rHoe.setItemMeta(rM);
/* 193 */     sHoe.setItemMeta(sM);
/* 194 */     prestigeM.addEnchant(Enchantment.UNBREAKING, 1, true);
/* 195 */     prestigeToken.removeEnchantment(Enchantment.UNBREAKING);
/* 196 */     prestigeToken.setItemMeta(prestigeM);
/* 197 */     this.items.put("Cactus", new ItemStack(Material.CACTUS));
/* 198 */     this.items.put("Sea_Lantern", new ItemStack(Material.SEA_LANTERN));
/* 199 */     this.commands.put("Grass_Block", "give [user] GRASS_BLOCK 1");
/* 200 */     this.items.put("Banner", new ItemStack(Material.WHITE_BANNER));
/* 201 */     this.items.put("Jack_o_Lantern", new ItemStack(Material.JACK_O_LANTERN));
/* 202 */     this.items.put("Glass", new ItemStack(Material.GLASS));
/* 203 */     this.items.put("Sand", new ItemStack(Material.SAND));
/* 204 */     this.items.put("Carrots", new ItemStack(Material.CARROT, 16));
/* 205 */     this.items.put("Potatoes", new ItemStack(Material.POTATO, 16));
/* 206 */     this.items.put("Diamond_Ore", new ItemStack(Material.DIAMOND_ORE, 16));
/* 207 */     this.items.put("Diamond_Block", new ItemStack(Material.DIAMOND_BLOCK, 8));
/* 208 */     this.items.put("XP", new ItemStack(Material.EXPERIENCE_BOTTLE, 16));
/* 209 */     this.items.put("Emerald_Ore", new ItemStack(Material.EMERALD_ORE));
/* 210 */     this.items.put("Emeralds", new ItemStack(Material.EMERALD, 2));
/* 211 */     this.items.put("Myra_Hoe", mHoe);
/* 212 */     this.items.put("Phoenix_Hoe", pHoe);
/* 213 */     this.items.put("Turtle_Hoe", tHoe);
/* 214 */     this.items.put("Rabbit_Hoe", rHoe);
/* 215 */     this.items.put("Seagull_Hoe", sHoe);
/* 216 */     this.items.put("Prestige_Token", prestigeToken);
/* 217 */     this.items.put("Book", new ItemStack(Material.BOOK));
/* 218 */     this.items.put("Glowstone", new ItemStack(Material.GLOWSTONE_DUST));
/* 219 */     this.items.put("Vines", new ItemStack(Material.VINE));
/* 220 */     this.items.put("Netherite_Ingot", new ItemStack(Material.NETHERITE_INGOT));
/* 221 */     this.commands.put("Myra", "lp user [user] parent add myra");
/* 222 */     this.commands.put("Phoenix", "lp user [user] parent add phoenix");
/* 223 */     this.commands.put("Turtle", "lp user [user] parent add turtle");
/* 224 */     this.commands.put("Rabbit", "lp user [user] parent add rabbit");
/* 225 */     this.commands.put("Seagull", "lp user [user] parent add seagull");
/* 226 */     this.commands.put("10M", "eco give [user] 10000000");
/* 227 */     this.commands.put("10M", "broadcast &6 [user] just won &r&b10 Mil!");
/* 228 */     this.commands.put("1M", "eco give [user] 1000000");
/* 229 */     this.commands.put("100K", "eco give [user] 100000");
/* 230 */     this.commands.put("25K", "eco give [user] 25000");
/* 231 */     this.commands.put("10K", "eco give [user] 10000");
/* 232 */     this.commands.put("5K", "eco give [user] 5000");
/* 233 */     this.commands.put("XPick", "pick explosive [user]");
/* 234 */     this.commands.put("PickoPlenty", "pick pickoplenty [user]");
/* 235 */     this.commands.put("NightVision", "lp user [user] permission set permapotion.NIGHT_VISION");
/* 236 */     suffixes.put("Myra", "' &3[&dMyra&3]&f'");
/* 237 */     suffixes.put("Phoenix", "' &9[&4Phoenix&9]&f'");
/* 238 */     suffixes.put("Turtle", "' &6[&eTurtle&6]&f'");
/* 239 */     suffixes.put("Rabbit", "' &d[&fRabbit&d]&f'");
/* 240 */     suffixes.put("Seagull", "' &7[&6Seagull&7]&f'");
/*     */   }
/*     */   
/*     */   public Map<Integer, ItemStack> addItem(ItemStack stack, Player player) {
/* 244 */     HashMap<Integer, ItemStack> overflow = player.getInventory().addItem(new ItemStack[] { stack });
/* 245 */     if (!overflow.isEmpty()) {
/* 246 */       for (Map.Entry<Integer, ItemStack> is : overflow.entrySet()) {
/* 247 */         player.getWorld().dropItemNaturally(player.getLocation(), is.getValue());
/*     */       }
/*     */     }
/*     */     
/* 251 */     player.updateInventory();
/* 252 */     return overflow;
/*     */   }
/*     */   
/*     */   public List<String> getGroups(Player base) {
/* 256 */     LuckPerms handler = (LuckPerms) groupManager;
/* 257 */     if (handler == null) {
/* 258 */       return null;
/*     */     }
/* 260 */     return Arrays.asList(handler.getGroupManager().getGroup(base.getName()).toString());
/*     */   }
/*     */   
/*     */   public int getRandom(int min, int max) {
/* 264 */     return ThreadLocalRandom.current().nextInt(min, max + 1);
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void onPluginDisable(PluginDisableEvent event) {
/* 269 */     saveCustomYml();
/*     */   }
/*     */ }
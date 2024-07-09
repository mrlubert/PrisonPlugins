package com.mrlubert.prisoncontrol;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
//import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.GroupManager;

public class Gamble2Utils {
	private Main main;
	private GroupManager groupManager;
	public int totalPossible;
	public HashMap<String, Integer> totals;
	public HashMap<String, ItemStack> items;
	public HashMap<String, String> commands;
	public Set<String> prestigeTokened;
	public Map<String, Object> rewards;
	public Map<String, Object> tot;
	public Set<String> ranks = new HashSet<>(
			Arrays.asList(new String[] { "Myra", "Phoenix", "Turtle", "Rabbit", "Seagull" }));
	public Set<String> picks = new HashSet<>(Arrays.asList(new String[] { "XPick", "PickoPlenty" }));
	public static HashMap<String, String> suffixes;
	File customYml;
	File prestigeTokens;
	FileConfiguration customConfig;
	FileConfiguration prestigeTokensConfig;

	public Gamble2Utils(Main plugin) {
		this.main = plugin;
		this.totalPossible = 0;
		this.totals = new HashMap<>();
		this.items = new HashMap<>();
		this.commands = new HashMap<>();
		suffixes = new HashMap<>();
		this.prestigeTokened = new HashSet<>(Arrays.asList(new String[0]));
		this.customYml = new File(this.main.getDataFolder() + "/totals.yml");
		this.customConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.customYml);
		this.prestigeTokens = new File(this.main.getDataFolder() + "/prestigeTokens.yml");
		this.prestigeTokensConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.prestigeTokens);
		// loadConfiguration();
		loadRewardMaps();
		this.main.getLogger().info("[KappaGamble] Loaded successfully.");
		PluginManager pluginManager = plugin.getServer().getPluginManager();
		Plugin GMplugin = pluginManager.getPlugin("GroupManager");
		if (GMplugin != null && GMplugin.isEnabled()) {
			this.groupManager = (GroupManager) GMplugin;
		}
	}

	public void loadConfiguration() {
		FileConfiguration config = this.main.getConfig();
		this.rewards = config.getConfigurationSection("Gamble.rewards").getValues(false);
		// ConfigurationSection x = this.customConfig.getConfigurationSection("Totals");
		// this.tot = x.getValues(false);
		for (String key22 : this.rewards.keySet()) {
			this.totalPossible += ((Integer) this.rewards.get(key22)).intValue();
		}
		for (String key22 : this.tot.keySet()) {
			this.totals.put(key22, (Integer) this.tot.get(key22));
		}
		this.prestigeTokened.addAll(this.prestigeTokensConfig.getStringList("Tokened"));
	}

	public void saveCustomYml() {
		for (String s : this.totals.keySet()) {
			this.customConfig.set("Totals." + s, this.totals.get(s));
		}
		ArrayList<String> tokenedList = new ArrayList<>();
		tokenedList.addAll(this.prestigeTokened);
		this.prestigeTokensConfig.set("Tokened", tokenedList);
		try {
			this.customConfig.save(this.customYml);
			this.prestigeTokensConfig.save(this.prestigeTokens);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String randomReward() {
		int rand = getRandom(1, this.totalPossible);
		int currentMax = this.totalPossible;
		int currentMin = 0;
		int nextMax = 0;
		for (String s : this.rewards.keySet()) {
			int i = ((Integer) this.rewards.get(s)).intValue();
			currentMin = (currentMax -= nextMax) - i;
			nextMax = i;
			if (currentMin >= rand || rand > currentMax)
				continue;
			try {
				this.totals.put(s, Integer.valueOf(((Integer) this.totals.get(s)).intValue() + 1));
			} catch (NullPointerException e) {
				this.totals.put(s, Integer.valueOf(1));
			}
			try {
				this.totals.put("NetheriteUsed",
						Integer.valueOf(((Integer) this.totals.get("NetheriteUsed")).intValue() + 1));
			} catch (NullPointerException e) {
				this.totals.put("NetheriteUsed", Integer.valueOf(1));
			}
			return s;
		}
		return "";
	}

	public void giveReward(Player p) {
		String reward = randomReward();
		String name = p.getName();

		@SuppressWarnings("unused")
		List<String> groups = getGroups(p);
		if (reward.equalsIgnoreCase("soap")) {
			ItemStack map = new ItemStack(Material.MAP);
			Item mapItem = p.getWorld().dropItem(p.getLocation(), map);
			mapItem.setTicksLived(5999);
			mapItem.setPickupDelay(999999);
			this.main.getServer().getPluginManager().callEvent((Event) new PlayerDropItemEvent(p, mapItem));
			mapItem.remove();
		} else if (this.commands.containsKey(reward)) {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			String command = "crazycrate forceopen Netherite " + name;
			Bukkit.dispatchCommand((CommandSender) console, command);
		}
	}

	public void loadRewardMaps() {
		this.items.clear();
		this.commands.clear();
		ItemStack mHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack pHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack tHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack rHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack sHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack prestigeToken = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
		ItemMeta mM = mHoe.getItemMeta();
		ItemMeta pM = pHoe.getItemMeta();
		ItemMeta tM = pHoe.getItemMeta();
		ItemMeta rM = pHoe.getItemMeta();
		ItemMeta sM = pHoe.getItemMeta();
		ItemMeta prestigeM = prestigeToken.getItemMeta();
		mM.setDisplayName(ChatColor.DARK_AQUA + "[" + ChatColor.LIGHT_PURPLE + "Myra" + ChatColor.DARK_AQUA + "]"
				+ ChatColor.RESET + " UPGRADE");
		pM.setDisplayName(ChatColor.BLUE + "[" + ChatColor.DARK_RED + "Phoenix" + ChatColor.BLUE + "]" + ChatColor.RESET
				+ " UPGRADE");
		sM.setDisplayName(ChatColor.GRAY + "[" + ChatColor.GOLD + "Seagull" + ChatColor.GRAY + "]" + ChatColor.RESET
				+ " UPGRADE");
		rM.setDisplayName(ChatColor.LIGHT_PURPLE + "[" + ChatColor.WHITE + "Rabbit" + ChatColor.LIGHT_PURPLE + "]"
				+ ChatColor.RESET + " UPGRADE");
		tM.setDisplayName(ChatColor.GOLD + "[" + ChatColor.YELLOW + "Turtle" + ChatColor.GOLD + "]" + ChatColor.RESET
				+ " UPGRADE");
		prestigeM.setDisplayName(ChatColor.GREEN + "Prestige Token");
		ArrayList<String> Lore = new ArrayList<>();
		ArrayList<String> prestigeLore = new ArrayList<>();
		Lore.add(ChatColor.GOLD + "Right click on oISketcHIo in the pagoda to claim this.");
		prestigeLore.add(ChatColor.GOLD + "If you hold this when you prestige,");
		prestigeLore.add(ChatColor.GOLD + "you won't lose your cell.");
		mM.setLore(Lore);
		pM.setLore(Lore);
		tM.setLore(Lore);
		rM.setLore(Lore);
		sM.setLore(Lore);
		prestigeM.setLore(prestigeLore);
		mHoe.setItemMeta(mM);
		pHoe.setItemMeta(pM);
		tHoe.setItemMeta(tM);
		rHoe.setItemMeta(rM);
		sHoe.setItemMeta(sM);
		prestigeM.addEnchant(Enchantment.UNBREAKING, 1, true);
		prestigeToken.removeEnchantment(Enchantment.UNBREAKING);
		prestigeToken.setItemMeta(prestigeM);
		this.items.put("Cactus", new ItemStack(Material.CACTUS));
		this.items.put("Sea_Lantern", new ItemStack(Material.SEA_LANTERN));
		this.commands.put("Grass_Block", "give [user] GRASS_BLOCK 1");
		this.items.put("Banner", new ItemStack(Material.WHITE_BANNER));
		this.items.put("Jack_o_Lantern", new ItemStack(Material.JACK_O_LANTERN));
		this.items.put("Glass", new ItemStack(Material.GLASS));
		this.items.put("Sand", new ItemStack(Material.SAND));
		this.items.put("Carrots", new ItemStack(Material.CARROT, 16));
		this.items.put("Potatoes", new ItemStack(Material.POTATO, 16));
		this.items.put("Diamond_Ore", new ItemStack(Material.DIAMOND_ORE, 16));
		this.items.put("Diamond_Block", new ItemStack(Material.DIAMOND_BLOCK, 8));
		this.items.put("XP", new ItemStack(Material.EXPERIENCE_BOTTLE, 16));
		this.items.put("Emerald_Ore", new ItemStack(Material.EMERALD_ORE));
		this.items.put("Emeralds", new ItemStack(Material.EMERALD, 2));
		this.items.put("Myra_Hoe", mHoe);
		this.items.put("Phoenix_Hoe", pHoe);
		this.items.put("Turtle_Hoe", tHoe);
		this.items.put("Rabbit_Hoe", rHoe);
		this.items.put("Seagull_Hoe", sHoe);
		this.items.put("Prestige_Token", prestigeToken);
		this.items.put("Book", new ItemStack(Material.BOOK));
		this.items.put("Glowstone", new ItemStack(Material.GLOWSTONE_DUST));
		this.items.put("Vines", new ItemStack(Material.VINE));
		this.items.put("Netherite_Ingot", new ItemStack(Material.NETHERITE_INGOT));
		this.commands.put("Myra", "lp user [user] parent add myra");
		this.commands.put("Phoenix", "lp user [user] parent add phoenix");
		this.commands.put("Turtle", "lp user [user] parent add turtle");
		this.commands.put("Rabbit", "lp user [user] parent add rabbit");
		this.commands.put("Seagull", "lp user [user] parent add seagull");
		this.commands.put("10M", "eco give [user] 10000000");
		this.commands.put("10M", "broadcast &6 [user] just won &r&b10 Mil!");
		this.commands.put("1M", "eco give [user] 1000000");
		this.commands.put("100K", "eco give [user] 100000");
		this.commands.put("25K", "eco give [user] 25000");
		this.commands.put("10K", "eco give [user] 10000");
		this.commands.put("5K", "eco give [user] 5000");
		this.commands.put("XPick", "pick explosive [user]");
		this.commands.put("PickoPlenty", "pick pickoplenty [user]");
		this.commands.put("NightVision", "lp user [user] permission set permapotion.NIGHT_VISION");
		suffixes.put("Myra", "' &3[&dMyra&3]&f'");
		suffixes.put("Phoenix", "' &9[&4Phoenix&9]&f'");
		suffixes.put("Turtle", "' &6[&eTurtle&6]&f'");
		suffixes.put("Rabbit", "' &d[&fRabbit&d]&f'");
		suffixes.put("Seagull", "' &7[&6Seagull&7]&f'");
	}

	public Map<Integer, ItemStack> addItem(ItemStack stack, Player player) {
		HashMap<Integer, ItemStack> overflow = player.getInventory().addItem(new ItemStack[] { stack });
		if (!overflow.isEmpty()) {
			for (Map.Entry<Integer, ItemStack> is : overflow.entrySet()) {
				player.getWorld().dropItemNaturally(player.getLocation(), is.getValue());
			}
		}

		player.updateInventory();
		return overflow;
	}

	public List<String> getGroups(Player base) {
		LuckPerms handler = (LuckPerms) groupManager;
		if (handler == null) {
			return null;
		}
		return Arrays.asList(handler.getGroupManager().getGroup(base.getName()).toString());
	}

	public int getRandom(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPluginDisable(PluginDisableEvent event) {
		saveCustomYml();
	}
}

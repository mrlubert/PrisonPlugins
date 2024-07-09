package com.mrlubert.prisoncontrol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.GroupManager;

public class GambleUtils {
	private Main main;
	private LuckPerms groupManager;
	public HashMap<String, ItemStack> items;
	public HashMap<String, String> commands;
	public Set<String> ranks = new HashSet<>(
			Arrays.asList(new String[] { "Myra", "Phoenix", "Turtle", "Rabbit", "Seagull" }));
	public Set<String> prestigeTokened;

	public GambleUtils() {
		items = new HashMap<>();
		commands = new HashMap<>();
		loadRewardMaps();
		this.main.getLogger().info("[KappaGamble] Loaded successfully.");
		Bukkit.getLogger().info("[Kappa] Loaded successfully.");
		this.prestigeTokened = new HashSet<>(Arrays.asList(new String[0]));

	}

	public void loadRewardMaps() {
		items.clear();
		commands.clear();
		ItemStack mHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack pHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack tHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack rHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack sHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack prestigeToken = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
		ItemMeta mM = mHoe.getItemMeta();
		ItemMeta pM = pHoe.getItemMeta();
		ItemMeta tM = tHoe.getItemMeta();
		ItemMeta rM = rHoe.getItemMeta();
		ItemMeta sM = sHoe.getItemMeta();
		ItemMeta prestigeM = prestigeToken.getItemMeta();
		mM.setDisplayName(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Myra" + ChatColor.DARK_AQUA + "]"
				+ ChatColor.RESET + " UPGRADE");
		pM.setDisplayName(ChatColor.GOLD + "[" + ChatColor.DARK_RED + "Phoenix" + ChatColor.GOLD + "]" + ChatColor.RESET
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
		Lore.add(ChatColor.GOLD + "Right click on Sketch in the pagoda to claim this.");
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
		items.put("Myra_Hoe", mHoe);
		items.put("Phoenix_Hoe", pHoe);
		items.put("Turtle_Hoe", tHoe);
		items.put("Rabbit_Hoe", rHoe);
		items.put("Seagull_Hoe", sHoe);
		items.put("Prestige_Token", prestigeToken);
		commands.put("Myra", "lp user [user] parent add myra");
		commands.put("Phoenix", "lp user [user] parent add phoenix");
		commands.put("Turtle", "lp user [user] parent add turtle");
		commands.put("Rabbit", "lp user [user] parent add rabbit");
		commands.put("Seagull", "lp user [user] parent add seagull");
	}

	public List<String> getGroups(Player base) {
		GroupManager handler = groupManager.getGroupManager();
		if (handler == null) {
			return null;
		}
		return Arrays.asList(handler.getGroup(base.getName()).toString());
	}
}

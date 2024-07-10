package com.mrlubert.prisoncontrol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.platform.PlayerAdapter;
import java.util.stream.Collectors;

public class NPCRanks implements Listener {
	@SuppressWarnings("unused")
	private Gamble2Utils gamble2Utils;
	public HashMap<String, String> commands;
	public HashMap<String, ItemStack> items;

	public NPCRanks(Main plugin) {
		this.items = new HashMap<>();
		commands = new HashMap<>();
		loadRewardMaps();

	}

	public static String format(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	public void loadRewardMaps() {
		items.clear();
		ItemStack mHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack pHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack tHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack rHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack sHoe = new ItemStack(Material.GOLDEN_HOE);
		ItemStack reHoe = new ItemStack(Material.DIAMOND_HOE);
		ItemStack prestigeToken = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
		ItemMeta mM = mHoe.getItemMeta();
		ItemMeta pM = pHoe.getItemMeta();
		ItemMeta tM = tHoe.getItemMeta();
		ItemMeta rM = rHoe.getItemMeta();
		ItemMeta sM = sHoe.getItemMeta();
		ItemMeta reM = reHoe.getItemMeta();
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
		reM.setDisplayName(ChatColor.LIGHT_PURPLE + "[" + ChatColor.WHITE + "Reset" + ChatColor.LIGHT_PURPLE + "]"
				+ ChatColor.RESET + " UPGRADE");
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
		reM.setLore(Lore);
		prestigeM.setLore(prestigeLore);
		mHoe.setItemMeta(mM);
		pHoe.setItemMeta(pM);
		tHoe.setItemMeta(tM);
		rHoe.setItemMeta(rM);
		sHoe.setItemMeta(sM);
		reHoe.setItemMeta(reM);
		prestigeM.addEnchant(Enchantment.UNBREAKING, 1, true);
		prestigeToken.removeEnchantment(Enchantment.UNBREAKING);
		prestigeToken.setItemMeta(prestigeM);
		items.put("Myra_Hoe", mHoe);
		items.put("Phoenix_Hoe", pHoe);
		items.put("Turtle_Hoe", tHoe);
		items.put("Rabbit_Hoe", rHoe);
		items.put("Seagull_Hoe", sHoe);
		items.put("Reset_Hoe", reHoe);
		items.put("Prestige_Token", prestigeToken);
	}
	
	@EventHandler
	public void onRightClick(NPCRightClickEvent e) {
		Player p = e.getClicker();
		NPC npc = e.getNPC();
		
		LuckPerms luckPerms = LuckPermsProvider.get();
		PlayerAdapter<Player> playerAdapter = luckPerms.getPlayerAdapter(Player.class);
		User user = playerAdapter.getUser(p);
		Collection<Group> groups = user.getInheritedGroups(playerAdapter.getQueryOptions(p));
		String groupsString = groups.stream().map(Group::getName).collect(Collectors.joining(", "));
		
		if (npc.getName().equalsIgnoreCase("oisketchio")) {
			p.sendMessage("Debug: Current Groups: " + groupsString);
			if (p.getInventory().getItemInMainHand().equals(items.get("Seagull_Hoe"))) {
				if (groupsString.toLowerCase().contains("seagull") || groupsString.toLowerCase().contains("rabbit") || groupsString.toLowerCase().contains("turtle") || groupsString.toLowerCase().contains("phoenix") || groupsString.toLowerCase().contains("myra")) {
					p.sendMessage(ChatColor.RED + "You already have Seagull or higher.");

				}
				int slot = p.getInventory().first(items.get("Seagull_Hoe"));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(0);
				}
				p.updateInventory();
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent add Seagull");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Seagull rank.");

			}
			if (p.getInventory().getItemInMainHand().equals(items.get("Rabbit_Hoe"))) {
				if (!groupsString.toLowerCase().contains("seagull") || groupsString.toLowerCase().contains("rabbit") || groupsString.toLowerCase().contains("turtle") || groupsString.toLowerCase().contains("phoenix") || groupsString.toLowerCase().contains("myra")) {
					p.sendMessage(ChatColor.RED + "You already have Rabbit or higher.");

				}
				int slot = p.getInventory().first(items.get("Rabbit_Hoe"));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(0);
				}
				p.updateInventory();
				String s = commands.get("Rabbit").replace("[user]", p.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Seagull");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Rabbit rank.");

			}
			if (p.getInventory().getItemInMainHand().equals(items.get("Turtle_Hoe"))) {
				if (!groupsString.toLowerCase().contains("seagull") || !groupsString.toLowerCase().contains("rabbit") || groupsString.toLowerCase().contains("turtle") || groupsString.toLowerCase().contains("phoenix") || groupsString.toLowerCase().contains("myra")) {
					p.sendMessage(ChatColor.RED + "You already have Turtle or higher.");

				}
				int slot = p.getInventory().first(items.get("Turtle_Hoe"));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(0);
				}
				p.updateInventory();
				String s = commands.get("Turtle").replace("[user]", p.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Rabbit");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Seagull");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Turtle rank.");

			}
			if (p.getInventory().getItemInMainHand().equals(items.get("Phoenix_Hoe"))) {
				if (!groupsString.toLowerCase().contains("seagull") || !groupsString.toLowerCase().contains("rabbit") || !groupsString.toLowerCase().contains("turtle") || groupsString.toLowerCase().contains("phoenix") || groupsString.toLowerCase().contains("myra")) {
					p.sendMessage(ChatColor.RED + "You already have Phoenix or Higher.");

				}
				int slot = p.getInventory().first(items.get("Phoenix_Hoe"));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(0);
				}
				p.updateInventory();
				String s = commands.get("Phoenix").replace("[user]", p.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Turtle");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Rabbit");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Seagull");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Phoenix rank.");

			}
			if (p.getInventory().getItemInMainHand().equals(items.get("Myra_Hoe"))) {
				if (!groupsString.toLowerCase().contains("seagull") || !groupsString.toLowerCase().contains("rabbit") || !groupsString.toLowerCase().contains("turtle") || !groupsString.toLowerCase().contains("phoenix") || groupsString.toLowerCase().contains("myra")) {
					p.sendMessage(ChatColor.RED + "You already have Myra.");

				}
				int slot = p.getInventory().first(items.get("Myra_Hoe"));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(0);
				}
				p.updateInventory();
				String s = commands.get("Myra").replace("[user]", p.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Phoenix");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Turtle");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Rabbit");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Seagull");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Myra rank.");
				return;
			}
			if (p.getInventory().getItemInMainHand().equals(items.get("Reset_Hoe"))) {
				p.sendMessage(ChatColor.DARK_RED + "Removing The Following Groups: " + groupsString);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Myra");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Phoenix");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Turtle");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Rabbit");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Seagull");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Reset rank.");
				return;
			}
		}
	}
}
package com.mrlubert.prisoncontrol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

public class NPCRanks implements Listener {
	@SuppressWarnings("unused")
	private Gamble2Utils gamble2Utils;
	private Main main;
	private List<ItemStack> hoes;
	public HashMap<String, String> commands;
	public HashMap<String, ItemStack> items;

	public NPCRanks(Main plugin) {
		this.main = plugin;
		this.items = new HashMap<>();
		commands = new HashMap<>();
		loadRewardMaps();
		this.hoes = Arrays.asList(new ItemStack[] { this.items.get("Phoenix_Hoe"), this.items.get("Turtle_Hoe"),
				this.items.get("Rabbit_Hoe"), this.items.get("Seagull_Hoe"), this.items.get("Myra_Hoe") });

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

	public static boolean isPlayerInGroup(Player player, String group) {
		if (group == null) {
			return false;
		}
		player.sendMessage("Debug: " + group + " Checked Returns: " + player.hasPermission("group." + group));
		return player.hasPermission("group." + group);
	}

	@EventHandler
	public void onRightClick(NPCRightClickEvent e) {
		Player p = e.getClicker();
		NPC npc = e.getNPC();
		if (npc.getName().equalsIgnoreCase("oisketchio")) {
			if (p.getInventory().contains(this.hoes.get(3))) {
				if (isPlayerInGroup(p, "Seagull") || isPlayerInGroup(p, "Rabbit") || isPlayerInGroup(p, "Turtle")
						|| isPlayerInGroup(p, "Phoenix") || isPlayerInGroup(p, "Myra")) {
					p.sendMessage(ChatColor.RED + "You already have Seagull or higher.");
					return;
				}
				int slot = p.getInventory().first(this.hoes.get(3));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
				}
				p.updateInventory();
				this.main.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent add Seagull");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Seagull rank.");
				return;
			}
			if (p.getInventory().contains(this.hoes.get(2))) {
				if (isPlayerInGroup(p, "Rabbit") || isPlayerInGroup(p, "Turtle") || isPlayerInGroup(p, "Phoenix")
						|| isPlayerInGroup(p, "Myra")) {
					p.sendMessage(ChatColor.RED + "You already have Rabbit or higher.");
					return;
				}
				int slot = p.getInventory().first(this.hoes.get(2));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
				}
				p.updateInventory();
				String s = this.commands.get("Rabbit").replace("[user]", p.getName());
				this.main.getServer().dispatchCommand(this.main.getServer().getConsoleSender(), s);
				this.main.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Seagull");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Rabbit rank.");
				return;
			}
			if (p.getInventory().contains(this.hoes.get(1))) {
				if (isPlayerInGroup(p, "Turtle") || isPlayerInGroup(p, "Phoenix") || isPlayerInGroup(p, "Myra")) {
					p.sendMessage(ChatColor.RED + "You already have Turtle or higher.");
					return;
				}
				int slot = p.getInventory().first(this.hoes.get(1));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
				}
				p.updateInventory();
				String s = this.commands.get("Turtle").replace("[user]", p.getName());
				this.main.getServer().dispatchCommand(this.main.getServer().getConsoleSender(), s);
				this.main.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Rabbit");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Turtle rank.");
				return;
			}
			if (p.getInventory().contains(this.hoes.get(0))) {
				if (isPlayerInGroup(p, "Phoenix") || isPlayerInGroup(p, "Myra")) {
					p.sendMessage(ChatColor.RED + "You already have Phoenix or Higher.");
					return;
				}
				int slot = p.getInventory().first(this.hoes.get(0));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
				}
				p.updateInventory();
				String s = this.commands.get("Phoenix").replace("[user]", p.getName());
				this.main.getServer().dispatchCommand(this.main.getServer().getConsoleSender(), s);
				this.main.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Turtle");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Phoenix rank.");
				return;
			}
			if (p.getInventory().contains(this.hoes.get(4))) {
				if (isPlayerInGroup(p, "Myra")) {
					p.sendMessage(ChatColor.RED + "You already have Myra.");
					return;
				}
				int slot = p.getInventory().first(this.hoes.get(4));
				if (p.getInventory().getItem(slot).getAmount() == 1) {
					p.getInventory().setItem(slot, null);
				} else {
					p.getInventory().getItem(slot).setAmount(p.getInventory().getItem(slot).getAmount() - 1);
				}
				p.updateInventory();
				String s = this.commands.get("Myra").replace("[user]", p.getName());
				this.main.getServer().dispatchCommand(this.main.getServer().getConsoleSender(), s);
				this.main.getServer().dispatchCommand(Bukkit.getConsoleSender(),
						"lp user " + p.getName() + " parent remove Phoenix");
				p.sendMessage(ChatColor.GREEN + "You have claimed your Myra rank.");
				return;
			}
		}
	}
}
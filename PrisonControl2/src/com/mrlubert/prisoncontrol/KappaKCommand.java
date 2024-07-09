package com.mrlubert.prisoncontrol;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KappaKCommand implements CommandExecutor {
	private Main main;
	public HashMap<String, ItemStack> items;

	public KappaKCommand(Main plugin) {
		this.main = plugin;
		this.items = new HashMap<>();
		loadRewardMaps();
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
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
		if (!(sender instanceof Player)) {
			if (arguments.length != 2) {
				this.main.getLogger().info(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]");
				return false;
			}
			if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(arguments[0].toLowerCase()))) {
				String str;
				switch ((str = arguments[1].toLowerCase()).hashCode()) {
				case -1276224445:
					if (!str.equals("prestige")) {
						break;
					}
					Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Prestige_Token") });

					return false;
					
				case -12624445:
					if (!str.equals("reset")) {
						break;
					}
					Bukkit.getPlayer(arguments[0].toLowerCase()).setAbsorptionAmount(0);
					Bukkit.getPlayer(arguments[0].toLowerCase()).sendMessage("Reset Ranks Check");

					return false;
					
				case -938645478:
					if (!str.equals("rabbit"))
						break;
					Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Rabbit_Hoe") });
					return false;
				case -862422724:
					if (!str.equals("turtle"))
						break;
					Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Turtle_Hoe") });
					return false;
				case -595742321:
					if (!str.equals("phoenix"))
						break;
					Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Phoenix_Hoe") });
					return false;
				case 3367131:
					if (!str.equals("myra"))
						break;
					Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Myra_Hoe") });
					return false;
				case 1968018365:
					if (!str.equals("seagull"))
						break;
					Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Seagull_Hoe") });
					return false;
				}
				this.main.getLogger().info(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]");
			} else {
				this.main.getLogger().info(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]");
			}
			return false;
		}
		Player player = (Player) sender;
		if (player.isOp()) {
			if (arguments.length != 2) {
				player.sendMessage(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]");
				return false;
			}
			if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(arguments[0].toLowerCase()))) {
				String str;
				switch ((str = arguments[1].toLowerCase()).hashCode()) {
				case -1276224445:
					if (!str.equals("prestige")) {
						break;
					}
					Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Prestige_Token") });
					return false;
				case -938645478:
					if (!str.equals("rabbit"))
						break;
					Bukkit.getPlayer(arguments[0]).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Rabbit_Hoe") });
					return false;
				case -862422724:
					if (!str.equals("turtle"))
						break;
					Bukkit.getPlayer(arguments[0]).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Turtle_Hoe") });
					return false;
				case -595742321:
					if (!str.equals("phoenix"))
						break;
					Bukkit.getPlayer(arguments[0]).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Phoenix_Hoe") });
					return false;
				case 3367131:
					if (!str.equals("myra"))
						break;
					Bukkit.getPlayer(arguments[0].toLowerCase()).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Myra_Hoe") });
					return false;
				case 1968018365:
					if (!str.equals("seagull"))
						break;
					Bukkit.getPlayer(arguments[0]).getInventory()
							.addItem(new ItemStack[] { (ItemStack) this.items.get("Seagull_Hoe") });
					return false;
				}
				player.sendMessage(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]");
			} else {
				player.sendMessage(ChatColor.RED + "Invalid syntax. Use /KappaK [player] [rank]");
			}
		} else {
			player.sendMessage("You are not OP.");
		}
		return false;
	}
}

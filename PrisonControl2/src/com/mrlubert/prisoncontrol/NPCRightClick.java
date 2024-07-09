
package com.mrlubert.prisoncontrol;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCRightClick implements Listener {
	@SuppressWarnings("unused")
	private Gamble2Utils gamble2Utils;

	public NPCRightClick(Main plugin) {

	}

	public static String format(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	@EventHandler
	public void onRightClick(NPCRightClickEvent e) {
		Player p = e.getClicker();
		NPC npc = e.getNPC();

		if (npc.getName().equalsIgnoreCase("Netherite")) {
			if (p.isSneaking()) {
				String name = p.getName();
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				String command = "crazycrates preview Netherite " + name;
				Bukkit.dispatchCommand((CommandSender) console, command);
			}
			if (!p.isSneaking()) {
				if (!(p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_INGOT)) {
					p.sendMessage(format("&0[&5Gambling&0]&r &4You Need An Netherite Ingot In Your Hand To Use This!"));
				} else if (p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_INGOT) {
					if (p.getGameMode() == GameMode.CREATIVE) {
						String name = p.getName();
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String command = "crazycrates forceopen Netherite " + name;
						Bukkit.dispatchCommand((CommandSender) console, command);
					} else if (p.getGameMode() != GameMode.CREATIVE) {
						p.getInventory().getItemInMainHand()
								.setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
						p.updateInventory();
						String name = p.getName();
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String command = "crazycrates forceopen Netherite " + name;
						Bukkit.dispatchCommand((CommandSender) console, command);
					}
				}
				p.updateInventory();
			}
		} else if (npc.getName().equalsIgnoreCase("TestuRLuck")) {
			if (p.isSneaking()) {
				String name = p.getName();
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				String command = "crazycrates preview Emerald " + name;
				Bukkit.dispatchCommand((CommandSender) console, command);
			}
			if (!p.isSneaking()) {
				if (!(p.getInventory().getItemInMainHand().getType() == Material.EMERALD)) {
					p.sendMessage(format("&0[&5Gambling&0]&r &4You Need An Emerald In Your Hand To Use This!"));
				} else if (p.getInventory().getItemInMainHand().getType() == Material.EMERALD) {
					if (p.getGameMode() == GameMode.CREATIVE) {
						String name = p.getName();
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String command = "crazycrates forceopen Emerald " + name;
						Bukkit.dispatchCommand((CommandSender) console, command);
					} else if (p.getGameMode() != GameMode.CREATIVE) {
						p.getInventory().getItemInMainHand()
								.setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
						p.updateInventory();
						String name = p.getName();
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String command = "crazycrates forceopen Emerald " + name;
						Bukkit.dispatchCommand((CommandSender) console, command);
					}
				}
				p.updateInventory();
			}
		}
	}
}
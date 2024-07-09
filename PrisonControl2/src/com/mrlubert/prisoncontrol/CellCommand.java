package com.mrlubert.prisoncontrol;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import me.wiefferink.areashop.AreaShop;
import me.wiefferink.areashop.regions.BuyRegion;
import me.wiefferink.areashop.regions.RentRegion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CellCommand implements CommandExecutor {
	private AreaShop as = getAreaShop();

	private static AreaShop getAreaShop() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("AreaShop");
		if (plugin == null || !(plugin instanceof AreaShop)) {
			return null;
		}
		return (AreaShop) plugin;
	}

	public CellCommand(Main plugin) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
		if (!(sender instanceof Player)) {
			return false;
		}
		Player p = (Player) sender;
		HashSet<RentRegion> rentRegions = new HashSet<>();
		HashSet<BuyRegion> buyRegions = new HashSet<>();
		List<RentRegion> lr = this.as.getFileManager().getRents();
		List<BuyRegion> br = this.as.getFileManager().getBuys();

		if (arguments.length > 0) {
			for (BuyRegion region : br) {
				if (!region.isOwner((OfflinePlayer) p))
					continue;
				buyRegions.add(region);
			}
			Iterator<BuyRegion> iterator = buyRegions.iterator();
			if (iterator.hasNext()) {
				BuyRegion r = iterator.next();
				r.getTeleportFeature().teleportPlayer(p);
				return false;
			}
		} else {
			for (RentRegion region : lr) {
				if (!region.isOwner((OfflinePlayer) p))
					continue;
				rentRegions.add(region);
			}
			Iterator<RentRegion> iterator = rentRegions.iterator();
			if (iterator.hasNext()) {
				RentRegion r = iterator.next();

				@SuppressWarnings("unused")
				List<Location> sign = r.getSignsFeature().getSignLocations();
				r.getTeleportFeature().teleportPlayer(p);
				return false;
			}
		}
		p.sendMessage(ChatColor.RED + "You don't have a cell to go to.");
		return false;
	}
}
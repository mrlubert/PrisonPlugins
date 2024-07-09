package com.mrlubert.prisoncontrol;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	private CellCommand cellCmd;
	private KappaKCommand kappaKCmd;
	private NPCRightClick npcRClick;
	private NPCRanks npcRanks;
	private UserBalanceUpdate balanceUpdate;
	public GambleUtils gambleUtils;

	public void onEnable() {
		this.gambleUtils = new GambleUtils();
		this.cellCmd = new CellCommand(this);
		this.kappaKCmd = new KappaKCommand(this);
		this.npcRClick = new NPCRightClick(this);
		this.npcRanks = new NPCRanks(this);
		this.balanceUpdate = new UserBalanceUpdate(this);
		getServer().getPluginManager().registerEvents((Listener) this.npcRClick, (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) this.npcRanks, (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) this.balanceUpdate, (Plugin) this);
		getCommand("cell").setExecutor((CommandExecutor) this.cellCmd);
		getCommand("kappak").setExecutor((CommandExecutor) this.kappaKCmd);
	}

	public void onDisable() {
	}
}

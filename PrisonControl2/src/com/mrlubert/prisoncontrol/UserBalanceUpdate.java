package com.mrlubert.prisoncontrol;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import java.math.BigDecimal;
import net.ess3.api.events.UserBalanceUpdateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UserBalanceUpdate implements Listener {
	private Main main;
	private MaxCashUtils mcUtils;

	public UserBalanceUpdate(Main plugin) {
		this.main = plugin;
		this.mcUtils = new MaxCashUtils(this.main);
	}

	@EventHandler
	public void onBalanceUpdate(UserBalanceUpdateEvent e) throws NoLoanPermittedException, UserDoesNotExistException {
		Player player = e.getPlayer();
		BigDecimal newBal = e.getNewBalance();
		Object[] prestiges = this.mcUtils.prestiges.toArray();
		if (this.mcUtils.hasPermission(player, "ezprestige.prestige.20")) {
			return;
		}
		for (int n = 19; n > 0;) {
			if (!this.mcUtils.hasPermission(player, "ezprestige.prestige." + n)) {
				n--;
				continue;
			}
			if (newBal.compareTo((BigDecimal) prestiges[n]) == 1) {
				e.setNewBalance((BigDecimal) prestiges[n]);
			}
			return;
		}
		if (newBal.compareTo((BigDecimal) prestiges[0]) == 1) {
			e.setNewBalance((BigDecimal) prestiges[0]);
			return;
		}
	}
}

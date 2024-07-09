package com.mrlubert.prisoncontrol;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.bukkit.entity.Player;

public class MaxCashUtils {
	private Main main;
	BigDecimal p0;
	BigDecimal p1;
	BigDecimal p2;
	BigDecimal p3;
	BigDecimal p4;
	BigDecimal p5;
	BigDecimal p6;
	BigDecimal p7;
	BigDecimal p8;
	BigDecimal p9;
	BigDecimal p10;
	BigDecimal p11;
	BigDecimal p12;
	BigDecimal p13;
	BigDecimal p14;
	BigDecimal p15;
	BigDecimal p16;
	BigDecimal p17;
	BigDecimal p18;
	BigDecimal p19;
	public List<BigDecimal> prestiges;

	public MaxCashUtils(Main plugin) {
		this.main = plugin;
		config();
	}

	public void config() {
		this.p0 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.default"));
		this.p1 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p1"));
		this.p2 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p2"));
		this.p3 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p3"));
		this.p4 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p4"));
		this.p5 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p5"));
		this.p6 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p6"));
		this.p7 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p7"));
		this.p8 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p8"));
		this.p9 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p9"));
		this.p10 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p10"));
		this.p11 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p11"));
		this.p12 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p12"));
		this.p13 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p13"));
		this.p14 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p14"));
		this.p15 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p15"));
		this.p16 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p16"));
		this.p17 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p17"));
		this.p18 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p18"));
		this.p19 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p19"));
		this.main.getConfig().addDefault("MaxCash.default", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p1", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p2", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p3", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p4", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p5", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p6", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p7", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p8", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p9", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p10", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p11", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p12", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p13", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p14", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p15", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p16", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p17", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p18", BigDecimal.valueOf(100000000L));
		this.main.getConfig().addDefault("MaxCash.p18", BigDecimal.valueOf(100000000L));
		this.main.saveDefaultConfig();
		this.prestiges = Arrays.asList(new BigDecimal[] { this.p0, this.p1, this.p2, this.p3, this.p4, this.p5, this.p6,
				this.p7, this.p8, this.p9, this.p10, this.p11, this.p12, this.p13, this.p14, this.p15, this.p16,
				this.p17, this.p18, this.p19 });
	}

	public boolean hasPermission(Player base, String node) {
		return base.hasPermission(node);
	}
}

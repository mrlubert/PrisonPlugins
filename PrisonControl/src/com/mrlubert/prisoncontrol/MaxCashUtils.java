/*     */ package com.mrlubert.prisoncontrol;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ public class MaxCashUtils
/*     */ {
/*     */   private PrisonPicks main;
/*     */   BigDecimal p0;
/*     */   BigDecimal p1;
/*     */   BigDecimal p2;
/*     */   BigDecimal p3;
/*     */   BigDecimal p4;
/*     */   BigDecimal p5;
/*     */   BigDecimal p6;
/*     */   BigDecimal p7;
/*     */   BigDecimal p8;
/*     */   BigDecimal p9;
/*     */   BigDecimal p10;
/*     */   BigDecimal p11;
/*     */   BigDecimal p12;
/*     */   BigDecimal p13;
/*     */   BigDecimal p14;
/*     */   BigDecimal p15;
/*     */   BigDecimal p16;
/*     */   BigDecimal p17;
/*     */   BigDecimal p18;
/*     */   BigDecimal p19;
/*     */   public List<BigDecimal> prestiges;
/*     */   
/*     */   public MaxCashUtils(PrisonPicks plugin) {
/*  49 */     this.main = plugin;
/*  50 */     config();
/*     */   }
/*     */   
/*     */   public void config() {
/*  54 */     this.p0 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.default"));
/*  55 */     this.p1 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p1"));
/*  56 */     this.p2 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p2"));
/*  57 */     this.p3 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p3"));
/*  58 */     this.p4 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p4"));
/*  59 */     this.p5 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p5"));
/*  60 */     this.p6 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p6"));
/*  61 */     this.p7 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p7"));
/*  62 */     this.p8 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p8"));
/*  63 */     this.p9 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p9"));
/*  64 */     this.p10 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p10"));
/*  65 */     this.p11 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p11"));
/*  66 */     this.p12 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p12"));
/*  67 */     this.p13 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p13"));
/*  68 */     this.p14 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p14"));
/*  69 */     this.p15 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p15"));
/*  70 */     this.p16 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p16"));
/*  71 */     this.p17 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p17"));
/*  72 */     this.p18 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p18"));
/*  73 */     this.p19 = BigDecimal.valueOf(this.main.getConfig().getDouble("MaxCash.p19"));
/*  74 */     this.main.getConfig().addDefault("MaxCash.default", BigDecimal.valueOf(100000000L));
/*  75 */     this.main.getConfig().addDefault("MaxCash.p1", BigDecimal.valueOf(100000000L));
/*  76 */     this.main.getConfig().addDefault("MaxCash.p2", BigDecimal.valueOf(100000000L));
/*  77 */     this.main.getConfig().addDefault("MaxCash.p3", BigDecimal.valueOf(100000000L));
/*  78 */     this.main.getConfig().addDefault("MaxCash.p4", BigDecimal.valueOf(100000000L));
/*  79 */     this.main.getConfig().addDefault("MaxCash.p5", BigDecimal.valueOf(100000000L));
/*  80 */     this.main.getConfig().addDefault("MaxCash.p6", BigDecimal.valueOf(100000000L));
/*  81 */     this.main.getConfig().addDefault("MaxCash.p7", BigDecimal.valueOf(100000000L));
/*  82 */     this.main.getConfig().addDefault("MaxCash.p8", BigDecimal.valueOf(100000000L));
/*  83 */     this.main.getConfig().addDefault("MaxCash.p9", BigDecimal.valueOf(100000000L));
/*  84 */     this.main.getConfig().addDefault("MaxCash.p10", BigDecimal.valueOf(100000000L));
/*  85 */     this.main.getConfig().addDefault("MaxCash.p11", BigDecimal.valueOf(100000000L));
/*  86 */     this.main.getConfig().addDefault("MaxCash.p12", BigDecimal.valueOf(100000000L));
/*  87 */     this.main.getConfig().addDefault("MaxCash.p13", BigDecimal.valueOf(100000000L));
/*  88 */     this.main.getConfig().addDefault("MaxCash.p14", BigDecimal.valueOf(100000000L));
/*  89 */     this.main.getConfig().addDefault("MaxCash.p15", BigDecimal.valueOf(100000000L));
/*  90 */     this.main.getConfig().addDefault("MaxCash.p16", BigDecimal.valueOf(100000000L));
/*  91 */     this.main.getConfig().addDefault("MaxCash.p17", BigDecimal.valueOf(100000000L));
/*  92 */     this.main.getConfig().addDefault("MaxCash.p18", BigDecimal.valueOf(100000000L));
/*  93 */     this.main.getConfig().addDefault("MaxCash.p18", BigDecimal.valueOf(100000000L));
/*  94 */     this.main.saveDefaultConfig();
/*  95 */     this.prestiges = Arrays.asList(new BigDecimal[] { this.p0, this.p1, this.p2, this.p3, this.p4, this.p5, this.p6, this.p7, this.p8, 
/*  96 */           this.p9, this.p10, this.p11, this.p12, this.p13, this.p14, this.p15, this.p16, this.p17, this.p18, 
/*  97 */           this.p19 });
/*     */   }
/*     */   
/*     */   public boolean hasPermission(Player base, String node) {
/* 101 */     return base.hasPermission(node);
/*     */   }
/*     */ }
package com.enwash.deco.init;

import java.util.ArrayList;
import java.util.List;

import com.enwash.deco.blocks.Bust;
import com.enwash.deco.blocks.Candelabra;
import com.enwash.deco.blocks.CarpentryStation;
import com.enwash.deco.blocks.Chandelier;
import com.enwash.deco.blocks.CoffeeTable;
import com.enwash.deco.blocks.Fountain;
import com.enwash.deco.blocks.LogDeer;
import com.enwash.deco.blocks.LogTinted;
import com.enwash.deco.blocks.Mailbox;
import com.enwash.deco.blocks.TorchTall;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BTDCBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	
	public static final Block TORCH_TALL_WOOD = new TorchTall("torch_tall_wood", Material.WOOD, 1F, SoundType.WOOD);
	public static final Block CHANDELIER = new Chandelier("chandelier", Material.WOOD, 1F, SoundType.WOOD);
	public static final Block CANDELABRA  = new Candelabra("candelabra", Material.ROCK, 1F, SoundType.STONE);
	public static final Block BUST_NOTCH = new Bust("bust_notch", Material.ROCK);
	public static final Block BUST_ENWASH = new Bust("bust_enwash", Material.ROCK);
	public static final Block BUST_VILLAGER = new Bust("bust_villager", Material.ROCK);
	public static final Block BUST_CREEPER = new Bust("bust_creeper", Material.ROCK);
	public static final Block BUST_ZOMBIE = new Bust("bust_zombie", Material.ROCK);
	public static final Block BUST_GHAST = new Bust("bust_ghast", Material.ROCK);
<<<<<<< HEAD:src/main/java/com/enwash/deco/init/BTDCBlocks.java
	public static final Block LUCKYCAT_WHITE = new Bust("luckycat_white", Material.ROCK);
	public static final Block LUCKYCAT_BLACK = new Bust("luckycat_black", Material.ROCK);
	public static final Block LUCKYCAT_GOLD = new Bust("luckycat_gold", Material.IRON);
=======
	public static final Block BUST_SLIME = new Bust("bust_slime", Material.ROCK);
>>>>>>> 0ef262bfc2f5df4a026fb3c69418ce96f4ed3843:src/main/java/com/enwash/deco/init/ModBlocks.java
	public static final Block MAILBOX_WOOD = new Mailbox("mailbox_wood", Material.WOOD);
	public static final Block LOGDEER = new LogDeer("logdeer", Material.WOOD);
	public static final Block LOG_TINTED = new LogTinted("log_tinted", Material.WOOD);
	public static final Block COFFEE_TABLE_GLASS = new CoffeeTable("coffee_table_glass", Material.WOOD, SoundType.WOOD);
	public static final Block FOUNTAIN = new Fountain("fountain", Material.ROCK);
	//public static final Block CARPENTRY_STATION = new CarpentryStation("carpentry_station", Material.WOOD);
}
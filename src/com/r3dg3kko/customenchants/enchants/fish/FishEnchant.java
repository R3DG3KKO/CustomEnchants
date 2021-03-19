package com.r3dg3kko.customenchants.enchants.fish;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class FishEnchant extends Enchant {

	public FishEnchant() {
		super("fish", "Fish", 1, ChatColor.DARK_AQUA + "Infinite water breathing",
				ChatColor.GREEN + "Helmet", new ItemSet[]{ItemSet.HELMET});
	}

}

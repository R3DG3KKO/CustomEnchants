package com.r3dg3kko.customenchants.enchants.beast;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class BeastEnchant extends Enchant {

	public BeastEnchant() {
		super("beast", "Beast", 3, ChatColor.DARK_AQUA + "Infinite strength",
				ChatColor.GREEN + "Chestplate", new ItemSet[]{ItemSet.CHESTPLATE});
	}
}

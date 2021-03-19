package com.r3dg3kko.customenchants.enchants.blaze;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class BlazeEnchant extends Enchant {

	public BlazeEnchant() {
		super("blaze", "Blaze", 1, ChatColor.DARK_AQUA + "Infinite fire res",
				ChatColor.GREEN + "Leggings", new ItemSet[]{ItemSet.LEGGINGS});
	}
}

package com.r3dg3kko.customenchants.enchants.jellylegs.enchant;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class JellyLegsEnchant extends Enchant {

	public JellyLegsEnchant() {
		super("jellylegs", "JellyLegs", 1, ChatColor.DARK_AQUA + "No fall damage", ChatColor.GREEN + "Boots", new ItemSet[]{ItemSet.BOOTS});
	}
}

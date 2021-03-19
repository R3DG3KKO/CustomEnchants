package com.r3dg3kko.customenchants.enchants.telepathy.enchant;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class TelepathyEnchant extends Enchant {

	public TelepathyEnchant() {
		super("telepathy", "Telepathy", 1, ChatColor.DARK_AQUA + "Broken blocks go straight to inventory",
				ChatColor.GREEN + "Axe, Hoe, Pickaxe and Shovels", new ItemSet[]{ItemSet.ALL_TOOL});
	}
}

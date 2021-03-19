package com.r3dg3kko.customenchants.enchants.scoot;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class ScootEnchant extends Enchant {

	public ScootEnchant() {
		super("scoot", "Scoot", 3, ChatColor.DARK_AQUA + "Infinite speed", ChatColor.GREEN + "Boots", new ItemSet[]{ItemSet.BOOTS});
	}
}

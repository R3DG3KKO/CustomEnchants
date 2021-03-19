package com.r3dg3kko.customenchants.enchants.freeze.enchant;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class FreezeEnchant extends Enchant {

	public FreezeEnchant() {
		super("freeze", "Freeze", 4, ChatColor.DARK_AQUA + "Freeze player", ChatColor.GREEN + "Bow", new ItemSet[]{ItemSet.BOW});
	}
}

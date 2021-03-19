package com.r3dg3kko.customenchants.enchants.cubed.enchant;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class CubedEnchant extends Enchant {

	public CubedEnchant() {
		super("cubed", "Cubed", 5, ChatColor.DARK_AQUA + "Mine blocks in a cubed form",
				ChatColor.GREEN + "Pickaxe", new ItemSet[]{ItemSet.PICKAXE});
	}
}

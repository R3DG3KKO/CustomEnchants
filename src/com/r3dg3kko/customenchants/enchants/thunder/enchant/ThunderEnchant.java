package com.r3dg3kko.customenchants.enchants.thunder.enchant;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class ThunderEnchant extends Enchant {

	public ThunderEnchant() {
		super("thunder", "Thunder", 4, ChatColor.DARK_AQUA + "Hit someone with a sword to strike thunder on them",
				ChatColor.GREEN + "Sword", new ItemSet[]{ItemSet.SWORD});
	}
}

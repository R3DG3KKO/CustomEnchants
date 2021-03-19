package com.r3dg3kko.customenchants.enchants.lightning.enchant;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class LightningEnchant extends Enchant {

	public LightningEnchant() {
		super("lightning", "Lightning", 4, ChatColor.DARK_AQUA + "Hit someone with a bow to strike lightning on them",
				ChatColor.GREEN + "Bow", new ItemSet[]{ItemSet.BOW});
	}
}

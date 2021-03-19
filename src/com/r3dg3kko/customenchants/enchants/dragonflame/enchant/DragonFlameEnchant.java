package com.r3dg3kko.customenchants.enchants.dragonflame.enchant;

import org.bukkit.ChatColor;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class DragonFlameEnchant extends Enchant {

	public DragonFlameEnchant() {
		super("dragonflame", "DragonFlame", 4, ChatColor.DARK_AQUA + "Shoot Dragon FireBall", ChatColor.GREEN + "Blaze Rod", new ItemSet[]{ItemSet.BLAZEROD});
	}
}

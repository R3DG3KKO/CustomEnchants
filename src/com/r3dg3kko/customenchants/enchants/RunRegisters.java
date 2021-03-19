package com.r3dg3kko.customenchants.enchants;

import org.bukkit.enchantments.Enchantment;

import com.r3dg3kko.customenchants.CustomEnchants;
import com.r3dg3kko.customenchants.superclass.CheckEnchant;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class RunRegisters {

	public static void runRegisters() {

		CheckEnchant ce = new CheckEnchant();
		
		for (Enchant ench : ce.getEnchantsList()) {
			
			RunRegisters.registerEnchant(ench.getEnch());
		}
	}

	private static void registerEnchant(Enchantment ench) {

		CustomEnchants.register(ench);
	}
}

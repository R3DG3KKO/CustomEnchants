package com.r3dg3kko.customenchants;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchants {

	public static void register(Enchantment ench) {
		boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(ench);

		if (!registered) {
			registerEnchantment(ench);
		}
	}

	public static void registerEnchantment(Enchantment ench) {
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(ench);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

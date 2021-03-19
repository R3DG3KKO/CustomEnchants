package com.r3dg3kko.customenchants.enchants;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.r3dg3kko.customenchants.enchants.beast.BeastEnchant;
import com.r3dg3kko.customenchants.enchants.blaze.BlazeEnchant;
import com.r3dg3kko.customenchants.enchants.fish.FishEnchant;
import com.r3dg3kko.customenchants.enchants.scoot.ScootEnchant;

public class CheckEnchants implements Runnable {

	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			ScootEnchant scoot = new ScootEnchant();
			BeastEnchant beast = new BeastEnchant();
			BlazeEnchant blaze = new BlazeEnchant();
			FishEnchant fish = new FishEnchant();
			
			scoot.runPotion(p, p.getInventory().getBoots(), PotionEffectType.SPEED);
			beast.runPotion(p, p.getInventory().getChestplate(), PotionEffectType.INCREASE_DAMAGE);
			blaze.runPotion(p, p.getInventory().getLeggings(), PotionEffectType.FIRE_RESISTANCE);
			fish.runPotion(p, p.getInventory().getHelmet(), PotionEffectType.WATER_BREATHING);
		}
	}
}

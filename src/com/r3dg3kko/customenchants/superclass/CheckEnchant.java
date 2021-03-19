package com.r3dg3kko.customenchants.superclass;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.r3dg3kko.customenchants.enchants.beast.BeastEnchant;
import com.r3dg3kko.customenchants.enchants.blaze.BlazeEnchant;
import com.r3dg3kko.customenchants.enchants.cubed.enchant.CubedEnchant;
import com.r3dg3kko.customenchants.enchants.dragonflame.enchant.DragonFlameEnchant;
import com.r3dg3kko.customenchants.enchants.fish.FishEnchant;
import com.r3dg3kko.customenchants.enchants.freeze.enchant.FreezeEnchant;
import com.r3dg3kko.customenchants.enchants.jellylegs.enchant.JellyLegsEnchant;
import com.r3dg3kko.customenchants.enchants.lightning.enchant.LightningEnchant;
import com.r3dg3kko.customenchants.enchants.scoot.ScootEnchant;
import com.r3dg3kko.customenchants.enchants.telepathy.enchant.TelepathyEnchant;
import com.r3dg3kko.customenchants.enchants.thunder.enchant.ThunderEnchant;

public class CheckEnchant {
	
	public CheckEnchant() {
	}
	
	public Enchant checkLore(ItemStack item) {
		
		Enchant ench = null;
		List<String> lore = item.getItemMeta().getLore();
		
		for (Enchant ench1 : this.getEnchantsList()) {
			
			if (lore.contains(ench1.getLoreMsg())) {
				ench = ench1;
			}
		}
		
		return ench;
	}
	
	public List<Enchant> getEnchantsList() {
		
		List<Enchant> enchantsList = new ArrayList<>();
		
		Enchant telepathy = new TelepathyEnchant();
		Enchant cubed = new CubedEnchant();
		Enchant thunder = new ThunderEnchant();
		Enchant lightning = new LightningEnchant();
		Enchant scoot = new ScootEnchant();
		Enchant beast = new BeastEnchant();
		Enchant blaze = new BlazeEnchant();
		Enchant fish = new FishEnchant();
		Enchant jellylegs = new JellyLegsEnchant();
		Enchant freeze = new FreezeEnchant();
		Enchant dragonflame = new DragonFlameEnchant();
		
		enchantsList.add(telepathy);
		enchantsList.add(cubed);
		enchantsList.add(thunder);
		enchantsList.add(lightning);
		enchantsList.add(scoot);
		enchantsList.add(beast);
		enchantsList.add(blaze);
		enchantsList.add(fish);
		enchantsList.add(jellylegs);
		enchantsList.add(freeze);
		enchantsList.add(dragonflame);
		
		return enchantsList;
	}
	
	public ChatColor getColor(int lvl) {
		
		ChatColor color = ChatColor.GRAY;
		
		if (lvl == 1) {
			color = ChatColor.AQUA;
		} else if (lvl == 2) {
			color = ChatColor.YELLOW;
		} else if (lvl == 3) {
			color = ChatColor.GREEN;
		} else if (lvl == 4) {
			color = ChatColor.LIGHT_PURPLE;
		} else if (lvl == 5) {
			color = ChatColor.DARK_RED;
		} else {
			return color;
		}
		
		return color;
	}
}

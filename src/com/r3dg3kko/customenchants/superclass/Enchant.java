package com.r3dg3kko.customenchants.superclass;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.r3dg3kko.customenchants.EnchantmentWrapper;
import com.r3dg3kko.customenchants.enums.ItemSet;

public class Enchant {

	private String namespace;
	private String name;
	private int maxLvl;
	private String loreMsg;
	private String compatability;
	private ItemSet[] itemSet;
	private Enchantment ench;

	public Enchant(String namespace, String name, int maxLvl, String loreMsg, String compatability, ItemSet[] itemSet) {

		this.setNamespace(namespace);
		this.setName(name);
		this.setMaxLvl(maxLvl);
		this.setLoreMsg(loreMsg);
		this.setCompatability(compatability);
		this.setItemSet(itemSet);

		Enchantment ench = new EnchantmentWrapper(namespace, name, maxLvl);

		this.setEnch(ench);
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxLvl() {
		return maxLvl;
	}

	public void setMaxLvl(int maxLvl) {
		this.maxLvl = maxLvl;
	}

	public String getLoreMsg() {
		return loreMsg;
	}

	public void setLoreMsg(String loreMsg) {
		this.loreMsg = loreMsg;
	}

	public String getCompatability() {
		return compatability;
	}

	public void setCompatability(String compatability) {
		this.compatability = compatability;
	}

	public ItemSet[] getItemSet() {
		return itemSet;
	}

	public void setItemSet(ItemSet[] itemSet) {
		this.itemSet = itemSet;
	}

	public Enchantment getEnch() {
		return ench;
	}

	public void setEnch(Enchantment ench) {
		this.ench = ench;
	}

	public boolean hasEnchant(ItemStack item) {

		boolean hasEnch = false;

		try {
			if (item.getItemMeta().hasEnchant(ench)) {
				hasEnch = true;
			}
		} catch (Exception e) {
		}

		return hasEnch;
	}

	public int getLvl(ItemStack item) {
		return item.getItemMeta().getEnchantLevel(ench);
	}

	public void runPotion(Player p, ItemStack item, PotionEffectType type) {
		if (hasEnchant(item)) {
			if (!p.hasPotionEffect(type)) {
				int lvl = getLvl(item) - 1;
				PotionEffect potion = new PotionEffect(type, 60, lvl);
				p.addPotionEffect(potion);
			}
		}
	}
}

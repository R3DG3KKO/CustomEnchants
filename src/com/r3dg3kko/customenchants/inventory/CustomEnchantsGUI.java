package com.r3dg3kko.customenchants.inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.r3dg3kko.customenchants.superclass.CheckEnchant;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class CustomEnchantsGUI {

	private Inventory inv;

	public CustomEnchantsGUI() {

		createInv();
	}

	private void createInv() {

		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "CustomEnchantments");

		this.inv = inv;

		initializeItems();
	}

	private void initializeItems() {

		CheckEnchant ce = new CheckEnchant();
		int i = 0;
		
		for (Enchant ench : ce.getEnchantsList()) {
			
			ChatColor color = ce.getColor(ench.getMaxLvl());
			
			inv.setItem(i, createGuiItem(Material.ENCHANTED_BOOK, color + ench.getName() + " " + ench.getMaxLvl(), "", ench.getLoreMsg(), ench.getCompatability()));
			i++;
		}

		inv.setItem(26, createGuiItem(Material.PAPER, ChatColor.DARK_AQUA + "Command:", "", ChatColor.DARK_GREEN
				+ "/ce <enchantName> <target-name> (leave target name empty for self) <enchantLevel>"));
	}

	protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
		final ItemStack item = new ItemStack(material, 1);
		final ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		// Set the name of the item
		meta.setDisplayName(name);

		// Set the lore of the item
		meta.setLore(Arrays.asList(lore));

		item.setItemMeta(meta);

		return item;
	}

	public void openInventory(Player p) {
		p.openInventory(inv);
	}
}

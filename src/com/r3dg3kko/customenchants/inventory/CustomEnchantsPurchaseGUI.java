package com.r3dg3kko.customenchants.inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

public class CustomEnchantsPurchaseGUI {

	private Inventory inv;

	public CustomEnchantsPurchaseGUI() {

		createInv();
	}

	private void createInv() {

		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "CustomEnchantPurchase");

		this.inv = inv;

		initializeItems();
	}

	private void initializeItems() {

		int enchantLevel = 1;
		CheckEnchant ce = new CheckEnchant();

		for (int i = 11; i < 16; i++) {
			int costLevel = enchantLevel * 10;
			ChatColor color = ce.getColor(enchantLevel);
			inv.setItem(i, createGuiItem(Material.ENCHANTED_BOOK, color + "Level " + enchantLevel + " Enchant", "",
					ChatColor.DARK_GREEN + "" + costLevel + " levels"));
			enchantLevel++;
		}

		inv.setItem(26, createGuiItem(Material.PAPER, ChatColor.LIGHT_PURPLE + "See all enchants", "",
				ChatColor.YELLOW + "click here"));
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

	public Map<Integer, Enchant> getEnchants(int lvl, Player p) {
		CheckEnchant ce = new CheckEnchant();
		Map<Integer, Enchant> enchIsLvl = new HashMap<Integer, Enchant>();

		for (Enchant ench : ce.getEnchantsList()) {
			if (ench.getMaxLvl() > lvl) {
				enchIsLvl.put(lvl, ench);
				p.sendMessage("test");
			}
		}

		return enchIsLvl;
	}

	public Enchant getSlot(Map<Integer, Enchant> map) {

		int number = map.size();

		Random rand = new Random();

		int random = rand.nextInt(number + 1);

		Enchant ench = map.get(random);

		return ench;
	}
}

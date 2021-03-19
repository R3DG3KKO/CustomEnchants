package com.r3dg3kko.customenchants.merge;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.r3dg3kko.customenchants.superclass.CheckEnchant;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class BookNameChangingEvent implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {

		CheckEnchant ce = new CheckEnchant();
		
		for (Enchant ench : ce.getEnchantsList()) {
			
			checkEverything(e, ench.getLoreMsg());
		}
	}

	private void checkEverything(InventoryClickEvent e, String lore) {
		Inventory inv = e.getInventory();

		if (inv instanceof AnvilInventory) {
			InventoryView view = e.getView();
			int rawSlot = e.getRawSlot();

			if (rawSlot == view.convertSlot(rawSlot)) {

				if (rawSlot == 2) {
					ItemStack item = e.getCurrentItem();

					if (item != null) {
						ItemMeta meta = item.getItemMeta();
						List<String> lore1 = meta.getLore();

						if ((item.getType().equals(Material.ENCHANTED_BOOK)) && (lore1.contains(lore))) {
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}
}
package com.r3dg3kko.customenchants.merge;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.r3dg3kko.customenchants.enums.ItemSet;
import com.r3dg3kko.customenchants.superclass.CheckEnchant;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class DragAndDropEvent implements Listener {

	@EventHandler
	public void dragAndDropEvent(InventoryClickEvent e) {

		if (e.getCursor() == null) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getCursor().getType() != Material.ENCHANTED_BOOK) {
			return;
		}
		Enchant ench = null;
		CheckEnchant ce = new CheckEnchant();

		try {
			ench = ce.checkLore(e.getCursor());
		} catch (Exception e1) {
			return;
		}

		first(e, ench);
	}

	public void first(InventoryClickEvent e, Enchant ench) {
		if (e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
			ItemStack item = e.getCursor();
			ItemStack item2 = e.getCurrentItem();
			ItemMeta meta = item.getItemMeta();
			if (meta.getLore().contains(ench.getLoreMsg())) {
				int lvl = ench.getLvl(item);

				boolean compatible = false;

				for (ItemSet i : ench.getItemSet()) {
					for (Material m : i.getItems()) {
						if (item2.getType().equals(m)) {
							compatible = true;
						}
					}
				}

				bookConfirmed(e, item, ench.getCompatability(), ench.getEnch(), lvl, ench.getName(), ench.getMaxLvl(),
						compatible);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void bookConfirmed(InventoryClickEvent e, ItemStack item, String COMPATIBILITY, Enchantment ench, int lvl,
			String loreMsg, int maxlvl, boolean compatible) {

		if (e.getCurrentItem().getType().equals(Material.AIR)) {
			return;
		}

		Player p = (Player) e.getWhoClicked();
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (!compatible) {
			p.sendMessage(ChatColor.RED + "This enchant only works on " + COMPATIBILITY);
			return;
		}
		ItemStack item2 = new ItemStack(e.getCurrentItem());
		boolean enchant = true;

		if (item2.containsEnchantment(ench)) {
			int item2lvl = item2.getItemMeta().getEnchantLevel(ench);
			if (item2lvl > lvl) {
				ItemStack cursor = e.getCursor();
				ItemStack currentItem = e.getCurrentItem();
				e.setCursor(null);
				e.setCurrentItem(null);
				p.getInventory().addItem(cursor);
				p.getInventory().addItem(currentItem);
				p.updateInventory();
				p.closeInventory();
				p.sendMessage(ChatColor.RED + "You already have a greater level enchant on your item!");
				enchant = false;
			}
			if (item2lvl == lvl) {
				if (lvl == maxlvl) {
					ItemStack cursor = e.getCursor();
					ItemStack currentItem = e.getCurrentItem();
					e.setCursor(null);
					e.setCurrentItem(null);
					p.getInventory().addItem(cursor);
					p.getInventory().addItem(currentItem);
					p.updateInventory();
					p.closeInventory();
					p.sendMessage(ChatColor.RED + "The two enchants combined will be higher then the maximum level of "
							+ maxlvl);
					return;
				} else {
					item2.getItemMeta().removeEnchant(ench);
					lvl++;
				}
			}
			if (item2lvl < lvl) {
				item2.getItemMeta().removeEnchant(ench);
			}
		}

		if (enchant) {
			CheckEnchant ce = new CheckEnchant();
			ChatColor color = ce.getColor(lvl);
			e.setCursor(null);
			ItemStack item3 = item2;
			ItemMeta item3Meta = item3.getItemMeta();
			List<String> lore;
			boolean setLore = false;
			if (item3Meta.hasLore()) {
				lore = item3Meta.getLore();
				for (int i = 0; i < lore.size(); i++) {
					if (lore.get(i).contains(loreMsg)) {
						color = ce.getColor(lvl);
						lore.set(i, color + loreMsg + " " + lvl);
						setLore = true;
						break;
					}
				}
			} else {
				lore = new ArrayList<String>();
			}
			if (!setLore) {
				lore.add(color + loreMsg + " " + lvl);
			}
			item3Meta.setLore(lore);
			item3Meta.addEnchant(ench, lvl, true);
			item3.setItemMeta(item3Meta);
			e.setCurrentItem(item3);
			p.updateInventory();
			p.sendMessage(ChatColor.GREEN + "Applied " + color + loreMsg + " " + lvl);
		}
	}
}

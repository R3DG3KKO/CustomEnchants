package com.r3dg3kko.customenchants.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import com.r3dg3kko.customenchants.CustomBook;
import com.r3dg3kko.customenchants.superclass.CheckEnchant;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class CustomEnchantsInventoryClickEvent implements Listener {

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (e.getView().getTitle().contains("CustomEnchantments")) {
			if (!e.getCurrentItem().equals(null) || !e.getCurrentItem().getType().equals(Material.AIR)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onInvDrag(InventoryDragEvent e) {
		if (e.getView().getTitle().contains("CustomEnchantments")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPurchase(InventoryClickEvent e) {
		if (e.getView().getTitle().contains("CustomEnchantPurchase")) {
			if (!e.getCurrentItem().equals(null) || !e.getCurrentItem().getType().equals(Material.AIR)) {

				e.setCancelled(true);
				CustomEnchantsGUI GUIShop = new CustomEnchantsGUI();
				CheckEnchant ce = new CheckEnchant();
   
				int slot = e.getSlot();

				if (!(e.getWhoClicked() instanceof Player)) {
					return;
				}

				Player p = (Player) e.getWhoClicked();

				if (slot == 26) {
					GUIShop.openInventory(p);
					return;
				}

				int enchant = slot - 10;
				
				int lvl = enchant * 10;
				
				int expNeeded = getExpNeeded(lvl);
				
				if (SetExpFix.getTotalExperience(p) < expNeeded) {
					p.sendMessage(ChatColor.DARK_RED + "You do not have the required level: " + lvl);
					p.closeInventory();
					return;
				}
				
				SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) - expNeeded);
				
				List<Enchant> enchants = new ArrayList<Enchant>();
				
				for (Enchant ench : ce.getEnchantsList()) {
					if (ench.getMaxLvl() >= enchant) {
						enchants.add(ench);
					}
				}

				Random rand = new Random();
				
				int random = rand.nextInt(enchants.size());
				
				Enchant ench = enchants.get(random);

				CommandSender sender = (CommandSender) p;
				CustomBook cb = new CustomBook();
				p.updateInventory();
				p.closeInventory();
				cb.giveBook(ench, p, enchant, sender);
			}
		}
	}
	
	public int getExpNeeded(int lvl) {
		
		int exp = SetExpFix.getExpToLevel(lvl);
		
		return exp;
	}
}

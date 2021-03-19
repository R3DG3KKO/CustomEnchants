package com.r3dg3kko.customenchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.r3dg3kko.customenchants.superclass.CheckEnchant;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class CustomBook {

	public void giveBook(Enchant ench, Player target, int lvl, CommandSender sender) {
		
		if (!checkInventory(target)) {
			sender.sendMessage(ChatColor.RED + "The target's inventory is full!");
			return;
		}

		if (ench.getMaxLvl() < lvl) {
			sender.sendMessage(ChatColor.RED + "The enchant level is too high. Max level: " + ench.getMaxLvl());
			return;
		}

		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		item.addUnsafeEnchantment(ench.getEnch(), lvl);

		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();

		CheckEnchant ce = new CheckEnchant();
		
		ChatColor color = ce.getColor(lvl);
		
		meta.setDisplayName(color + ench.getName() + " " + lvl);
		lore.add("");
		lore.add(ench.getLoreMsg());

		meta.setLore(lore);
		item.setItemMeta(meta);

		target.getInventory().addItem(item);

		target.sendMessage(ChatColor.AQUA + "recieved " + ChatColor.GREEN + ench.getName() + " " + lvl);
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.AQUA + "sent " + ench.getName() + " " + lvl + ChatColor.AQUA + " to "
					+ ChatColor.GREEN + target.getDisplayName());
		} else {
			Player p = (Player) sender;
			if (!p.getUniqueId().equals(target.getUniqueId())) {
				sender.sendMessage(ChatColor.AQUA + "sent " + ench.getName() + " " + lvl + ChatColor.AQUA + " to "
						+ ChatColor.GREEN + target.getDisplayName());
			}
		}
	}

	public boolean checkInventory(Player target) {

		return target.getInventory().firstEmpty() != -1;
	}
}

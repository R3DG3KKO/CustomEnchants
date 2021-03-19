package com.r3dg3kko.customenchants.enchants.dragonflame.events;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.r3dg3kko.customenchants.enchants.dragonflame.enchant.DragonFlameEnchant;

public class DragonFlameShootEvent implements Listener {

	Map<UUID, Long> cooldown = new HashMap<>();
	
	@EventHandler
	public void onShoot(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		DragonFlameEnchant dfe = new DragonFlameEnchant();
		
		if (!p.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD))
			return;
		
		ItemStack item = p.getInventory().getItemInMainHand();
		
		if (!item.getItemMeta().hasEnchant(dfe.getEnch()))
			return;
		
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			int lvl = item.getItemMeta().getEnchantLevel(dfe.getEnch());
			
			if (cooldown.get(p.getUniqueId()) != null) {
				if ((cooldown.get(p.getUniqueId()) + getCooldown(lvl)) > (System.currentTimeMillis() / 1000)) {
					p.sendMessage(ChatColor.RED + "You are on cooldown for " + ((cooldown.get(p.getUniqueId()) + getCooldown(lvl)) - (System.currentTimeMillis() / 1000)) + " seconds!" );
					return;
				} else {
					cooldown.remove(p.getUniqueId());
				}
			}
			
			e.setCancelled(true);
			
			p.launchProjectile(DragonFireball.class);
			cooldown.put(p.getUniqueId(), (System.currentTimeMillis() / 1000));
		}
	}
	
	public long getCooldown(int lvl) {
		
		long cooldown;
		
		if (lvl == 1) {
			cooldown = 60;
		} else if (lvl == 2) {
			cooldown = 45;
		} else if (lvl == 3) {
			cooldown = 30;
		} else {
			cooldown = 15;
		}
		
		return cooldown;
	}
}

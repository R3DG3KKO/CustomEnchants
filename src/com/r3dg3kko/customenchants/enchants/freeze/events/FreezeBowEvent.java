package com.r3dg3kko.customenchants.enchants.freeze.events;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import com.r3dg3kko.customenchants.enchants.freeze.enchant.FreezeEnchant;

public class FreezeBowEvent implements Listener {

	Map<UUID, Long> cooldown = new HashMap<>();
	Map<UUID, String> playerNames = new HashMap<>();
	
	@EventHandler
	public void onBowShot(EntityDamageByEntityEvent e) {

		if (e.getDamager().getType().equals(EntityType.ARROW)) {
			if (e.getEntity().getType().equals(EntityType.PLAYER)) {
				Arrow arrow = (Arrow) e.getDamager();

				if (!(arrow.getShooter() instanceof Player))
					return;

				Player p = (Player) arrow.getShooter();
				Player target = (Player) e.getEntity();

				ItemStack item = p.getInventory().getItemInMainHand();
				
				FreezeEnchant freeze = new FreezeEnchant();

				if (freeze.hasEnchant(item)) {
					int lvl = freeze.getLvl(item);
					int newLvl = checkLevel(lvl);
					Random rand = new Random();
					boolean val = rand.nextInt(newLvl) == 0;

					if (cooldown.get(target.getUniqueId()) != null) {
						return;
					}
					
					if (val) {
						cooldown.put(target.getUniqueId(), (System.currentTimeMillis() / 1000));
						playerNames.put(target.getUniqueId(), p.getName());
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		
		if (cooldown.get(e.getPlayer().getUniqueId()) == null) {
			return;
		}
		
		Player p = e.getPlayer();
		
		if ((cooldown.get(p.getUniqueId()) + 5) > (System.currentTimeMillis() / 1000)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You were frozen by " + playerNames.get(p.getUniqueId()) + "!");
			return;
		} else {
			cooldown.remove(p.getUniqueId());
			playerNames.remove(p.getUniqueId());
			return;
		}
	}
	
	private int checkLevel(int lvl) {
		int newlvl;
		if (lvl == 1) {
			newlvl = 4;
		} else if (lvl == 2) {
			newlvl = 3;
		} else if (lvl == 3) {
			newlvl = 2;
		} else {
			newlvl = 1;
		}
		return newlvl;
	}
}

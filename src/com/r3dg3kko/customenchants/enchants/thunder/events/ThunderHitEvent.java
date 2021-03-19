package com.r3dg3kko.customenchants.enchants.thunder.events;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.r3dg3kko.customenchants.enchants.thunder.enchant.ThunderEnchant;

public class ThunderHitEvent implements Listener {

	@EventHandler
	public void onThunderHit(EntityDamageByEntityEvent e) {
		if (e.getDamager().getType().equals(EntityType.PLAYER)) {
			//if (e.getEntity().getType().equals(EntityType.PLAYER)) {
				Player p = (Player) e.getDamager();
				Entity target = e.getEntity();
				ItemStack item = p.getInventory().getItemInMainHand();

				if (new ThunderEnchant().hasEnchant(item)) {
					int lvl = new ThunderEnchant().getLvl(item);
					int newLvl = checkLevel(lvl);
					Random rand = new Random();
					boolean val = rand.nextInt(newLvl) == 0;

					if (val) {
						World world = target.getWorld();
						world.strikeLightning(target.getLocation());
					}
				}
			}
		}
	//}

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

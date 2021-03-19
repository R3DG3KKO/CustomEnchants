package com.r3dg3kko.customenchants.enchants.lightning.events;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.r3dg3kko.customenchants.enchants.lightning.enchant.LightningEnchant;

public class LightningHitEvent implements Listener {

	@EventHandler
	public void onLightningHit(EntityDamageByEntityEvent e) {

		if (e.getDamager().getType().equals(EntityType.ARROW)) {
			//if (e.getEntity().getType().equals(EntityType.PLAYER)) {
				Arrow arrow = (Arrow) e.getDamager();
				Entity target = (Entity) e.getEntity();
				
				if (!(arrow.getShooter() instanceof Player))
					return;
				
				Player p = (Player) arrow.getShooter();
				
				if ((target instanceof Player)) {
					Player targetPlayer = (Player) target;
					if (p.equals(targetPlayer)) {
						return;
					}
				}
				
				ItemStack item = p.getInventory().getItemInMainHand();

				LightningEnchant lightning = new LightningEnchant();

				if (lightning.hasEnchant(item)) {
					int lvl = lightning.getLvl(item);
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

package com.r3dg3kko.customenchants.enchants.jellylegs.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.r3dg3kko.customenchants.enchants.jellylegs.enchant.JellyLegsEnchant;

public class JellyLegsEvent implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		
		if (!e.getEntity().getType().equals(EntityType.PLAYER))
			return;
		
		Player p = (Player) e.getEntity();
		JellyLegsEnchant jl = new JellyLegsEnchant();
		
		if (!p.getInventory().getBoots().getItemMeta().hasEnchant(jl.getEnch()))
			return;
		
		if (!e.getCause().equals(DamageCause.FALL))
			return;
		
		if (!p.getLastDamageCause().getCause().equals(DamageCause.FALL))
			return;
		
		e.setCancelled(true);
	}
}

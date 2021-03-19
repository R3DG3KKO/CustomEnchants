package com.r3dg3kko.customenchants.enchants.telepathy.events;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.r3dg3kko.customenchants.enchants.cubed.enchant.CubedEnchant;
import com.r3dg3kko.customenchants.enchants.telepathy.enchant.TelepathyEnchant;

public class TelepathyBlockBreakEvent implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {

		Player p = event.getPlayer();

		if (p.getInventory().getItemInMainHand() == null)
			return;
		if (!p.getInventory().getItemInMainHand().hasItemMeta())
			return;
		
			TelepathyEnchant te = new TelepathyEnchant();
		if (!p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(te.getEnch()))
			return;
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR)
			return;
		if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(new CubedEnchant().getEnch()))
			return;
		if (p.getInventory().firstEmpty() == -1)
			return;
		if (event.getBlock().getState() instanceof Container)
			return;

		event.setDropItems(false);
		Block block = event.getBlock();

		Collection<ItemStack> drops = block.getDrops(p.getInventory().getItemInMainHand());

		if (drops.isEmpty())
			return;

		p.getInventory().addItem(drops.iterator().next());
	}
}

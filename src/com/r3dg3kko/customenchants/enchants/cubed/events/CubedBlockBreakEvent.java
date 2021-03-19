package com.r3dg3kko.customenchants.enchants.cubed.events;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.r3dg3kko.customenchants.enchants.cubed.enchant.CubedEnchant;
import com.r3dg3kko.customenchants.enchants.telepathy.enchant.TelepathyEnchant;

public class CubedBlockBreakEvent implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {

		Player p = event.getPlayer();

		if (p.getInventory().getItemInMainHand() == null)
			return;
		if (!p.getInventory().getItemInMainHand().hasItemMeta())
			return;
		if (!p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(new CubedEnchant().getEnch()))
			return;
		if (p.getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
			return;
		if (event.getBlock().getState() instanceof Container)
			return;

		event.setDropItems(false);
		int lvl = p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(new CubedEnchant().getEnch());
		Block block = event.getBlock();

		for (int i = 1; i < lvl; i++) {
			for (int x = block.getX() - i; x <= block.getX() + i; x++) {
				for (int y = block.getY() - i; y <= block.getY() + i; y++) {
					for (int z = block.getZ() - i; z <= block.getZ() + i; z++) {
						Location loc = new Location(block.getWorld(), x, y, z);
						World world = loc.getWorld();
						if (loc.getBlock() != null) {
							boolean isEmpty = false;
							Collection<ItemStack> drops = loc.getBlock().getDrops(p.getInventory().getItemInMainHand());

							if (drops.isEmpty()) {
								isEmpty = true;
							}

							if (!isEmpty) {
								loc.getBlock().setType(Material.AIR);
								TelepathyEnchant te = new TelepathyEnchant();
								if (!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta()
										.hasEnchant(te.getEnch()))) {
									world.dropItemNaturally(loc, drops.iterator().next());
								} else {
									if (p.getInventory().firstEmpty() == -1) {
										world.dropItemNaturally(loc, drops.iterator().next());
									} else {
										p.getInventory().addItem(drops.iterator().next());
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

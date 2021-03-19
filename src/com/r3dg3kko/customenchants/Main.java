package com.r3dg3kko.customenchants;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.r3dg3kko.customenchants.enchants.CheckEnchants;
import com.r3dg3kko.customenchants.enchants.RunRegisters;
import com.r3dg3kko.customenchants.enchants.cubed.events.CubedBlockBreakEvent;
import com.r3dg3kko.customenchants.enchants.dragonflame.events.DragonFlameShootEvent;
import com.r3dg3kko.customenchants.enchants.freeze.events.FreezeBowEvent;
import com.r3dg3kko.customenchants.enchants.jellylegs.events.JellyLegsEvent;
import com.r3dg3kko.customenchants.enchants.lightning.events.LightningHitEvent;
import com.r3dg3kko.customenchants.enchants.telepathy.events.TelepathyBlockBreakEvent;
import com.r3dg3kko.customenchants.enchants.thunder.events.ThunderHitEvent;
import com.r3dg3kko.customenchants.inventory.CustomEnchantsInventoryClickEvent;
import com.r3dg3kko.customenchants.inventory.CustomEnchantsPurchaseGUI;
import com.r3dg3kko.customenchants.merge.BookNameChangingEvent;
import com.r3dg3kko.customenchants.merge.DragAndDropEvent;
import com.r3dg3kko.customenchants.superclass.CheckEnchant;
import com.r3dg3kko.customenchants.superclass.Enchant;

public class Main extends JavaPlugin {

	private CustomBook cb = new CustomBook();
	private CustomEnchantsPurchaseGUI ceg = new CustomEnchantsPurchaseGUI();
	
	@Override
	public void onEnable() {
		RunRegisters.runRegisters();

		this.getServer().getPluginManager().registerEvents(new TelepathyBlockBreakEvent(), this);
		this.getServer().getPluginManager().registerEvents(new CubedBlockBreakEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ThunderHitEvent(), this);
		this.getServer().getPluginManager().registerEvents(new LightningHitEvent(), this);
		this.getServer().getPluginManager().registerEvents(new JellyLegsEvent(), this);
		this.getServer().getPluginManager().registerEvents(new FreezeBowEvent(), this);
		this.getServer().getPluginManager().registerEvents(new DragonFlameShootEvent(), this);

		this.getServer().getPluginManager().registerEvents(new DragAndDropEvent(), this);
		this.getServer().getPluginManager().registerEvents(new BookNameChangingEvent(), this);
		this.getServer().getPluginManager().registerEvents(new CustomEnchantsInventoryClickEvent(), this);

		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new CheckEnchants(), 0, 20);
	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("ce")) {
			if (args.length == 0) {
				if (!(sender instanceof Player)) {
					sender.sendMessage("You are not a player! Please use the args /ce <enchant-name> <target> <level>");
					return true;
				}

				Player p = (Player) sender;

				ceg.openInventory(p);

			} else if (args.length == 2) {
				String enchantName = args[0];
				
				if (!(sender.hasPermission("ce.giveself"))) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command");
					return true;
				}

				if (!(sender instanceof Player)) {
					sender.sendMessage("You are not a player! Please use the args /ce <enchant-name> <target> <level>");
					return true;
				}

				Player p = (Player) sender;

				int lvl;
				try {
					lvl = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					sender.sendMessage(ChatColor.RED + "Not a valid number!");
					return true;
				}
				if (lvl < 1) {
					sender.sendMessage(ChatColor.RED + "Too low of a number!");
					return true;
				}
				
				runCommands(enchantName, p, lvl, sender);
				
			} else if (args.length == 3) {
				String enchantName = args[0];

				if (!(sender.hasPermission("ce.givetarget"))) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command");
					return true;
				}
				
				Player target = Bukkit.getPlayerExact(args[1].toString());
				if (target == null) {
					sender.sendMessage(ChatColor.RED + "Player does not exist!");
					return true;
				}

				int lvl;
				try {
					lvl = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					sender.sendMessage(ChatColor.RED + "Not a valid number!");
					return true;
				}
				if (lvl < 1) {
					sender.sendMessage(ChatColor.RED + "Too low of a number!");
					return true;
				}

				runCommands(enchantName, target, lvl, sender);
				
			} else {
				sender.sendMessage(ChatColor.RED + "use /ce");
			}
		}
		return true;
	}
	
	public void runCommands(String enchantName, Player target, int lvl, CommandSender sender) {
		
		CheckEnchant ce = new CheckEnchant();
		boolean done = false;
		
		for (Enchant ench : ce.getEnchantsList()) {
			
			if (enchantName.equalsIgnoreCase(ench.getNamespace())) {
				cb.giveBook(ench, target, lvl, sender);
				done = true;
			}
		}
		
		if (!done) {
			sender.sendMessage(ChatColor.RED + "Not a valid enchantment!");
			return;
		}
	}
}

package uk.rythefirst.smp2.spawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.rythefirst.smp2.spawn.Main;

public class Pvptoggle implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("Player Only Command");
			return true;
		}

		Player player = (Player) sender;

		if (args.length == 0) {
			String msg = ChatColor.DARK_RED + "Disabled";
			if (Main.pvpManager.pvpEnabled(player)) {
				msg = ChatColor.GREEN + "Enabled";
			}
			player.sendMessage(ChatColor.GOLD + "You currently have pvp " + msg);
		} else if (args.length == 1) {
			if (!(args[0].equalsIgnoreCase("toggle"))) {
				player.sendMessage(ChatColor.DARK_RED + "Incorrect Usage, please use /pvp toggle");
			} else {
				Main.pvpManager.togglePvp(player);
			}
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Incorrect Usage, please use /pvp toggle");
		}

		return false;
	}

}

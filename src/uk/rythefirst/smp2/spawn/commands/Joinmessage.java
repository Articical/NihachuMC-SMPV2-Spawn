package uk.rythefirst.smp2.spawn.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.rythefirst.smp2.spawn.Main;

public class Joinmessage implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;

		if (!(player.hasPermission("v2.jmessage"))) {
			return true;
		}

		if (args.length <= 1) {
			player.sendMessage(ChatColor.DARK_RED + "Incorrect Usage! Avaliable commands:");
			player.sendMessage(ChatColor.GOLD + "/" + label + " set <message>");
			player.sendMessage(ChatColor.GOLD + "/" + label + " get <player>");
			player.sendMessage(ChatColor.GOLD + "/" + label + " reset");
			if (player.hasPermission("v2.mod")) {
				player.sendMessage("");
				player.sendMessage("" + ChatColor.GOLD + ChatColor.UNDERLINE + "--- Mod Commands ---");
				player.sendMessage("");
				player.sendMessage(ChatColor.GOLD + "/" + label + " clear <player>");
			}
			return true;
		}

		if (args[0].equals("clear")) {
			if (!(player.hasPermission("v2.mod"))) {
				return true;
			}
			if (!(args.length == 2)) {
				player.sendMessage(ChatColor.DARK_RED + "Invalid usage! Use: /jmessage clear <player>");
				return true;
			}
			Player targ = Bukkit.getPlayer(args[1]);
			if (targ == null | !(Bukkit.getOnlinePlayers().contains(targ))) {
				player.sendMessage(ChatColor.DARK_RED + "Invalid player!");
				return true;
			}
			Main.joinMsgManager.clearCMessage(targ.getUniqueId());
			player.sendMessage(ChatColor.GOLD + args[1] + "'s join message is reset!");
		}

		if (args[0].equals("reset")) {
			Main.joinMsgManager.clearCMessage(player.getUniqueId());
			player.sendMessage(ChatColor.GOLD + "Your Join message was reset!");
		}

		if (args[0].equals("set")) {
			StringBuilder builder = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				builder.append(args[i]);
				if (!(i == args.length - 1)) {
					builder.append(" ");
				}
			}

			Main.joinMsgManager.setCMessage(player.getUniqueId(), builder.toString());

			player.sendMessage(ChatColor.DARK_GREEN + "Join Message Set!");
		}

		if (args[0].equals("get")) {

			if (!(args.length == 2)) {
				player.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /jmessage get <player>");
			}

			Player targ = Bukkit.getPlayer(args[1]);

			String msg = Main.joinMsgManager.getCMessage(targ.getUniqueId());

			player.sendMessage(ChatColor.DARK_RED + args[1] + "'s join message is: " + ChatColor.RESET + msg);
		}

		return true;
	}

}

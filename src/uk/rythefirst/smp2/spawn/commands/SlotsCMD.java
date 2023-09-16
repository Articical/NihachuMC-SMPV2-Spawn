package uk.rythefirst.smp2.spawn.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.rythefirst.smp2.spawn.Main;

public class SlotsCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<String> playersLst = Main.SlotCache.slotLst;
		StringBuilder sb = new StringBuilder();
		Integer count = 0;
		for (String str : playersLst) {
			count++;
			Player p = Bukkit.getPlayer(UUID.fromString(str));
			sb.append(p.getDisplayName());
			if (!(count == 0) && !(count == playersLst.size())) {
				sb.append(ChatColor.RESET + ", ");
			}
		}
		sender.sendMessage(ChatColor.GOLD + "Players using reserved slots:");
		sender.sendMessage(sb.toString());
		sender.sendMessage(ChatColor.GOLD + "Total Reserved Slots Used: " + count);
		return true;
	}

}

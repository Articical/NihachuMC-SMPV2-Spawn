package uk.rythefirst.smp2.spawn.tabcomplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class LeaveMessageTab implements TabCompleter {

	public static final String[] arg0 = { "set", "get", "reset" };

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		final List<String> completions = new ArrayList<String>();
		List<String> commands = new ArrayList<>();

		if (args.length == 1) {
			for (int i = 0; i < arg0.length; i++) {
				commands.add(arg0[i]);
			}
			StringUtil.copyPartialMatches(args[0], commands, completions);
		}

		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("get")) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					commands.add(player.getName());
				}
				StringUtil.copyPartialMatches(args[1], commands, completions);
			}

		}

		return completions;
	}
}

package uk.rythefirst.smp2.spawn.tabcomplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class PvpTab implements TabCompleter {

	public static final String[] arg0 = { "toggle" };

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

		return completions;
	}

}

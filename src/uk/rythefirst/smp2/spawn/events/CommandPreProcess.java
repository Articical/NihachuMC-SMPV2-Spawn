package uk.rythefirst.smp2.spawn.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreProcess implements Listener {

	@EventHandler
	public void onPreCommad(PlayerCommandPreprocessEvent e) {
		if (e.getPlayer().hasPermission("v2.admin")) {
			return;
		}
		String[] cmd = e.getMessage().split(" ");
		if (cmd[0].equalsIgnoreCase("/plugins") || cmd[0].equalsIgnoreCase("/pl") || cmd[0].equalsIgnoreCase("/me")
				|| cmd[0].equalsIgnoreCase("/ver") || cmd[0].equalsIgnoreCase("/version")) {
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "Unknown Command.");
			e.setCancelled(true);
		}
	}

}

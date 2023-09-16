package uk.rythefirst.smp2.spawn.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import uk.rythefirst.smp2.spawn.Main;

public class Pvp {

	public Boolean pvpEnabled(Player player) {
		if (Main.pvpcache.pvpMap.containsKey(player.getUniqueId().toString())) {
			return Main.pvpcache.pvpMap.get(player.getUniqueId().toString());
		} else {
			Main.pvpcache.pvpMap.put(player.getUniqueId().toString(), false);
			return false;
		}
	}

	public void registerPlayer(Player player) {
		if (!(Main.pvpcache.pvpMap.containsKey(player.getUniqueId().toString()))) {
			Main.pvpcache.pvpMap.put(player.getUniqueId().toString(), false);
		}
	}

	public Boolean togglePvp(Player player) {
		if (Main.pvpcache.pvpMap.containsKey(player.getUniqueId().toString())) {
			if (Main.pvpcache.pvpMap.get(player.getUniqueId().toString()) == true) {
				Main.pvpcache.pvpMap.remove(player.getUniqueId().toString());
				Main.pvpcache.pvpMap.put(player.getUniqueId().toString(), false);
			} else {
				Main.pvpcache.pvpMap.remove(player.getUniqueId().toString());
				Main.pvpcache.pvpMap.put(player.getUniqueId().toString(), true);
			}
		} else {
			Main.pvpcache.pvpMap.put(player.getUniqueId().toString(), false);
		}

		player.sendMessage(
				ChatColor.GOLD + "Pvp now set to " + Main.pvpcache.pvpMap.get(player.getUniqueId().toString()));

		return Main.pvpcache.pvpMap.get(player.getUniqueId().toString());
	}

}

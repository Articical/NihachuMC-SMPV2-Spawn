package uk.rythefirst.smp2.spawn.managers;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import uk.rythefirst.smp2.spawn.Main;

public class CJoinMessage {

	public Boolean hasCMessage(UUID uuid) {
		return Main.jmCache.jmc.containsKey(uuid.toString());
	}

	public String getCMessage(UUID uuid) {
		if (!(Main.jmCache.jmc.containsKey(uuid.toString()))) {
			return ChatColor.GOLD + Bukkit.getPlayer(uuid).getName() + " is here!";
		} else {
			return ChatColor.translateAlternateColorCodes('&', Main.jmCache.jmc.get(uuid.toString()));
		}
	}

	public void setCMessage(UUID uuid, String msg) {
		Main.jmCache.jmc.put(uuid.toString(), msg);
	}

	public void clearCMessage(UUID uuid) {
		if (Main.jmCache.jmc.containsKey(uuid.toString())) {
			Main.jmCache.jmc.remove(uuid.toString());
		}
	}

}

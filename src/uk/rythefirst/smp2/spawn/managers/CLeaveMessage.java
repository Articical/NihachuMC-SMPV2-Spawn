package uk.rythefirst.smp2.spawn.managers;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import uk.rythefirst.smp2.spawn.Main;

public class CLeaveMessage {

	public Boolean hasLMessage(UUID uuid) {
		return Main.lmCache.lmc.containsKey(uuid.toString());
	}

	public String getLMessage(UUID uuid) {

		if (!(Main.lmCache.lmc.containsKey(uuid.toString()))) {
			return ChatColor.GOLD + Bukkit.getPlayer(uuid).getName() + " left :(";
		}

		if (Main.lmCache.lmc.get(uuid.toString()) == null) {
			return ChatColor.GOLD + Bukkit.getPlayer(uuid).getName() + " left :(";
		}
		return ChatColor.translateAlternateColorCodes('&', Main.lmCache.lmc.get(uuid.toString()));
	}

	public void setLMessage(UUID uuid, String msg) {
		Main.lmCache.lmc.put(uuid.toString(), msg);
	}

	public void clearLMessage(UUID uuid) {
		if (Main.lmCache.lmc.containsKey(uuid.toString())) {
			Main.lmCache.lmc.remove(uuid.toString());
		}
	}

}

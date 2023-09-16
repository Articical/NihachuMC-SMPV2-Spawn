package uk.rythefirst.smp2.spawn.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import uk.rythefirst.smp2.spawn.Main;

public class PlayerLeave implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Main.SlotManager.removePlayer(e.getPlayer());
		if (Main.leaveMsgManager.hasLMessage(e.getPlayer().getUniqueId())) {
			e.setQuitMessage(Main.leaveMsgManager.getLMessage(e.getPlayer().getUniqueId()));
		} else {
			e.setQuitMessage(ChatColor.DARK_RED + e.getPlayer().getName() + " left :(");
		}

	}

}

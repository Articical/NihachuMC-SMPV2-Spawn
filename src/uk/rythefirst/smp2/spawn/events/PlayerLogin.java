package uk.rythefirst.smp2.spawn.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import uk.rythefirst.smp2.spawn.Main;

public class PlayerLogin implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if (!(e.getPlayer().hasPermission("v2.resslot"))) {
			if (!(Main.SlotManager.publicSlotLeft())) {
				e.setResult(Result.KICK_FULL);
				e.setKickMessage(ChatColor.DARK_RED + "Unfortunately, only reserved slots are left!");
			}
		} else {
			if (Main.SlotManager.ResSlotLeft()) {
				Main.SlotManager.useSlot(e.getPlayer());
				e.setResult(Result.ALLOWED);
			} else if (!(Main.SlotManager.publicSlotLeft())) {
				e.setResult(Result.KICK_FULL);
				e.setKickMessage(ChatColor.DARK_RED + "Unfortunately, there are no slots left!");
			}
		}
	}

}

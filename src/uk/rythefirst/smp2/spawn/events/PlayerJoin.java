package uk.rythefirst.smp2.spawn.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import uk.rythefirst.smp2.spawn.Main;

public class PlayerJoin implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {

		if (!Main.nickNameManager.hasNickName(e.getPlayer())) {
			Main.nickNameManager.clearNickName(e.getPlayer());
		} else {
			Main.nickNameManager.setVisibleName(e.getPlayer());
		}

		if (!(e.getPlayer().hasPlayedBefore())) {
			e.setJoinMessage("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Welcome " + e.getPlayer().getName()
					+ " to NihachuMC!");
			return;
		}

		if (e.getPlayer().getWorld().getName().equals("duels-world")) {
			e.getPlayer().teleport(Main.configManager.getSpawnLocation());
		}

		Main.pvpManager.registerPlayer(e.getPlayer());
		// e.setJoinMessage(Main.joinMsgManager.getCMessage(e.getPlayer().getUniqueId()));
	}

}

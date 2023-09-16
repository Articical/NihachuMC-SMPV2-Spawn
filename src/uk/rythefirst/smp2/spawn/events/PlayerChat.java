package uk.rythefirst.smp2.spawn.events;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import uk.rythefirst.smp2.spawn.Main;
import uk.rythefirst.smp2.spawn.speedtyper.ChatWord;
import uk.rythefirst.smp2.spawn.util.Messages;

public class PlayerChat implements Listener {

	@EventHandler
	public void onMessage(AsyncPlayerChatEvent e) {
		if (Main.getCwg().isOnGoing()) {
			ChatWord word = Main.getCwg().getWord();

			if (e.getMessage().equalsIgnoreCase(word.getWord())) {
				word.setGuessed(true);
				Main.getCwg().setOnGoing(false);
				new BukkitRunnable() {
					@Override
					public void run() {
						for (Player p : Bukkit.getOnlinePlayers()) {
							p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1f, 1f);
							Messages.sendCenteredMessage(p, "" + ChatColor.GOLD + ChatColor.UNDERLINE + "Speed Typer");
							Messages.sendCenteredMessage(p, ChatColor.GOLD + "Game over!");
							Messages.sendCenteredMessage(p,
									ChatColor.GOLD + e.getPlayer().getDisplayName() + " typed the word first!");
							if (p == e.getPlayer()) {
								Messages.sendCenteredMessage(p, ChatColor.GOLD + "You won $1000");
								Main.getEconomy().depositPlayer(e.getPlayer(), 1000d);
							} else {
								Messages.sendCenteredMessage(p, "");
							}
						}
					}
				}.runTaskLater(Main.instance, 2);

				Bukkit.getLogger().log(Level.INFO, "[NihaESS] " + e.getPlayer().getName() + " Guessed the word!");
			}

		}
	}

}

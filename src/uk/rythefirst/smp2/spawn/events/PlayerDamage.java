package uk.rythefirst.smp2.spawn.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import uk.rythefirst.smp2.spawn.Main;

public class PlayerDamage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {

		if (!(e.getEntity().getType() == EntityType.PLAYER)) {
			return;
		}

		Player player = (Player) e.getEntity();

		if (player.getWorld().getName().equalsIgnoreCase("duels-world")) {
			return;
		}

		if (e.getDamager() instanceof Projectile) {
			Projectile weapon = (Projectile) e.getDamager();
			if (weapon.getShooter() instanceof Player) {
				Player Damager = (Player) weapon.getShooter();

				if (!(Main.pvpManager.pvpEnabled(player))) {
					Damager.sendMessage(ChatColor.DARK_RED + player.getName() + " Has pvp disabled!");
					e.setCancelled(true);
					return;
				}

				if (!(Main.pvpManager.pvpEnabled(Damager))) {
					Damager.sendMessage(ChatColor.DARK_RED + "You have pvp disabled!");
					e.setCancelled(true);
					return;
				}
			}
		}

		if (!(e.getDamager().getType() == EntityType.PLAYER)) {
			return;
		}

		Player Damager = (Player) e.getDamager();

		if (!(Main.pvpManager.pvpEnabled(player))) {
			Damager.sendMessage(ChatColor.DARK_RED + player.getName() + " Has pvp disabled!");
			e.setCancelled(true);
			return;
		}

		if (!(Main.pvpManager.pvpEnabled(Damager))) {
			Damager.sendMessage(ChatColor.DARK_RED + "You have pvp disabled!");
			e.setCancelled(true);
			return;
		}

	}

}

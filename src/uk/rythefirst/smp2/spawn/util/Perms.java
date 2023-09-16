package uk.rythefirst.smp2.spawn.util;

import org.bukkit.entity.Player;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import uk.rythefirst.smp2.spawn.Main;

public class Perms {

	public static User loadUser(Player player) {
		LuckPerms lpm = Main.lpermsAPI;

		if (!player.isOnline()) {
			throw new IllegalStateException("Player is offline");
		}

		return lpm.getUserManager().getUser(player.getUniqueId());
	}

}

package uk.rythefirst.smp2.spawn.managers;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import uk.rythefirst.smp2.spawn.Main;
import uk.rythefirst.smp2.spawn.util.Perms;

public class NickNames {

	public Boolean hasNickName(OfflinePlayer p) {
		return Main.nickNameCache.nmap.containsKey(p.getUniqueId().toString());
	}

	public void setNickName(Player p, String Nickname) {
		if (Main.nickNameCache.nmap.containsKey(p.getUniqueId().toString())) {
			Main.nickNameCache.nmap.remove(p.getUniqueId().toString());
		}
		Main.nickNameCache.nmap.put(p.getUniqueId().toString(), Nickname);
		setVisibleName(p);
	}

	public void clearNickName(Player p) {
		setNickName(p, p.getName());
	}

	public void setVisibleName(Player p) {
		User user = Perms.loadUser(p);

		CachedMetaData metaData = user.getCachedData().getMetaData();
		String prefix;

		StringBuilder sb = new StringBuilder();

		if (metaData.getPrefix() != null) {
			prefix = ChatColor.translateAlternateColorCodes('&', metaData.getPrefix());
			sb.append(prefix);
		}

		sb.append(Main.nickNameCache.nmap.get(p.getUniqueId().toString()));
		sb.append("&r");
		p.setDisplayName(ChatColor.translateAlternateColorCodes('&', sb.toString()));
	}

	public String getNickName(Player p) {
		if (hasNickName(p)) {
			return Main.nickNameCache.nmap.get(p.getUniqueId().toString());
		}
		return null;
	}

}

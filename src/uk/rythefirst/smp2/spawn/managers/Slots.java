package uk.rythefirst.smp2.spawn.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import uk.rythefirst.smp2.spawn.Main;

public class Slots {

	Integer maxSlots;

	public Slots() {
		maxSlots = 15;
	}

	public void loadPlayersOnline() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (!(Main.SlotCache.slotLst.contains(player.getUniqueId().toString())
					&& player.hasPermission("v2.resslot"))) {
				if (ResSlotLeft()) {
					useSlot(player);
				}
			}
		}
	}

	public void removePlayer(Player p) {
		if (!(Main.SlotCache.slotLst.contains(p.getUniqueId().toString()))) {
			return;
		}
		Main.SlotCache.slotLst.remove(p.getUniqueId().toString());
	}

	public Boolean publicSlotLeft() {
		Integer maxPubSlots = Bukkit.getServer().getMaxPlayers() - maxSlots;
		Integer SlotsUsed = Bukkit.getServer().getOnlinePlayers().size() - maxSlots;
		return !(SlotsUsed >= maxPubSlots);
	}

	public Boolean ResSlotLeft() {
		return !(Main.SlotCache.slotLst.size() >= maxSlots);
	}

	public Integer usedResSlots() {
		return Main.SlotCache.slotLst.size();
	}

	public Integer maxResSlots() {
		return maxSlots;
	}

	public void useSlot(Player p) {
		if (!(Main.SlotCache.slotLst.contains(p.getUniqueId().toString()))) {
			Main.SlotCache.slotLst.add(p.getUniqueId().toString());
		}
	}

}

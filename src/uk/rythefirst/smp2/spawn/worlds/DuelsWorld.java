package uk.rythefirst.smp2.spawn.worlds;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class DuelsWorld {

	public DuelsWorld() {
		load();
	}

	public void load() {
		WorldCreator wc = new WorldCreator("duels-world");
		wc.generator("VoidGenerator");
		wc.type(WorldType.FLAT);
		wc.createWorld();
		Bukkit.getLogger().log(Level.INFO, "[SMP-SPAWN] Duels World Loaded!");
	}

}

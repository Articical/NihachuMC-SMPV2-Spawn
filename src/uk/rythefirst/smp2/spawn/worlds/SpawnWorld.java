package uk.rythefirst.smp2.spawn.worlds;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

public class SpawnWorld {

	public SpawnWorld() {
		load();
	}

	public void load() {
		new WorldCreator("spawn-world").createWorld();
		Bukkit.getLogger().log(Level.INFO, "[SMP-SPAWN] Spawn World Loaded");
	}

}

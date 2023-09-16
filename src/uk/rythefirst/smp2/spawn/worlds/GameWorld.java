package uk.rythefirst.smp2.spawn.worlds;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

public class GameWorld {

	public GameWorld() {
		load();
	}

	public void load() {
		new WorldCreator("world").createWorld();
		Bukkit.getLogger().log(Level.INFO, "[SMP-SPAWN] Game World Loaded!");
	}

}

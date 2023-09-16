package uk.rythefirst.smp2.spawn.events;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoad implements Listener {

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent e) {
		Chunk chunk = e.getChunk();
		World world = e.getWorld();
		if (world.getName() == "spawn-world") {
			if (!(chunk.isForceLoaded())) {
				chunk.setForceLoaded(true);
			}
		}
	}

}

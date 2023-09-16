package uk.rythefirst.smp2.spawn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

	private File configFile = new File(Main.instance.getDataFolder() + "/config.yml");
	private YamlConfiguration configYaml;

	private File jMsgFile = new File(Main.instance.getDataFolder() + "/jmessage.yml");
	private YamlConfiguration jmsgCfg;

	private File lMsgFile = new File(Main.instance.getDataFolder() + "/lmessage.yml");
	private YamlConfiguration lmsgCfg;

	private File pvpFile = new File(Main.instance.getDataFolder() + "/pvp.yml");
	private YamlConfiguration pvpCfg;

	private File nickFile = new File(Main.instance.getDataFolder() + "/nick.yml");
	private YamlConfiguration nickCfg;

	private HashMap<String, Object> cache = new HashMap<String, Object>();

	public ConfigManager() {
		if (!(configFile.exists())) {
			Bukkit.getLogger().log(Level.INFO, Templates.consolePrefix + "Config file not found, creating...");
			Main.instance.getDataFolder().mkdirs();
			try {
				configFile.createNewFile();
				this.configYaml = YamlConfiguration.loadConfiguration(configFile);
				fill(configYaml);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			setConfig(YamlConfiguration.loadConfiguration(configFile));
			cache.put("spawn-location", configYaml.getLocation("spawnlocation"));
		}

		if (!(jMsgFile.exists())) {
			Bukkit.getLogger().info(Templates.consolePrefix + "Jmsg File not found, creating...");
			try {
				jMsgFile.createNewFile();
				this.jmsgCfg = YamlConfiguration.loadConfiguration(jMsgFile);
				jmsgCfg.set("messages", new ArrayList<String>());
				jmsgCfg.save(jMsgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			jmsgCfg = YamlConfiguration.loadConfiguration(jMsgFile);
		}

		if (!(lMsgFile.exists())) {
			Bukkit.getLogger().info(Templates.consolePrefix + "Lmsg File not found, creating...");
			try {
				lMsgFile.createNewFile();
				this.lmsgCfg = YamlConfiguration.loadConfiguration(lMsgFile);
				lmsgCfg.set("messages", new ArrayList<String>());
				lmsgCfg.save(lMsgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			lmsgCfg = YamlConfiguration.loadConfiguration(lMsgFile);
		}

		if (!(pvpFile.exists())) {
			Bukkit.getLogger().info(Templates.consolePrefix + "Pvp File not found, creating...");
			try {
				pvpFile.createNewFile();
				this.pvpCfg = YamlConfiguration.loadConfiguration(pvpFile);
				pvpCfg.set("players", new ArrayList<String>());
				pvpCfg.save(pvpFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			pvpCfg = YamlConfiguration.loadConfiguration(pvpFile);
		}

		if (!(nickFile.exists())) {
			Bukkit.getLogger().info(Templates.consolePrefix + "Nickname File not found, creating...");
			try {
				nickFile.createNewFile();
				this.nickCfg = YamlConfiguration.loadConfiguration(nickFile);
				nickCfg.set("nicknames", new ArrayList<String>());
				nickCfg.save(nickFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			nickCfg = YamlConfiguration.loadConfiguration(nickFile);
		}

	}

	public void saveJoinMessage() {
		TreeMap<String, String> prefixMap = Main.jmCache.jmc;
		String divide = "#::#";
		List<String> fixLst = new ArrayList<String>();
		for (Entry<String, String> entry : prefixMap.entrySet()) {
			fixLst.add(entry.getKey() + divide + entry.getValue());
		}
		jmsgCfg.set("messages", fixLst);
		try {
			jmsgCfg.save(jMsgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadJoinMessage() {
		String divide = "#::#";
		List<String> fixLst = jmsgCfg.getStringList("messages");
		for (String str : fixLst) {
			String[] spltStr = str.split(divide);
			Main.jmCache.jmc.put(spltStr[0], spltStr[1]);
		}
	}

	public void saveLeaveMessage() {
		TreeMap<String, String> prefixMap = Main.lmCache.lmc;
		String divide = "#::#";
		List<String> fixLst = new ArrayList<String>();
		for (Entry<String, String> entry : prefixMap.entrySet()) {
			fixLst.add(entry.getKey() + divide + entry.getValue());
		}
		lmsgCfg.set("messages", fixLst);
		try {
			lmsgCfg.save(lMsgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadLeaveMessage() {
		String divide = "#::#";
		List<String> fixLst = lmsgCfg.getStringList("messages");
		for (String str : fixLst) {
			String[] spltStr = str.split(divide);
			Main.lmCache.lmc.put(spltStr[0], spltStr[1]);
		}
	}

	public void savePvp() {
		TreeMap<String, Boolean> saveMap = Main.pvpcache.pvpMap;
		String divide = "#::#";
		List<String> pvpLst = new ArrayList<String>();
		for (Entry<String, Boolean> entry : saveMap.entrySet()) {
			pvpLst.add(entry.getKey() + divide + entry.getValue().toString());
		}
		pvpCfg.set("players", pvpLst);
		try {
			pvpCfg.save(pvpFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadPvp() {
		String divide = "#::#";
		List<String> loadLst = pvpCfg.getStringList("players");
		for (String str : loadLst) {
			String[] strSplit = str.split(divide);
			Main.pvpcache.pvpMap.put(strSplit[0], Boolean.valueOf(strSplit[1]));
		}

	}

	public void loadNicks() {
		String divide = "#::#";
		List<String> loadLst = nickCfg.getStringList("nicknames");
		for (String str : loadLst) {
			String[] strSplit = str.split(divide);
			Main.nickNameCache.nmap.put(strSplit[0], strSplit[1]);
		}
	}

	public void saveNicks() {
		TreeMap<String, String> saveMap = Main.nickNameCache.nmap;
		String divide = "#::#";
		List<String> pvpLst = new ArrayList<String>();
		for (Entry<String, String> entry : saveMap.entrySet()) {
			pvpLst.add(entry.getKey() + divide + entry.getValue());
		}
		nickCfg.set("nicknames", pvpLst);
		try {
			nickCfg.save(nickFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fill(YamlConfiguration config) throws IOException {
		config.set("spawnlocation", new Location(Bukkit.getWorld("spawn-world"), 0.897, 69, -0.023));
		config.save(configFile);
		cache.put("spawn-location", config.getLocation("spawnlocation"));
		Bukkit.getLogger().log(Level.INFO, "Config file created and loaded.");
	}

	public Location getSpawnLocation() {
		return (Location) cache.get("spawn-location");
	}

	public YamlConfiguration getConfig() {
		return configYaml;
	}

	public void setConfig(YamlConfiguration configYaml) {
		this.configYaml = configYaml;
	}

}

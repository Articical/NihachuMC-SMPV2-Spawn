package uk.rythefirst.smp2.spawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import uk.rythefirst.smp2.spawn.cache.JoinMessageCache;
import uk.rythefirst.smp2.spawn.cache.LeaveMessageCache;
import uk.rythefirst.smp2.spawn.cache.NickNameCache;
import uk.rythefirst.smp2.spawn.cache.Pvpcache;
import uk.rythefirst.smp2.spawn.cache.Slotcache;
import uk.rythefirst.smp2.spawn.commands.Pvptoggle;
import uk.rythefirst.smp2.spawn.commands.SlotsCMD;
import uk.rythefirst.smp2.spawn.events.ChunkLoad;
import uk.rythefirst.smp2.spawn.events.CommandPreProcess;
import uk.rythefirst.smp2.spawn.events.PlayerChat;
import uk.rythefirst.smp2.spawn.events.PlayerDamage;
import uk.rythefirst.smp2.spawn.events.PlayerJoin;
import uk.rythefirst.smp2.spawn.events.PlayerLeave;
import uk.rythefirst.smp2.spawn.events.PlayerLogin;
import uk.rythefirst.smp2.spawn.managers.CJoinMessage;
import uk.rythefirst.smp2.spawn.managers.CLeaveMessage;
import uk.rythefirst.smp2.spawn.managers.NickNames;
import uk.rythefirst.smp2.spawn.managers.Pvp;
import uk.rythefirst.smp2.spawn.managers.Slots;
import uk.rythefirst.smp2.spawn.speedtyper.ChatWordGame;
import uk.rythefirst.smp2.spawn.tabcomplete.PvpTab;
import uk.rythefirst.smp2.spawn.worlds.DuelsWorld;
import uk.rythefirst.smp2.spawn.worlds.GameWorld;
import uk.rythefirst.smp2.spawn.worlds.SpawnWorld;

public class Main extends JavaPlugin {

	SpawnWorld sworld;
	GameWorld gworld;
	DuelsWorld dWorld;
	public static LuckPerms lpermsAPI;
	public static ConfigManager configManager;
	public static JoinMessageCache jmCache;
	public static CJoinMessage joinMsgManager;
	public static LeaveMessageCache lmCache;
	public static CLeaveMessage leaveMsgManager;
	public static Pvpcache pvpcache;
	public static Pvp pvpManager;
	public static NickNameCache nickNameCache;
	public static NickNames nickNameManager;
	public static Slotcache SlotCache;
	public static Slots SlotManager;
	public static Plugin instance;

	private static ChatWordGame cwg;

	private static Economy econ;
	private static Permission perms;
	private static Chat chat;

	@Override
	public void onEnable() {

		// Easy way to call the main plugin by using Main.instance();
		instance = this;

		// Setup Caches
		pvpcache = new Pvpcache();
		jmCache = new JoinMessageCache();
		lmCache = new LeaveMessageCache();
		nickNameCache = new NickNameCache();
		SlotCache = new Slotcache();

		// Setup Luckperms
		lpermsAPI = LuckPermsProvider.get();

		// Load the worlds
		sworld = new SpawnWorld();
		gworld = new GameWorld();
		dWorld = new DuelsWorld();

		// Load the Manager Classes
		setConfigManager(new ConfigManager());
		joinMsgManager = new CJoinMessage();
		leaveMsgManager = new CLeaveMessage();
		pvpManager = new Pvp();
		nickNameManager = new NickNames();
		SlotManager = new Slots();

		// Speed Typer
		cwg = new ChatWordGame();

		// Load config files
		configManager.loadJoinMessage();
		configManager.loadLeaveMessage();
		configManager.loadPvp();
		configManager.loadNicks();

		// Register Events
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDamage(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerChat(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLogin(), this);
		Bukkit.getPluginManager().registerEvents(new CommandPreProcess(), this);
		Bukkit.getPluginManager().registerEvents(new ChunkLoad(), this);

		// Register Commands
		// this.getCommand("jmessage").setExecutor(new Joinmessage());
		// this.getCommand("lmessage").setExecutor(new Leavemessage());
		this.getCommand("pvp").setExecutor(new Pvptoggle());
		// this.getCommand("cnick").setExecutor(new Nick());
		// this.getCommand("nick").setExecutor(new Nick());
		// this.getCommand("nickname").setExecutor(new Nick());
		this.getCommand("slots").setExecutor(new SlotsCMD());

		// Set Tab Completes
		// getCommand("cnick").setTabCompleter(new
		// uk.rythefirst.smp2.spawn.tabcomplete.Nick());
		// getCommand("jmessage").setTabCompleter(new JoinMessageTab());
		// getCommand("lmessage").setTabCompleter(new LeaveMessageTab());
		getCommand("pvp").setTabCompleter(new PvpTab());

		// Start the speed typer
		cwg.runGame();

		// Load online players into reserved slots.
		SlotManager.loadPlayersOnline();

		// Setup Vault
		if (!setupEconomy()) {
			this.getLogger().severe("Disabled due to no Vault dependency found!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		this.setupPermissions();
		this.setupChat();

	}

	@Override
	public void onDisable() {
		configManager.saveJoinMessage();
		configManager.saveLeaveMessage();
		configManager.savePvp();
		configManager.saveNicks();
	}

	public static ChatWordGame getCwg() {
		return cwg;
	}

	public static ConfigManager getConfigManager() {
		return configManager;
	}

	public static void setConfigManager(ConfigManager configManager) {
		Main.configManager = configManager;
	}

	private boolean setupEconomy() {
		if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		return chat != null;
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}

	public static Economy getEconomy() {
		return econ;
	}

	public static Permission getPermissions() {
		return perms;
	}

	public static Chat getChat() {
		return chat;
	}

}

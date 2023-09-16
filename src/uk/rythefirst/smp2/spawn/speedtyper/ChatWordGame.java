package uk.rythefirst.smp2.spawn.speedtyper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import uk.rythefirst.smp2.spawn.Main;
import uk.rythefirst.smp2.spawn.util.Messages;

public class ChatWordGame {

	private ChatWord cword = null;

	private File wordFile = new File(Main.instance.getDataFolder() + "/chatwords.yml");

	private List<String> defWords = new ArrayList<String>();

	private FileConfiguration wordCNF;

	private Boolean onGoing = false;

	private List<String> wordlst = new ArrayList<String>();

	public ChatWordGame() {

		defWords.add("kirbephine");
		defWords.add("twitch");
		defWords.add("amazon prime");
		defWords.add("fish");
		defWords.add("jojo");
		defWords.add("josephine");

		if (!(wordFile.exists())) {
			Bukkit.getLogger().log(Level.INFO, "[SpawnPL] Words file not found, creating.");
			try {
				wordFile.createNewFile();
				wordCNF = YamlConfiguration.loadConfiguration(wordFile);
				wordCNF.set("words", defWords);
				wordCNF.save(wordFile);
				Bukkit.getLogger().log(Level.INFO, "[SpawnPL] Words file created.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Bukkit.getLogger().log(Level.INFO, "[SpawnPL] Words file found, loading words.");
			wordCNF = YamlConfiguration.loadConfiguration(wordFile);
		}
		wordlst = wordCNF.getStringList("words");
		Bukkit.getLogger().log(Level.INFO, "[SpawnPL] Words loaded.");
	}

	public void runGame() {

		Bukkit.getScheduler().runTaskTimer(Main.instance, new Runnable() {
			@Override
			public void run() {

				if (Bukkit.getOnlinePlayers().size() > 3) {

					// TODO Auto-generated method stub
					Random rand = new Random();

					int wordCount = wordlst.size();
					int maxnum = wordCount;
					if (!(wordCount == 0)) {
						maxnum = wordCount - 1;
					}
					int chosen = rand.nextInt(maxnum);

					String wordPicked = wordlst.get(chosen);

					cword = new ChatWord(wordPicked);
					for (Player p : Bukkit.getOnlinePlayers()) {
						Messages.sendCenteredMessage(p, "" + ChatColor.GOLD + ChatColor.UNDERLINE + "Speed Typer");
						Messages.sendCenteredMessage(p, ChatColor.GOLD + "First person to type " + cword.getWord());
						Messages.sendCenteredMessage(p, ChatColor.GOLD + "Wins $1000");
						Messages.sendCenteredMessage(p, ChatColor.GOLD + "You have 30 seconds!");
						Messages.sendCenteredMessage(p, ChatColor.GOLD + "");
						p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1f, 1f);
					}

					onGoing = true;

					Bukkit.getLogger().log(Level.INFO, "[NihaESS] Chat Word Running! Word: " + cword.getWord());

					new BukkitRunnable() {
						@Override
						public void run() {
							onGoing = false;
							if (!(cword.isGuessed())) {
								for (Player p : Bukkit.getOnlinePlayers()) {
									Messages.sendCenteredMessage(p,
											"" + ChatColor.GOLD + ChatColor.BOLD + ChatColor.UNDERLINE + "Speed Typer");
									Messages.sendCenteredMessage(p, ChatColor.GOLD + "Times Up!");
									Messages.sendCenteredMessage(p, ChatColor.GOLD + "No one typed the word in time!");
									Messages.sendCenteredMessage(p, ChatColor.GOLD + "");
									p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1f, 1f);
								}
								cword = null;
								Bukkit.getLogger().log(Level.INFO,
										"[SpawnPL] Chat Word ended, the word wasn't guessed!");
							}
						}
					}.runTaskLater(Main.instance, 600l);
				}
			}
		}, 0xDl, 24000l);

	}

	public Boolean isOnGoing() {
		return onGoing;
	}

	public void setOnGoing(Boolean setting) {
		onGoing = setting;
	}

	public ChatWord getWord() {
		return cword;
	}

}

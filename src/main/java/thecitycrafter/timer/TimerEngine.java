package thecitycrafter.timer;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TimerEngine {
    public static void Engine() {
        FileConfiguration config = Main.getPlugin().getConfig();
        if (config.getBoolean("timer-running")) {
            config.set("current-time.seconds", config.getInt("current-time.seconds") + 1);
            if (config.getInt("current-time.seconds") >= 60) {
                config.set("current-time.seconds", 0);
                config.set("current-time.minutes", config.getInt("current-time.minutes") + 1);
            }
            if (config.getInt("current-time.minutes") >= 60) {
                config.set("current-time.minutes", 0);
                config.set("current-time.hours", config.getInt("current-time.hours") + 1);
            }
            if (config.getInt("current-time.hours") >= 24) {
                config.set("current-time.hours", 0);
                config.set("current-time.days", config.getInt("current-time.days") + 1);
            }
            Main.getPlugin().saveConfig();
        }
    }

    public static void showEngine() {
        FileConfiguration config = Main.getPlugin().getConfig();
        if (config.getBoolean("timer-shown")) {
            String color = null;
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                switch (config.getString("timer-color")) {
                    case "black" -> color = "§0";
                    case "dark_blue" -> color = "§1";
                    case "dark_green" -> color = "§2";
                    case "dark_aqua" -> color = "§3";
                    case "dark_red" -> color = "§4";
                    case "dark_purple" -> color = "§5";
                    case "gold" -> color = "§6";
                    case "gray" -> color = "§7";
                    case "dark_gray" -> color = "§8";
                    case "blue" -> color = "§9";
                    case "green" -> color = "§a";
                    case "aqua" -> color = "§b";
                    case "red" -> color = "§c";
                    case "pink" -> color = "§d";
                    case "yellow" -> color = "§e";
                    case "white" -> color = "§f";
                    default -> color = "§f";
                }
                String timer = config.getString("current-time.days") + "d " + config.getString("current-time.hours") + "h " + config.getString("current-time.minutes") + "m " + config.getString("current-time.seconds") + "s";
                String timerPaused = !config.getBoolean("timer-running") ? " §o(Paused)" : "";
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(color + timer + timerPaused));
            }
        }
    }
}

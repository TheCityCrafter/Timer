package thecitycrafter.timer;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player player){
            FileConfiguration config = Main.getPlugin().getConfig();
            if (args.length == 0){
                sender.sendMessage("§6Timer Plugin coded by TheCityCrafter");
                sender.sendMessage("§7Version: 1.0.0");
                return true;
            }else if (args.length == 1 && (args[0].equals("set") || args[0].equals("color"))){
                sender.sendMessage("§4Not enough arguments!");
                return true;
            }


            switch (args[0]) {
                case "pause": {
                    config.set("timer-running", false);
                    Main.getPlugin().saveConfig();
                    break;
                }
                case "resume": {
                    config.set("timer-running", true);
                    Main.getPlugin().saveConfig();
                    break;
                }
                case "toggle": {
                    config.set("timer-shown", !config.getBoolean("timer-shown"));
                    Main.getPlugin().saveConfig();
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(""));
                    break;
                }
                case "reset": {
                    config.set("current-time.seconds", 0);
                    config.set("current-time.minutes", 0);
                    config.set("current-time.hours", 0);
                    config.set("current-time.days", 0);
                    config.set("timer-running", false);
                    Main.getPlugin().saveConfig();
                    break;
                }
                case "set": {
                    try {
                        int arg2 = Integer.valueOf(args[2]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("§4"+ '"' + args[2] + '"' + " is not a number!");
                        return true;
                    }

                    switch (args[1]) {
                        case "seconds": {
                            config.set("current-time.seconds", Integer.valueOf(args[2]));
                            Main.getPlugin().saveConfig();
                            break;
                        }
                        case "minutes": {
                            config.set("current-time.minutes", Integer.valueOf(args[2]));
                            Main.getPlugin().saveConfig();
                            break;
                        }
                        case "hours": {
                            config.set("current-time.hours", Integer.valueOf(args[2]));
                            Main.getPlugin().saveConfig();
                            break;
                        }
                        case "days": {
                            config.set("current-time.days", Integer.valueOf(args[2]));
                            Main.getPlugin().saveConfig();
                            break;
                        }
                    }
                    break;
                }
                case "color": {
                    config.set("timer-color", args[1]);
                    Main.getPlugin().saveConfig();
                    break;
                }
                default: sender.sendMessage("§4Unknown Argument!");
            }
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> tabComplete = new ArrayList<>();
        if (args.length == 1) {
            tabComplete.add("toggle");
            tabComplete.add("pause");
            tabComplete.add("resume");
            tabComplete.add("reset");
            tabComplete.add("set");
            tabComplete.add("color");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            tabComplete.add("seconds");
            tabComplete.add("minutes");
            tabComplete.add("hours");
            tabComplete.add("days");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("color")) {
            tabComplete.add("black");
            tabComplete.add("dark_blue");
            tabComplete.add("dark_green");
            tabComplete.add("dark_aqua");
            tabComplete.add("dark_red");
            tabComplete.add("dark_purple");
            tabComplete.add("gold");
            tabComplete.add("gray");
            tabComplete.add("dark_gray");
            tabComplete.add("blue");
            tabComplete.add("green");
            tabComplete.add("aqua");
            tabComplete.add("red");
            tabComplete.add("pink");
            tabComplete.add("yellow");
            tabComplete.add("white");
        }
        return tabComplete;
    }

}

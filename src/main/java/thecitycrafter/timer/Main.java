package thecitycrafter.timer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main plugin;

    @Override
    public void onEnable() {
        getCommand("timer").setExecutor(new TimerCommand());
        this.saveDefaultConfig();
        plugin = this;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, TimerEngine::showEngine, 1L, 1L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, TimerEngine::Engine, 20L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}

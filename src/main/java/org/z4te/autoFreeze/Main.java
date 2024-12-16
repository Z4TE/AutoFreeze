package org.z4te.autoFreeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Bukkit.getOnlinePlayers().size() == 1) {
            World world = getServer().getWorld("world");
            assert world != null;
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Daylight cycle resumed");
            Bukkit.getLogger().info("Daylight cycle resumed");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (Bukkit.getOnlinePlayers().isEmpty()) {
            World world = getServer().getWorld("world");
            assert world != null;
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            Bukkit.getLogger().info("Daylight cycle paused");
        }
    }
}

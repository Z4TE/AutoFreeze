package org.z4te.autoFreeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        disableCycle();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        disableCycle();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Bukkit.getOnlinePlayers().size() == 1) {
            enableCycle();
            Player player = event.getPlayer();
            player.sendMessage(ChatColor.YELLOW + "Daylight cycle resumed");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (Bukkit.getOnlinePlayers().size() == 1) {
            disableCycle();
        }
    }

    private void enableCycle() {
        World world = getServer().getWorld("world");
        assert world != null;
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
        Bukkit.getLogger().info(ChatColor.YELLOW + "Daylight cycle resumed");
    }

    private void disableCycle() {
        World world = getServer().getWorld("world");
        assert world != null;
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        Bukkit.getLogger().info(ChatColor.YELLOW + "Daylight cycle paused");
    }
}

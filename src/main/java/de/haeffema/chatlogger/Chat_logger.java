package de.haeffema.chatlogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;


public final class Chat_logger extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) throws IOException {
        final BufferedWriter writer = Files.newBufferedWriter(Paths.get("chat.txt"), StandardCharsets.UTF_8);
        writer.write(String.format("%s: %s", event.getPlayer().getDisplayName(), event.getMessage()));
        writer.newLine();
        writer.close();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
        final BufferedWriter writer = Files.newBufferedWriter(Paths.get("deaths.txt"), StandardCharsets.UTF_8);
        writer.write(Objects.requireNonNull(event.getDeathMessage()));
        writer.newLine();
        writer.close();
    }

    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) throws IOException {
        final BufferedWriter writer = Files.newBufferedWriter(Paths.get("advancements.txt"), StandardCharsets.UTF_8);
        writer.write(String.format("%s: %s", event.getPlayer().getDisplayName(), Objects.requireNonNull(event.getAdvancement().getDisplay()).getTitle()));
        writer.newLine();
        writer.close();
    }

}

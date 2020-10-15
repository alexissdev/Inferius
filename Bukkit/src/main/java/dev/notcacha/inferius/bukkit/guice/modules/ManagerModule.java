package dev.notcacha.inferius.bukkit.guice.modules;

import com.google.inject.AbstractModule;
import dev.notcacha.inferius.bukkit.chat.ChatManager;
import dev.notcacha.inferius.bukkit.chat.InferiusChatManager;
import dev.notcacha.inferius.bukkit.punishment.manager.InferiusPunishmentManager;
import dev.notcacha.inferius.bukkit.punishment.manager.PunishmentManager;
import dev.notcacha.inferius.bukkit.spawn.InferiusSpawnManager;
import dev.notcacha.inferius.bukkit.spawn.SpawnManager;

public class ManagerModule extends AbstractModule
{
    @Override
    protected void configure() {
        this.bind(PunishmentManager.class).to(InferiusPunishmentManager.class);
        this.bind(ChatManager.class).to(InferiusChatManager.class);
        this.bind(SpawnManager.class).to(InferiusSpawnManager.class);
    }
}

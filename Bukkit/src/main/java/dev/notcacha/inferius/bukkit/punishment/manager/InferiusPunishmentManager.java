package dev.notcacha.inferius.bukkit.punishment.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.Inferius;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.punishment.issuer.Issuer;
import dev.notcacha.inferius.bukkit.punishment.punished.Punished;
import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;
import dev.notcacha.inferius.storage.Storage;

@Singleton
public class InferiusPunishmentManager implements PunishmentManager {

    @Inject
    private Inferius plugin;

    @Inject
    private Storage<Punishment> punishmentStorage;

    @Override
    public void add(String id, Issuer issuer, Punished punished, String reason, PunishmentType type, Long endTime) {
        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () -> {

            Punishment punishment = Punishment.builder(id)
                    .setIssuer(issuer)
                    .setPunished(punished)
                    .setReason(reason)
                    .setType(type)
                    .setEndTime(endTime)
                    .build();

            punishmentStorage.save(punishment);

        }, 20);
    }

}

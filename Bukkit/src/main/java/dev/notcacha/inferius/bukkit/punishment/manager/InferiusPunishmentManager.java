package dev.notcacha.inferius.bukkit.punishment.manager;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;
import dev.notcacha.inferius.response.async.AsyncResponse;
import dev.notcacha.inferius.response.async.SimpleAsyncResponse;
import dev.notcacha.inferius.storage.Storage;

@Singleton
public class InferiusPunishmentManager implements PunishmentManager {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private Storage<Punishment> punishmentStorage;

    @Override
    public void add(String id, String issuerId, String issuerName, String punishedId, String punishedName, String punishedAddress, String reason, PunishmentType type, Long endTime) {
        Punishment punishment = Punishment.builder(id)
                .setIssuerId(issuerId)
                .setIssuerName(issuerName)
                .setPunishedId(punishedId)
                .setPunishedName(punishedName)
                .setPunishedAddress(punishedAddress)
                .setReason(reason)
                .setType(type)
                .setEndTime(endTime)
                .build();

        punishmentStorage.saveSync(punishment);
    }

    @Override
    public AsyncResponse<Void> addAsync(String id, String issuerId, String issuerName, String punishedId, String punishedName, String punishedAddress, String reason, PunishmentType type, Long endTime) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            add(id, issuerId, issuerName, punishedId, punishedName, punishedAddress, reason, type, endTime);
            return null;
        }));
    }
}

package dev.notcacha.inferius.bukkit.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import dev.notcacha.inferius.bukkit.punishment.Punishment;
import dev.notcacha.inferius.bukkit.punishment.issuer.Issuer;
import dev.notcacha.inferius.bukkit.punishment.punished.Punished;
import dev.notcacha.inferius.bukkit.punishment.type.PunishmentType;
import dev.notcacha.inferius.json.adapter.GsonAdapter;

import java.lang.reflect.Type;

public class PunishmentGsonAdapter implements GsonAdapter<Punishment> {

    @Override
    public JsonElement serialize(Punishment src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("_id", src.getId());
        object.addProperty("issuer_id", src.getIssuer().getId());
        object.addProperty("issuer_name", src.getIssuer().getName());
        object.addProperty("punished_id", src.getPunished().getId());
        object.addProperty("punished_name", src.getPunished().getName());
        object.addProperty("punished_ip", src.getPunished().getAddress());
        object.addProperty("reason", src.getReason());
        object.addProperty("type", src.getType().getId());
        object.addProperty("end_time", src.getEndTime());

        return object;
    }

    @Override
    public Punishment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        return Punishment.builder(
                object.get("_id").getAsString()
        )
                .setIssuer(
                        Issuer.builder(
                                object.get("issuer_id").getAsString()
                        )
                                .setName(
                                        object.get("issuer_name").getAsString()
                                )
                                .build()
                )
                .setPunished(
                        Punished.build(
                                object.get("punished_id").getAsString()
                        )
                                .setName(
                                        object.get("punished_name").getAsString()
                                )
                                .setAddress(
                                        object.get("punished_ip").getAsString()
                                )
                                .build()
                )
                .setReason(
                        object.get("reason").getAsString()
                )
                .setType(
                        PunishmentType.valueOf(
                                object.get("type").getAsString()
                        )
                )
                .setEndTime(object.get("end_time").getAsLong())
                .build();
    }
}

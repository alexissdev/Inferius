package dev.notcacha.inferius.bukkit.punishment.issuer;

import dev.notcacha.inferius.buildable.Buildable;
import dev.notcacha.inferius.model.Model;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface Issuer extends Model {

    /**
     * @return name from Issuer
     */

    String getName();

    /**
     * @return a new object {@link Issuer} using the parameter {@param player} to get the properties to set
     */

    static Issuer fromPlayer(Player player) {
        return new InferiusIssuer(
                player.getUniqueId().toString(),
                player.getName()
        );
    }

    /**
     * @return a new object {@link Issuer} using the parameter {@param sender} to obtain the properties to be set,
     * in any case that {@param sender} is a {@link Player} this method will return the method {@linkplain Issuer#fromPlayer(Player)}
     */

    static Issuer fromCommandSender(CommandSender sender) {
        if (sender instanceof Player) {
            return fromPlayer((Player) sender);
        }

        return new InferiusIssuer(
                "SENDER_ID",
                sender.getName()
        );
    }

    interface Builder extends Buildable<Issuer> {

        /**
         * Set name from {@link Issuer}, {@param name} has been set
         */

        Builder setName(String name);

    }

    /**
     * @return {@link Builder} from create {@link Issuer} object.
     */

    static Builder builder(String id) {
        return new InferiusIssuer.Builder(id);
    }


}

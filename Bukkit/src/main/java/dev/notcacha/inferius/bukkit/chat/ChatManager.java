package dev.notcacha.inferius.bukkit.chat;

public interface ChatManager {

    /**
     * @return if the chat is currently muted in {@link Boolean} format
     */

    boolean isMuted();

    /**
     * Set whether the chat will be mute or unmute
     *
     * @param muted state to be set
     */

    void setMuted(boolean muted);

    /**
     * @return if the chat has a delay set in {@link Boolean} format
     */

    default boolean hasDelayed() {
        return !(getDelay() == -1L);
    }

    /**
     * @return the time that will be set as delay to chat
     */

    Long getDelay();

    /**
     * Set delay from chat
     *
     * @param delay has been set
     */

    void setDelay(Long delay);

}

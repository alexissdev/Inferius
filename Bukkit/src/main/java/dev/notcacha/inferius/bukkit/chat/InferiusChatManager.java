package dev.notcacha.inferius.bukkit.chat;

import com.google.inject.Singleton;

@Singleton
public class InferiusChatManager implements ChatManager {

    private boolean muted;
    private Long delay;

    public InferiusChatManager() {
        this.muted = false;
        this.delay = -1L;
    }

    @Override
    public boolean isMuted() {
        return this.muted;
    }

    @Override
    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    @Override
    public Long getDelay() {
        return this.delay;
    }

    @Override
    public void setDelay(Long delay) {
        this.delay = delay;
    }
}

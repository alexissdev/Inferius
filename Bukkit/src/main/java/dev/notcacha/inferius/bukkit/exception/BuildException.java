package dev.notcacha.inferius.bukkit.exception;

public class BuildException extends RuntimeException {

    public BuildException() {
        super();
    }

    public BuildException(String message) {
        super(message);
    }

    public BuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuildException(Throwable cause) {
        super(cause);
    }
}

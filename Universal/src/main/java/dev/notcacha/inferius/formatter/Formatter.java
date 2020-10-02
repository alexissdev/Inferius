package dev.notcacha.inferius.formatter;

public interface Formatter<T> {

    /**
     * Set format from text
     *
     * @param holder from get options from set
     * @param text   has been set format
     * @return {@link String} with the established format
     */

    String format(T holder, String text);

    /**
     * Set placeholders from text
     *
     * @param text   has been set placeholders
     * @param holder from get values
     * @return text from format
     */

    String setPlaceholders(T holder, String text);

    /**
     * Merge other format from this format
     *
     * @param formatter has been merge
     */

    Formatter<?> merge(Formatter<?> formatter);

}

package dev.notcacha.inferius.model;

import java.util.Map;

public interface SavableModel extends Model {

    /**
     * Save model
     *
     * @return save in map
     */

    Map<String, Object> save();

}

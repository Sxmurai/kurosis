package no.kurosis.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A registry holding all of the player data
 *
 * @author aesthetical
 * @since 12/10/22
 */
public class DataRegistry {

    /**
     * A map of the player UUID resolving to their PlayerData
     */
    private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    /**
     * Gets the player data, or creates new data if non exists
     * @param uuid the uuid of the player
     * @return the PlayerData object resolved from their UUID
     */
    public PlayerData get(UUID uuid) {
        return playerDataMap.computeIfAbsent(uuid, (s) -> new PlayerData(uuid));
    }

    /**
     * Removes their player data
     * @param uuid the players UUID
     */
    public void remove(UUID uuid) {
        playerDataMap.remove(uuid);
    }

}

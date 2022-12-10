package no.kurosis.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataRegistry {

    private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    public PlayerData get(UUID uuid) {
        return playerDataMap.computeIfAbsent(uuid, (s) -> new PlayerData(uuid));
    }

    public void remove(UUID uuid) {
        playerDataMap.remove(uuid);
    }

}

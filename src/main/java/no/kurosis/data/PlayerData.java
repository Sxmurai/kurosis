package no.kurosis.data;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;

    public final InventoryData inventoryData = new InventoryData();
    public final MoveData moveData = new MoveData();

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}

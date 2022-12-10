package no.kurosis.event.events.net;

import me.bush.eventbus.event.Event;
import net.minecraft.server.v1_8_R3.Packet;

import java.util.UUID;

public class PacketEvent extends Event {

    private final Packet<?> packet;
    private final PacketSide side;
    private final UUID uuid;

    public PacketEvent(UUID uuid, Packet<?> packet, PacketSide side) {
        this.uuid = uuid;
        this.packet = packet;
        this.side = side;
    }

    public UUID getUuid() {
        return uuid;
    }

    public <T extends Packet<?>> T getPacket() {
        return (T) packet;
    }

    public PacketSide getSide() {
        return side;
    }

    @Override
    protected boolean isCancellable() {
        return false;
    }
}

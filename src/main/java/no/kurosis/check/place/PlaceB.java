package no.kurosis.check.place;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

public class PlaceB extends Check {

    private long placeTime = -1L;
    private boolean placed = false;

    private boolean swing = false;

    public PlaceB(PlayerData data) {
        super("PlaceB (No Swing)", data, 5.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
//        if (event.getPacket() instanceof PacketPlayInArmAnimation) {
//
//            swing = true;
//
//        } else if (event.getPacket() instanceof PacketPlayInBlockPlace) {
//
//            placed = true;
//            placeTime = System.currentTimeMillis();
//        } else if (event.getPacket() instanceof PacketPlayInFlying) {
//
//            if (placed && !swing && System.currentTimeMillis() - placeTime > 60L) {
//                violate(1.0);
//            }
//
//            placed = false;
//            swing = false;
//
//        }
    }
}

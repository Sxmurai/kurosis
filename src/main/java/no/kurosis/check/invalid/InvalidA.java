package no.kurosis.check.invalid;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Checks if sprint/sneak packets are sent while already sprinting/sneaking, the vanilla game will never do this
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class InvalidA extends Check {

    private boolean sprinting = false;
    private boolean sneaking = false;

    public InvalidA(PlayerData data) {
        super("InvalidA (Unneeded Actions)", data, 2.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInEntityAction) {
            PacketPlayInEntityAction packet = event.getPacket();
            switch (packet.b()) {
                case START_SPRINTING: {

                    // if we are already sprinting and START_SPRINTING is sent, violate
                    if (sprinting) {
                        violate(1.0);
                    }

                    sprinting = true;
                    break;
                }

                case STOP_SPRINTING: {

                    // if we already not sprinting and STOP_SPRINTING is sent, violate
                    if (!sprinting) {
                        violate(1.0);
                    }

                    sprinting = false;
                    break;
                }

                case START_SNEAKING: {

                    // if we are already sneaking and START_SNEAKING is sent, violate
                    if (sneaking) {
                        violate(1.0);
                    }

                    sneaking = true;
                    break;
                }

                case STOP_SNEAKING: {

                    // if we already not sneaking and STOP_SNEAKING is sent, violate
                    if (!sneaking) {
                        violate(1.0);
                    }

                    sneaking = false;
                    break;
                }
            }
        }
    }
}

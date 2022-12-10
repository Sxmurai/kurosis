package no.kurosis.check.autoclicker;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;
import no.kurosis.check.Check;
import no.kurosis.check.Experimental;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;
import no.kurosis.util.MathUtils;

import java.util.Arrays;

/**
 * A check that checks for standard deviation in attacks
 *
 * @author aesthetical
 * @since 12/10/22
 */
@Experimental
public class AutoClickerB extends Check {

    private final double[] samples = new double[10];
    private long lastTime = -1L;
    private int i = 0;
    private int times = 0;

    public AutoClickerB(PlayerData data) {
        super("AutoClickerB (Standard Deviation)", data, 5.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInUseEntity) {
            PacketPlayInUseEntity packet = event.getPacket();
            if (packet.a().equals(EnumEntityUseAction.ATTACK)) {

                if (lastTime == -1L) {
                    lastTime = System.currentTimeMillis();
                }

                ++times;
                if (System.currentTimeMillis() - lastTime >= 1000L) {
                    samples[i % samples.length] = times;
                    times = 0;
                    lastTime = -1L;

                    ++i;
                }

                // TODO: i am retarded and need to fix this
                if (i >= samples.length) {
                    double deviation = MathUtils.standardDeviation(samples);
                    if (deviation > 3.0) {
                        violate(1.0);
                    }
                }
            }
        } else if (event.getPacket() instanceof PacketPlayInFlying) {

            // TODO: this should probably not be here
            if (System.currentTimeMillis() - lastTime >= 2500L && i > 0) {
                lastTime = -1L;
                i = 0;
                Arrays.fill(samples, 0.0);
                times = 0;
            }
        }
    }
}

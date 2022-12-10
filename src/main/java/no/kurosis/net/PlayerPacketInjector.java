package no.kurosis.net;

import io.netty.channel.*;
import net.minecraft.server.v1_8_R3.Packet;
import no.kurosis.Kurosis;
import no.kurosis.event.events.net.PacketEvent;
import no.kurosis.event.events.net.PacketSide;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * A class for injecting into the packet_handler in netty to intercept send/received packets
 * to analyze them for the anti-cheat
 *
 * @author aesthetical
 * @since 12/10/22
 */
public class PlayerPacketInjector {

    /**
     * Injects the a new packet handler into this bukkit player
     * @param player the bukkit player
     */
    public static void inject(Player player) {
        ChannelDuplexHandler handler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
                if (o instanceof Packet<?>) {
                    Kurosis.BUS.post(new PacketEvent(player.getUniqueId(), (Packet<?>) o, PacketSide.CLIENT));
                }

                super.channelRead(channelHandlerContext, o);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {
                if (o instanceof Packet<?>) {
                    Kurosis.BUS.post(new PacketEvent(player.getUniqueId(), (Packet<?>) o, PacketSide.SERVER));
                }

                super.write(channelHandlerContext, o, channelPromise);
            }
        };

        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), handler);
    }

    /**
     * Removes the packet handler from this bukkit player
     * @param player the bukkit player
     */
    public static void uninject(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }
}

package no.kurosis.event.listener.bukkit;

import no.kurosis.Kurosis;
import no.kurosis.net.PlayerPacketInjector;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * A bukkit listener for when a player joins/leaves the game
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class PlayerJoinQuitListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        PlayerPacketInjector.inject(event.getPlayer());
        Kurosis.getInstance().getCheckManager().registerChecks(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        PlayerPacketInjector.uninject(event.getPlayer());
        Kurosis.getInstance().getCheckManager().unregisterChecks(event.getPlayer().getUniqueId());
    }

}

package no.kurosis.alert;

import no.kurosis.check.Check;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Handles alerts sent from violations of a check to online staff members
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class AlertManager {

    private static final String PREFIX = ChatColor.BLUE + "[Kurosis] " + ChatColor.RESET;

    public void notify(Check check) {
        Player offendingPlayer = Bukkit.getPlayer(check.getData().getUuid());
        if (offendingPlayer == null) {
            return;
        }

        notify(ChatColor.GRAY
                + offendingPlayer.getName() + " failed " +
                ChatColor.BLUE + check.getName() + ChatColor.GRAY +
                " (x" + String.format("%.1f", check.getViolationCount()) + ")");

    }

    public void notify(String info) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isOp()) {
                player.sendRawMessage(PREFIX + info);
            }
        }
    }

}

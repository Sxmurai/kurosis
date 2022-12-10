package no.kurosis.util;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * A utility class for things bukkit/server related
 *
 * @author aesthetical
 * @since 12/10/22
 */
public class BukkitUtils {

    /**
     * Gets a Bukkit Player object by a UUID
     * @param uuid the uuid of this player
     * @return the bukkit Player, or null if none
     */
    public static Player getPlayer(UUID uuid) {
        return Bukkit.getPlayer(uuid);
    }

    /**
     * Gets the entity player in the vanilla minecraft server code
     * @param uuid the uuid of the player
     * @return the EntityPlayr object, or null
     */
    public static EntityPlayer getRealPlayer(UUID uuid) {
        return ((CraftPlayer) Bukkit.getPlayer(uuid)).getHandle();
    }

}

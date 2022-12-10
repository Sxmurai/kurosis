package no.kurosis;

import me.bush.eventbus.bus.EventBus;
import no.kurosis.alert.AlertManager;
import no.kurosis.check.CheckRegistry;
import no.kurosis.data.DataRegistry;
import no.kurosis.event.listener.bukkit.PlayerJoinQuitListener;
import no.kurosis.event.listener.packet.InventoryPacketListener;
import no.kurosis.event.listener.packet.MovePacketListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main java plugin class for Kurosis
 * @author aesthetical
 * @since 12/09/22
 */
public class Kurosis extends JavaPlugin {
    private static Kurosis instance;
    public static final EventBus BUS = new EventBus();

    private CheckRegistry checkRegistry;
    private AlertManager alertManager;
    private DataRegistry dataRegistry;

    @Override
    public void onEnable() {

        // load this instance variable
        instance = this;

        // load bukkit listeners
        loadBukkitListener(new PlayerJoinQuitListener());

        // add event bus listeners
        BUS.subscribe(new InventoryPacketListener());
        BUS.subscribe(new MovePacketListener());

        // create managers
        checkRegistry = new CheckRegistry();
        alertManager = new AlertManager();
        dataRegistry = new DataRegistry();
    }

    /**
     * Gets the check manager instance
     * @return the check manager
     */
    public CheckRegistry getCheckManager() {
        return checkRegistry;
    }

    /**
     * Gets the alert manager instance
     * @return the alert manager
     */
    public AlertManager getAlertManager() {
        return alertManager;
    }

    /**
     * Gets the data registry cache
     * @return the data registry cache
     */
    public DataRegistry getDataRegistry() {
        return dataRegistry;
    }

    /**
     * Loads a bukkit event via the PluginManager
     * @param listenerIn the listener to register
     */
    private void loadBukkitListener(Listener listenerIn) {
        getServer().getPluginManager().registerEvents(listenerIn, this);
    }

    /**
     * Gets the plugin instance
     * @return the plugin instance or null if not yet initialized
     */
    public static Kurosis getInstance() {
        return instance;
    }
}

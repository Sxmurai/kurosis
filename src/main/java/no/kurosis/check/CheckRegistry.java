package no.kurosis.check;

import no.kurosis.Kurosis;
import no.kurosis.check.aim.AimA;
import no.kurosis.check.aim.AimB;
import no.kurosis.check.aim.AimC;
import no.kurosis.check.autoclicker.AutoClickerA;
import no.kurosis.check.invalid.InvalidA;
import no.kurosis.check.invalid.InvalidB;
import no.kurosis.check.inventory.*;
import no.kurosis.check.move.MoveA;
import no.kurosis.check.place.PlaceA;
import no.kurosis.check.place.PlaceB;
import no.kurosis.check.timer.TimerA;
import no.kurosis.data.PlayerData;
import no.kurosis.registry.ListRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Contains all of the anti-cheats checks for each player
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class CheckRegistry extends ListRegistry<Class<? extends Check>> {

    private final Map<UUID, List<Check>> checkMap = new HashMap<>();

    public CheckRegistry() {
        registry.add(AimA.class);
        registry.add(AimB.class);
        registry.add(AimC.class);

        registry.add(AutoClickerA.class);

        registry.add(InvalidA.class);
        registry.add(InvalidB.class);

        registry.add(InventoryA.class);
        registry.add(InventoryB.class);
        registry.add(InventoryC.class);
        registry.add(InventoryD.class);
        registry.add(InventoryE.class);

        registry.add(MoveA.class);

        registry.add(PlaceA.class);
        registry.add(PlaceB.class);

        registry.add(TimerA.class);
    }

    public void registerChecks(UUID uuid) {
        PlayerData playerData = Kurosis.getInstance().getDataRegistry().get(uuid);
        if (playerData != null) {

            List<Check> checks = new ArrayList<>();
            for (Class<? extends Check> clazz : registry) {
                try {
                    checks.add((Check) clazz.getConstructors()[0].newInstance(playerData));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            checks.forEach(Kurosis.BUS::subscribe);
            checkMap.put(uuid, checks);
        }
    }

    public void unregisterChecks(UUID uuid) {
        List<Check> checks = checkMap.getOrDefault(uuid, null);
        if (checks != null || checks.isEmpty()) {
            return;
        }

        checks.forEach(Kurosis.BUS::unsubscribe);
        checkMap.remove(uuid);
    }

}

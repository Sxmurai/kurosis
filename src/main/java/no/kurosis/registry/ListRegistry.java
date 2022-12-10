package no.kurosis.registry;

import java.util.ArrayList;
import java.util.List;

/**
 * A registry with a list based off the type parameter
 * @param <T> the type to hold in this registry
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class ListRegistry<T> {

    protected final List<T> registry = new ArrayList<>();

    /**
     * Gets the existing entries in this registry
     * @return the list of entries
     */
    public List<T> get() {
        return registry;
    }

}

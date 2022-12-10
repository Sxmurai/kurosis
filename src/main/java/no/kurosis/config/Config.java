package no.kurosis.config;

import java.util.HashMap;
import java.util.Map;

public class Config {

    private final Map<String, Object> entries = new HashMap<>();

    // lol
    public <T> T get(String location, T defaultValue) {
        String[] props = location.split("\\.");
        Object at = null;
        for (String prop : props) {
            if (at instanceof Map) {
                at = ((Map<?, ?>) at).getOrDefault(prop, null);
            } else {
                at = entries.getOrDefault(prop, null);
            }

            if (at == null) {
                break;
            }
        }

        return at == null ? defaultValue : (T) at;
    }

}

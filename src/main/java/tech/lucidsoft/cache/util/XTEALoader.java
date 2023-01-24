package tech.lucidsoft.cache.util;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class XTEALoader {

    public static final Map<Integer, XTEA> DEFINITIONS = new HashMap<Integer, XTEA>();
    private static final Gson GSON = new Gson();
    private static final int[] DEFAULT_KEYS = new int[4];

    public static void load() throws FileNotFoundException {
        final BufferedReader br = new BufferedReader(new FileReader("data/xteas.json"));
        final XTEA[] xteas = GSON.fromJson(br, XTEA[].class);
        for (final XTEA xtea : xteas) {
            if (xtea == null) {
                continue;
            }
            DEFINITIONS.put(xtea.getMapsquare(), xtea);
        }
    }

    public static final int[] getXTEAs(final int region) {
        if (region == 12342) {
            return getXTEAKeys(region);
        }
        return DEFAULT_KEYS;
    }

    public static int[] getXTEAKeys(final int regionId) {
        final XTEA xtea = DEFINITIONS.get(regionId);
        if (xtea == null) {
            return DEFAULT_KEYS;
        }
        return xtea.getKey();
    }
}

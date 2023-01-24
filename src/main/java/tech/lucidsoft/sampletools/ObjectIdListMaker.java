package tech.lucidsoft.sampletools;

import tech.lucidsoft.cache.definitions.ObjectDefinition;
import tech.lucidsoft.cache.definitions.managers.ObjectManager;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.util.DefUtil;
import java.io.IOException;
import java.util.*;

public class ObjectIdListMaker {

    private List<String> takenNames = new ArrayList<>();
    private HashMap<Integer, String> idNames = new HashMap<>();
    private HashMap<Integer, String> nameOverrides = new HashMap<>();

    private String cachePath;
    private String dumpPath;

    private Cache cache;
    private ObjectManager objectManager;

    public static void main(String[] args) {
        new ObjectIdListMaker(DefUtil.LIST_MAKER_ZENYTE_ARGS);
    }

    private ObjectIdListMaker(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient argument length. Arguments are: [cache input path], [dump output path]");
        }
        cachePath = args[0];
        dumpPath = args[1];
        cache = Cache.openCache(cachePath);

        loadObjectManager(cache);

        loadOverrides();

        restructureDefinitionNameScheme();

        try {
            DefUtil.createListFile("Objects", "com.zenyte.game.world.object", dumpPath, idNames);
        } catch (IOException e) {
            System.out.println("Unable to create Objects.java file");
            e.printStackTrace();
        }

        cache.close();
    }

    private void loadObjectManager(Cache cache) {
        objectManager = new ObjectManager(cache);
        objectManager.setVerbose(true);
        objectManager.load();
    }

    private void put(int id, String name) {
        nameOverrides.put(id, name);
    }

    private void restructureDefinitionNameScheme() {
        for (ObjectDefinition def : objectManager.getDefinitions().values()) {
            String name = def.getName();
            if (name.isEmpty()) {
                name = "null";
            } else if (Character.isDigit(name.charAt(0)) || name.charAt(0) == '$') {
                name = "_" + name;
            }

            if (nameOverrides.containsKey(def.getId())) {
                idNames.put(def.getId(), nameOverrides.get(def.getId()));
                takenNames.add(nameOverrides.get(def.getId()));
            } else {
                if (!name.equals("null")) {
                    String sanitized = DefUtil.removeTags(name).toUpperCase().replace(' ', '_').replaceAll("[^a-zA-Z0-9_]", "");
                    if(!nameOverrides.containsValue(sanitized) && !takenNames.contains(sanitized)) {
                        idNames.put(def.getId(), sanitized);
                        takenNames.add(sanitized);
                    } else {
                        sanitized += "_" + def.getId();
                        idNames.put(def.getId(), sanitized);
                    }
                }
            }
        }
    }

    private void loadOverrides() {
        //prevent it from being used without the id at the end
        put(-1, "NULL");

        //Custom replacements to make development easier
        put(11700, "VENOM_CLOUD");

        put(20790, "SOS_ENTRANCE");

        put(26364, "SARADOMIN_ALTAR");
        put(26371, "ROPE_26371");
        put(26375, "ROPE_26375");
        put(26561, "ROPE_26561");
        put(26562, "ROPE_26562");

        put(31669, "CLOYSTER_BELL");
        put(31681, "GG_ENTRANCE");
        put(31686, "ENERGY_SPHERE_31686");
        put(31687, "ENERGY_SPHERE_31687");
        put(31688, "ENERGY_SPHERE_31688");

        put(32534, "BRYOPHYTAS_ENTRANCE");
        put(32535, "BRYOPHYTAS_EXIT");

        put(34555, "ALCHEMICAL_DOOR_G"); //Graphical representations of the door
        put(34556, "ALCHEMICAL_DOOR_G2"); // ^

        put(34628, "LITHKRIN_MACHINERY");
        put(34662, "BRIMSTONE_CHEST");
    }
}

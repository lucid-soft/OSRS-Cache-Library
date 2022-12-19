package tech.lucidsoft.sampletools;

import tech.lucidsoft.cache.definitions.NpcDefinition;
import tech.lucidsoft.cache.definitions.managers.NpcManager;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.util.DefUtil;
import java.util.*;

public class NpcIdListMaker {

    private List<String> takenNames = new ArrayList<>();
    private HashMap<Integer, String> idNames = new HashMap<>();
    private HashMap<Integer, String> nameOverrides = new HashMap<>();

    private String cachePath;
    private String dumpPath;

    private Cache cache;
    private NpcManager npcManager;

    public static void main(String[] args) {
        new NpcIdListMaker(DefUtil.LIST_MAKER_ZENYTE_ARGS);
    }

    private NpcIdListMaker(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient argument length. Arguments are: [cache input path], [dump output path]");
        }
        cachePath = args[0];
        dumpPath = args[1];
        cache = Cache.openCache(cachePath);

        loadNpcManager(cache);

        loadOverrides();

        restructureDefinitionNameScheme();

        try {
            DefUtil.createListFile("Npcs", "com.zenyte.game.world.entity.npc", dumpPath, idNames);
        } catch (Exception e) {
            System.out.println("Unable to create Objects.java file");
            e.printStackTrace();
        }

        cache.close();
    }

    private void loadNpcManager(Cache cache) {
        npcManager = new NpcManager(cache);
        npcManager.setVerbose(true);
        npcManager.load();
    }

    private void put(int id, String name) {
        nameOverrides.put(id, name);
    }

    private void restructureDefinitionNameScheme() {
        for (NpcDefinition def : npcManager.getDefinitions().values()) {
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
        put(1476, "DEAD_CHOMPY_BIRD");
        put(1613, "SNARE_TRAP_ACTIVE");
        put(1618, "SNARE_TRAP_INACTIVE");
        put(1633, "BOX_TRAP_ACTIVE");
        put(1634, "BOX_TRAP_INACTIVE");
        put(3292, "DEADFALL_TRAP_ACTIVE");
        put(3293, "DEADFALL_TRAP_INACTIVE");
        put(3294, "NET_TRAP_ACTIVE");
        put(3295, "NET_TRAP_INACTIVE");
        put(3296, "PITFALL_TRAP_ACTIVE");
        put(3297, "PITFALL_TRAP_INACTIVE");

        put(5863, "CERBERUS_SLEEPING");
    }
}


package tech.lucidsoft.sampletools;

import tech.lucidsoft.cache.definitions.ItemDefinition;
import tech.lucidsoft.cache.definitions.managers.ItemManager;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.util.DefUtil;

import java.util.*;

public class ItemIdListMaker {

    private List<String> takenNames = new ArrayList<>();
    private HashMap<Integer, String> idNames = new HashMap<>();
    private HashMap<Integer, String> nameOverrides = new HashMap<>();

    private String cachePath;
    private String dumpPath;

    private Cache cache;
    private ItemManager itemManager;

    public static void main(String[] args) {
        new ItemIdListMaker(DefUtil.LIST_MAKER_ZENYTE_ARGS);
    }

    private ItemIdListMaker(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient argument length. Arguments are: [cache input path], [dump output path]");
        }
        cachePath = args[0];
        dumpPath = args[1];
        cache = Cache.openCache(cachePath);

        loadItemManager(cache);

        loadOverrides();

        restructureDefinitionNameScheme();

        try {
            DefUtil.createListFile("Items", "com.zenyte.game.item", dumpPath, idNames);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cache.close();
    }

    private void loadItemManager(Cache cache) {
        itemManager = new ItemManager(cache);
        itemManager.setVerbose(false);
        itemManager.load();
    }

    private void put(int id, String name) {
        nameOverrides.put(id, name);
    }

    private void restructureDefinitionNameScheme() {
        for (ItemDefinition def : itemManager.getDefinitions().values()) {
            String name = def.getName();
            if (name.equals("null")) {
                if (def.getPlaceholderId() != -1) {
                    name = itemManager.getItemDef(def.getPlaceholderId()).getName() + " placeholder";
                } else if (def.getNotedId() != -1) {
                    name = itemManager.getItemDef(def.getNotedId()).getName() + " noted";
                }
            }

            if (name.isEmpty()) {
                name = "null";
            } else if (Character.isDigit(name.charAt(0)) || name.charAt(0) == '$') {
                name = "_" + name;
            }

            if (nameOverrides.containsKey(def.getId())) {
                idNames.put(def.getId(), nameOverrides.get(def.getId()));
                takenNames.add(nameOverrides.get(def.getId()));
            } else {
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

    private void loadOverrides() {
        //prevent it from being used without the id at the end
        put(-1, "NULL");


        //Custom replacements to make development easier
        put(995, "COINS");

        put(24782, "AMULET_OF_BLOOD_FURY_75");
        put(24784, "AMULET_OF_BLOOD_FURY_50");
        put(24786, "AMULET_OF_BLOOD_FURY_25");
        put(24788, "AMULET_OF_BLOOD_FURY_100");
    }
}

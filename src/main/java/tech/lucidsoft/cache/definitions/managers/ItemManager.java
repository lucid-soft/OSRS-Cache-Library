package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.ItemDefinition;
import tech.lucidsoft.cache.definitions.exporters.ItemExporter;
import tech.lucidsoft.cache.definitions.loaders.ItemLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;
import tech.lucidsoft.cache.util.DefUtil;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private final Group itemDefGroup;
    private static final Map<Integer, ItemDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;

    public ItemManager(Cache cache) {
        this.itemDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.ITEM);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Item Definitions...");
        }

        ItemLoader loader = new ItemLoader();
        for (File f : itemDefGroup.getFiles()) {
            ItemDefinition def = loader.load(f.getID(), f.getData());
            definitions.put(f.getID(), def);
        }

        if (isVerbose()) {
            System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Item definitions.");
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Item JSON to: " + directory.getPath());
        }
        exportToJson(getItemDef(id), directory);
    }

    public void exportToJson(ItemDefinition def, java.io.File directory) {
        directory.mkdirs();

        ItemExporter exporter = new ItemExporter(def);
        String cleansedName = DefUtil.cleanseItemName(def.getName(), def.getName().equals("null") && def.getNotedId() != -1 ? getItemDef(def.getNotedId()).getName() : def.getName(),
                def.getName().equals("null") && def.getPlaceholderId() != -1 ? getItemDef(def.getPlaceholderId()).getName() : "null");
        try {
            exporter.exportToJson(directory, def.getId() + "_" + cleansedName + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Item JSONs to: " + directory.getPath());
        }
        for (ItemDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, ItemDefinition> getDefinitions() {
        return definitions;
    }

    public ItemDefinition getItemDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        ItemManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }
}

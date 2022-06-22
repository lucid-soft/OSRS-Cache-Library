package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.InventoryDefinition;
import tech.lucidsoft.cache.definitions.exporters.InventoryExporter;
import tech.lucidsoft.cache.definitions.loaders.InventoryLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private final Group inventoryDefGroup;
    private static final Map<Integer, InventoryDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;
    private static boolean verboseDefinitions = false;

    public InventoryManager(Cache cache) {
        this.inventoryDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.INV);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Inventory Definitions...");
        }

        InventoryLoader loader = new InventoryLoader();
        for (File f : inventoryDefGroup.getFiles()) {
            InventoryDefinition def = loader.load(f.getID(), f.getData());
            definitions.put(f.getID(), def);
        }

        if (isVerbose()) {
            System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Inventory definitions.");
        }
    }

    public void exportToToml(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Inventory TOML to: " + directory.getPath());
        }
        exportToToml(getInventoryDef(id), directory);
    }

    public void exportToToml(InventoryDefinition def, java.io.File directory) {
        directory.mkdirs();

        InventoryExporter exporter = new InventoryExporter(def);
        try {
            exporter.exportToToml(directory, + def.getId() + ".toml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToToml(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Inventory TOMLs to: " + directory.getPath());
        }
        for (InventoryDefinition def : definitions.values()) {
            exportToToml(def, directory);
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Inventory JSON to: " + directory.getPath());
        }
        exportToJson(getInventoryDef(id), directory);
    }

    public void exportToJson(InventoryDefinition def, java.io.File directory) {
        directory.mkdirs();

        InventoryExporter exporter = new InventoryExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Inventory JSONs to: " + directory.getPath());
        }
        for (InventoryDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, InventoryDefinition> getDefinitions() {
        return definitions;
    }

    public InventoryDefinition getInventoryDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        InventoryManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }

    /**
     *
     * @param verboseDefinitions Will print the definition to console if set to true
     */
    public void setVerboseDefinitions(boolean verboseDefinitions) {
        InventoryManager.verboseDefinitions = verboseDefinitions;
    }

    public static boolean isVerboseDefinitions() {
        return verboseDefinitions;
    }
}

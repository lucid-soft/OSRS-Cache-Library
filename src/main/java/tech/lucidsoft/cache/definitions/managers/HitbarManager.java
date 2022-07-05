package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.HitbarDefinition;
import tech.lucidsoft.cache.definitions.exporters.HitbarExporter;
import tech.lucidsoft.cache.definitions.loaders.HitbarLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class HitbarManager {

    private final Group hitbarDefGroup;
    private static final Map<Integer, HitbarDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;

    public HitbarManager(Cache cache) {
        this.hitbarDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.HITBAR);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Hitbar Definitions...");

            HitbarLoader loader = new HitbarLoader();
            for (File f : hitbarDefGroup.getFiles()) {
                HitbarDefinition def = loader.load(f.getID(), f.getData());
                definitions.put(f.getID(), def);
            }

            if(isVerbose()) {
                System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Hitbar definitions.");
            }
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Hitbar JSON to: " + directory.getPath());
        }
        exportToJson(getHitbarDef(id), directory);
    }

    public void exportToJson(HitbarDefinition def, java.io.File directory) {
        directory.mkdirs();

        HitbarExporter exporter = new HitbarExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Hitbar JSONs to: " + directory.getPath());
        }
        for (HitbarDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, HitbarDefinition> getDefinitions() {
        return definitions;
    }

    public HitbarDefinition getHitbarDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        HitbarManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }
}

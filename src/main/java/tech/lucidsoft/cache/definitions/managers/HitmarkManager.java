package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.HitmarkDefinition;
import tech.lucidsoft.cache.definitions.exporters.HitmarkExporter;
import tech.lucidsoft.cache.definitions.loaders.HitmarkLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class HitmarkManager {

    private final Group HitmarkDefGroup;
    private static final Map<Integer, HitmarkDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;

    public HitmarkManager(Cache cache) {
        this.HitmarkDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.HITMARK);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Hitmark Definitions...");

            HitmarkLoader loader = new HitmarkLoader();
            for (File f : HitmarkDefGroup.getFiles()) {
                HitmarkDefinition def = loader.load(f.getID(), f.getData());
                definitions.put(f.getID(), def);
            }

            if(isVerbose()) {
                System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Hitmark definitions.");
            }
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Hitmark JSON to: " + directory.getPath());
        }
        exportToJson(getHitmarkDef(id), directory);
    }

    public void exportToJson(HitmarkDefinition def, java.io.File directory) {
        directory.mkdirs();

        HitmarkExporter exporter = new HitmarkExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Hitmark JSONs to: " + directory.getPath());
        }
        for (HitmarkDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, HitmarkDefinition> getDefinitions() {
        return definitions;
    }

    public HitmarkDefinition getHitmarkDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        HitmarkManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }
}

package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.SpotanimDefinition;
import tech.lucidsoft.cache.definitions.exporters.SpotanimExporter;
import tech.lucidsoft.cache.definitions.loaders.SpotanimLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class SpotanimManager {

    private final Group spotanimDefGroup;
    private static final Map<Integer, SpotanimDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;

    public SpotanimManager(Cache cache) {
        this.spotanimDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.SPOTANIM);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Spotanim Definitions...");
        }

        SpotanimLoader loader = new SpotanimLoader();
        for (File f : spotanimDefGroup.getFiles()) {
            SpotanimDefinition def = loader.load(f.getID(), f.getData());
            definitions.put(f.getID(), def);
        }

        if (isVerbose()) {
            System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Spotanim definitions.");
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Spotanim JSON to: " + directory.getPath());
        }
        exportToJson(getSpotanimDef(id), directory);
    }

    public void exportToJson(SpotanimDefinition def, java.io.File directory) {
        directory.mkdirs();

        SpotanimExporter exporter = new SpotanimExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Spotanim JSONs to: " + directory.getPath());
        }
        for (SpotanimDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, SpotanimDefinition> getDefinitions() {
        return definitions;
    }

    public SpotanimDefinition getSpotanimDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        SpotanimManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }
}

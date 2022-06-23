package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.IdentikitDefinition;
import tech.lucidsoft.cache.definitions.exporters.IdentikitExporter;
import tech.lucidsoft.cache.definitions.loaders.IdentikitLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class IdentikitManager {

    private final Group identikitDefGroup;
    private static final Map<Integer, IdentikitDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;

    public IdentikitManager(Cache cache) {
        this.identikitDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.IDENTIKIT);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Identikit Definitions...");
        }

        IdentikitLoader loader = new IdentikitLoader();
        for (File f : identikitDefGroup.getFiles()) {
            IdentikitDefinition def = loader.load(f.getID(), f.getData());
            definitions.put(f.getID(), def);
        }

        if (isVerbose()) {
            System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Identikit definitions.");
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Identikit JSON to: " + directory.getPath());
        }
        exportToJson(getIdentikitDef(id), directory);
    }

    public void exportToJson(IdentikitDefinition def, java.io.File directory) {
        directory.mkdirs();

        IdentikitExporter exporter = new IdentikitExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Identikit JSONs to: " + directory.getPath());
        }
        for (IdentikitDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, IdentikitDefinition> getDefinitions() {
        return definitions;
    }

    public IdentikitDefinition getIdentikitDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        IdentikitManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }
}

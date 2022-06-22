package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.SequenceDefinition;
import tech.lucidsoft.cache.definitions.exporters.SequenceExporter;
import tech.lucidsoft.cache.definitions.loaders.SequenceLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class SequenceManager {

    private final Group sequenceDefGroup;
    private static final Map<Integer, SequenceDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;
    private static boolean verboseDefinitions = false;

    public SequenceManager(Cache cache) {
        this.sequenceDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.SEQUENCE);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Sequence Definitions...");
        }

        SequenceLoader loader = new SequenceLoader();
        for (File f : sequenceDefGroup.getFiles()) {
            SequenceDefinition def = loader.load(f.getID(), f.getData());
            definitions.put(f.getID(), def);
        }

        if (isVerbose()) {
            System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Sequence definitions.");
        }
    }

    public void exportToToml(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Sequence TOML to: " + directory.getPath());
        }
        exportToToml(getSequenceDef(id), directory);
    }

    public void exportToToml(SequenceDefinition def, java.io.File directory) {
        directory.mkdirs();

        SequenceExporter exporter = new SequenceExporter(def);
        try {
            exporter.exportToToml(directory, + def.getId() + ".toml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToToml(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Sequence TOMLs to: " + directory.getPath());
        }
        for (SequenceDefinition def : definitions.values()) {
            exportToToml(def, directory);
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Sequence JSON to: " + directory.getPath());
        }
        exportToJson(getSequenceDef(id), directory);
    }

    public void exportToJson(SequenceDefinition def, java.io.File directory) {
        directory.mkdirs();

        SequenceExporter exporter = new SequenceExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Sequence JSONs to: " + directory.getPath());
        }
        for (SequenceDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, SequenceDefinition> getDefinitions() {
        return definitions;
    }

    public SequenceDefinition getSequenceDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        SequenceManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }

    /**
     *
     * @param verboseDefinitions Will print the definition to console if set to true
     */
    public void setVerboseDefinitions(boolean verboseDefinitions) {
        SequenceManager.verboseDefinitions = verboseDefinitions;
    }

    public static boolean isVerboseDefinitions() {
        return verboseDefinitions;
    }
}

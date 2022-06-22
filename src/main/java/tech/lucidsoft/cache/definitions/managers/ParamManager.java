package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.ParamDefinition;
import tech.lucidsoft.cache.definitions.exporters.ParamExporter;
import tech.lucidsoft.cache.definitions.loaders.ParamLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class ParamManager {

    private final Group paramDefGroup;
    private static final Map<Integer, ParamDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;
    private static boolean verboseDefinitions = false;

    public ParamManager(Cache cache) {
        this.paramDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.PARAMS);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Param Definitions...");

            ParamLoader loader = new ParamLoader();
            for (File f : paramDefGroup.getFiles()) {
                ParamDefinition def = loader.load(f.getID(), f.getData());
                definitions.put(f.getID(), def);
            }

            if(isVerbose()) {
                System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Param definitions.");
            }
        }
    }

    public void exportToToml(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Param TOML to: " + directory.getPath());
        }
        exportToToml(getParamDef(id), directory);
    }

    public void exportToToml(ParamDefinition def, java.io.File directory) {
        directory.mkdirs();

        ParamExporter exporter = new ParamExporter(def);
        try {
            exporter.exportToToml(directory, + def.getId() + ".toml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToToml(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Param TOMLs to: " + directory.getPath());
        }
        for (ParamDefinition def : definitions.values()) {
            exportToToml(def, directory);
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Param JSON to: " + directory.getPath());
        }
        exportToJson(getParamDef(id), directory);
    }

    public void exportToJson(ParamDefinition def, java.io.File directory) {
        directory.mkdirs();

        ParamExporter exporter = new ParamExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Param JSONs to: " + directory.getPath());
        }
        for (ParamDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, ParamDefinition> getDefinitions() {
        return definitions;
    }

    public ParamDefinition getParamDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        ParamManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }

    /**
     *
     * @param verboseDefinitions Will print the definition to console if set to true
     */
    public void setVerboseDefinitions(boolean verboseDefinitions) {
        ParamManager.verboseDefinitions = verboseDefinitions;
    }

    public static boolean isVerboseDefinitions() {
        return verboseDefinitions;
    }
}

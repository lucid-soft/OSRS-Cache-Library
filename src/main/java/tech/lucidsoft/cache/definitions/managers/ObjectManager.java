package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;
import tech.lucidsoft.cache.definitions.ObjectDefinition;
import tech.lucidsoft.cache.definitions.exporters.ObjectExporter;
import tech.lucidsoft.cache.definitions.loaders.ObjectLoader;
import tech.lucidsoft.cache.util.DefUtil;

import java.util.HashMap;
import java.util.Map;

public class ObjectManager {

    private final Group objectDefGroup;
    private final Map<Integer, ObjectDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;
    private static boolean verboseDefinitions = false;

    public ObjectManager(Cache cache) {
        this.objectDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.OBJECT);
    }

    public void load() {
        if(isVerbose()) {
            System.out.println("Loading Object Definitions...");
        }

        ObjectLoader loader = new ObjectLoader();
        for(File f : objectDefGroup.getFiles()) {
            ObjectDefinition def = loader.load(f.getID(), f.getData());
            definitions.put(f.getID(), def);
        }

        if(isVerbose()) {
            System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Object definitions.");
        }
    }

    public void exportToToml(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Object TOML to: " + directory.getPath());
        }
        exportToToml(getObjectDef(id), directory);
    }

    public void exportToToml(ObjectDefinition def, java.io.File directory) {
        directory.mkdirs();

        ObjectExporter exporter = new ObjectExporter(def);
        String cleansedName = DefUtil.cleanseName(def.getName());
        try {
            exporter.exportToToml(directory, def.getId() + "_" + cleansedName + ".toml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToToml(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Object TOMLs to: " + directory.getPath());
        }
        for(ObjectDefinition def : definitions.values()) {
            exportToToml(def, directory);
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Object JSON to: " + directory.getPath());
        }
        exportToJson(getObjectDef(id), directory);
    }

    public void exportToJson(ObjectDefinition def, java.io.File directory) {
        directory.mkdirs();

        ObjectExporter exporter = new ObjectExporter(def);
        String cleansedName = DefUtil.cleanseName(def.getName());
        try {
            exporter.exportToJson(directory, def.getId() + "_" + cleansedName + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Object JSONs to: " + directory.getPath());
        }
        for(ObjectDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, ObjectDefinition> getDefinitions() {
        return definitions;
    }

    public ObjectDefinition getObjectDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        ObjectManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }

    /**
     *
     * @param verboseDefinitions Will print the definition to console if set to true
     */
    public void setVerboseDefinitions(boolean verboseDefinitions) {
        ObjectManager.verboseDefinitions = verboseDefinitions;
    }

    public static boolean isVerboseDefinitions() {
        return verboseDefinitions;
    }
}

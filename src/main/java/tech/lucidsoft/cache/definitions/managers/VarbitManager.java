package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.VarbitDefinition;
import tech.lucidsoft.cache.definitions.exporters.VarbitExporter;
import tech.lucidsoft.cache.definitions.loaders.VarbitLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class VarbitManager {

    private final Group varbitDefGroup;
    private final Map<Integer, VarbitDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;

    public VarbitManager(Cache cache) {
        this.varbitDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.VARBIT);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Varbit Definitions...");
        }

        VarbitLoader loader = new VarbitLoader();
        for (File f : varbitDefGroup.getFiles()) {
            VarbitDefinition def = loader.load(f.getID(), f.getData());
            definitions.put(f.getID(), def);
        }

        if (isVerbose()) {
            System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Varbit definitions.");
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Varbit JSON to: " + directory.getPath());
        }
        exportToJson(getVarbitDef(id), directory);
    }

    public void exportToJson(VarbitDefinition def, java.io.File directory) {
        directory.mkdirs();

        VarbitExporter exporter = new VarbitExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Varbit JSONs to: " + directory.getPath());
        }
        for (VarbitDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, VarbitDefinition> getDefinitions() {
        return definitions;
    }

    public VarbitDefinition getVarbitDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        VarbitManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }
}

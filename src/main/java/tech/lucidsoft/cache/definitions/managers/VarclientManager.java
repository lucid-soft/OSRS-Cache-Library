package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.VarclientDefinition;
import tech.lucidsoft.cache.definitions.exporters.VarclientExporter;
import tech.lucidsoft.cache.definitions.loaders.VarclientLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class VarclientManager {

    private final Group varclientDefGroup;
    private static final Map<Integer, VarclientDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;

    public VarclientManager(Cache cache) {
        this.varclientDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.VARCLIENT);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Varclient Definitions...");

            VarclientLoader loader = new VarclientLoader();
            for (File f : varclientDefGroup.getFiles()) {
                VarclientDefinition def = loader.load(f.getID(), f.getData());
                definitions.put(f.getID(), def);
            }

            if(isVerbose()) {
                System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Varclient definitions.");
            }
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Varclient JSON to: " + directory.getPath());
        }
        exportToJson(getVarclientDef(id), directory);
    }

    public void exportToJson(VarclientDefinition def, java.io.File directory) {
        directory.mkdirs();

        VarclientExporter exporter = new VarclientExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Varclient JSONs to: " + directory.getPath());
        }
        for (VarclientDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }

    public Map<Integer, VarclientDefinition> getDefinitions() {
        return definitions;
    }

    public VarclientDefinition getVarclientDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        VarclientManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }
}

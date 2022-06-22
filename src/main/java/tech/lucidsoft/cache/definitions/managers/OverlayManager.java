package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.GroupType;
import tech.lucidsoft.cache.definitions.OverlayDefinition;
import tech.lucidsoft.cache.definitions.exporters.OverlayExporter;
import tech.lucidsoft.cache.definitions.loaders.OverlayLoader;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.File;
import tech.lucidsoft.cache.filesystem.Group;

import java.util.HashMap;
import java.util.Map;

public class OverlayManager {

    private final Group overlayDefGroup;
    private static final Map<Integer, OverlayDefinition> definitions = new HashMap<>();
    private static boolean verbose = false;
    private static boolean verboseDefinitions = false;

    public OverlayManager(Cache cache) {
        this.overlayDefGroup = cache.getArchive(ArchiveType.CONFIGS).findGroupByID(GroupType.OVERLAY);
    }

    public void load() {
        if (isVerbose()) {
            System.out.println("Loading Overlay Definitions...");
        }

        OverlayLoader loader = new OverlayLoader();
        for (File f : overlayDefGroup.getFiles()) {
            OverlayDefinition def = loader.load(f.getID(), f.getData());
            definitions.put(f.getID(), def);
        }

        if (isVerbose()) {
            System.out.println("Loaded " + String.format( "%,d", definitions.size()) + " Overlay definitions.");
        }
    }

    public void exportToToml(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Overlay TOML to: " + directory.getPath());
        }
        exportToToml(getOverlayDef(id), directory);
    }

    public void exportToToml(OverlayDefinition def, java.io.File directory) {
        directory.mkdirs();

        OverlayExporter exporter = new OverlayExporter(def);
        try {
            exporter.exportToToml(directory, + def.getId() + ".toml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToToml(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Overlay TOMLs to: " + directory.getPath());
        }
        for (OverlayDefinition def : definitions.values()) {
            exportToToml(def, directory);
        }
    }

    public void exportToJson(int id, java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Overlay JSON to: " + directory.getPath());
        }
        exportToJson(getOverlayDef(id), directory);
    }

    public void exportToJson(OverlayDefinition def, java.io.File directory) {
        directory.mkdirs();

        OverlayExporter exporter = new OverlayExporter(def);
        try {
            exporter.exportToJson(directory, def.getId() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportAllToJson(java.io.File directory) {
        if(isVerbose()) {
            System.out.println("Exporting Overlay JSONs to: " + directory.getPath());
        }
        for (OverlayDefinition def : definitions.values()) {
            exportToJson(def, directory);
        }
    }


    public Map<Integer, OverlayDefinition> getDefinitions() {
        return definitions;
    }

    public OverlayDefinition getOverlayDef(int id) {
        return definitions.get(id);
    }

    public void setVerbose(boolean verbose) {
        OverlayManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }

    /**
     *
     * @param verboseDefinitions Will print the definition to console if set to true
     */
    public void setVerboseDefinitions(boolean verboseDefinitions) {
        OverlayManager.verboseDefinitions = verboseDefinitions;
    }

    public static boolean isVerboseDefinitions() {
        return verboseDefinitions;
    }
}

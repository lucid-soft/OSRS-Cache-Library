package tech.lucidsoft.cache.dumper;

import tech.lucidsoft.cache.definitions.ItemDefinition;
import tech.lucidsoft.cache.definitions.NpcDefinition;
import tech.lucidsoft.cache.definitions.ObjectDefinition;
import tech.lucidsoft.cache.definitions.managers.*;
import tech.lucidsoft.cache.filesystem.Cache;

import java.io.File;

/**
 *  This is an example of the power of the cache library's dumping capabilities. This library is intended to be used as
 *  a dependency to create your own dumps instead of being an all-in-one dumping tool.
 */
public class MegaDumper {

    private static String cachePath = "./data/cache/";

    private NpcManager npcManager;
    private ObjectManager objectManager;
    private ItemManager itemManager;
    private EnumManager enumManager;
    private UnderlayManager underlayManager;

    private ModelManager modelManager;

    private long startTime = -1L;


    public static void main(String[] args) {
        if (System.getProperty("cache") != null) {
            cachePath = System.getProperty("cache");
        } else if (args.length > 0) {
            cachePath = args[0];
        }

        new MegaDumper();
    }

    public MegaDumper() {
        startTime = System.currentTimeMillis();
        Cache cache = Cache.openCache(cachePath);

        loadObjectDefinitions(cache);
        loadNpcDefinitions(cache);
        loadItemDefinitions(cache);
        loadEnumDefinitions(cache);
        loadUnderlayDefinitions(cache);

        loadModels(cache);

        objectManager.exportAllToToml(new File("dumps/toml/objects/"));
        objectManager.exportAllToJson(new File("dumps/json/objects/"));

        npcManager.exportAllToToml(new File("dumps/toml/npc/"));
        npcManager.exportAllToJson(new File("dumps/json/npc/"));

        itemManager.exportAllToToml(new File("dumps/toml/items/"));
        itemManager.exportAllToJson(new File("dumps/json/items/"));

        enumManager.exportAllToToml(new File("dumps/toml/enums/"));
        enumManager.exportAllToJson(new File("dumps/json/enums/"));

        underlayManager.exportAllToToml(new File("dumps/toml/underlays/"));
        underlayManager.exportAllToJson(new File("dumps/json/underlays/"));

        dumpingExamples();
        dumpObjectModels();
        dumpNpcModels();
        dumpItemModels();

        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;
        System.out.println("Dumping complete! Took: " + String.format("%,.2f", (float)diff / 1000) + " seconds");
    }

    private void dumpingExamples() {
        // Dumps just one model
        // modelManager.dumpModel(1, new File("dumps/models/"), "1");

        // Dumps all models
        System.out.println("Dumping all models to: " + "dumps/models/");
        for (int i = 0; i < modelManager.getModels().length; i++) {
            modelManager.dumpModel(i, new File("dumps/models/"), i + "");
        }

        // Dumps one object model
        // modelManager.dumpObjectModels(objectManager.getObjectDef(1), "dumps/objects/");
    }

    public void loadObjectDefinitions(Cache cache) {
        objectManager = new ObjectManager(cache);
        objectManager.setVerbose(true);
        // objectManager.setVerboseDefinitions(true);
        objectManager.load();
    }

    public void loadNpcDefinitions(Cache cache) {
        npcManager = new NpcManager(cache);
        npcManager.setVerbose(true);
        // npcManager.setVerboseDefinitions(true);
        npcManager.load();
    }

    public void loadItemDefinitions(Cache cache) {
        itemManager = new ItemManager(cache);
        itemManager.setVerbose(true);
        // itemManager.setVerboseDefinitions(true);
        itemManager.load();
    }

    public void loadEnumDefinitions(Cache cache) {
        enumManager = new EnumManager(cache);
        enumManager.setVerbose(true);
        // enumManager.setVerboseDefinitions(true);
        enumManager.load();
    }

    public void loadUnderlayDefinitions(Cache cache) {
        underlayManager = new UnderlayManager(cache);
        underlayManager.setVerbose(true);
        // underlayManager.setVerboseDefinitions(true);
        underlayManager.load();
    }

    public void loadModels(Cache cache) {
        modelManager = new ModelManager(cache);
        modelManager.setVerbose(true);
        modelManager.load();
    }

    public void dumpObjectModels() {
        // Dumps all object models
        System.out.println("Dumping Object Models...");
        for (ObjectDefinition def : objectManager.getDefinitions().values()) {
            modelManager.dumpObjectModels(def, "dumps/objects/models/");
        }
    }

    public void dumpNpcModels() {
        // Dumps all npc models
        System.out.println("Dumping NPC Models...");
        for (NpcDefinition def : npcManager.getDefinitions().values()) {
            modelManager.dumpNpcModels(def, "dumps/npcs/models/");
        }
    }

    public void dumpItemModels() {
        // Dumps all item models
        System.out.println("Dumping Item Models...");
        for (ItemDefinition def : itemManager.getDefinitions().values()) {
            modelManager.dumpItemModels(itemManager, def, "dumps/items/models/");
        }
    }
}

package tech.lucidsoft.cache.dumper;

import tech.lucidsoft.cache.definitions.ItemDefinition;
import tech.lucidsoft.cache.definitions.NpcDefinition;
import tech.lucidsoft.cache.definitions.ObjectDefinition;
import tech.lucidsoft.cache.definitions.managers.ItemManager;
import tech.lucidsoft.cache.definitions.managers.ModelManager;
import tech.lucidsoft.cache.definitions.managers.NpcManager;
import tech.lucidsoft.cache.definitions.managers.ObjectManager;
import tech.lucidsoft.cache.filesystem.Cache;

import java.io.File;

/**
 *  This is an example of the power of the cache library's dumping capabilities. This library is intended to be used as
 *  a dependency to create your own dumps instead of being an all-in-one dumping tool.
 */
public class MegaDumper {

    private static String cachePath = "./data/cache/";
    private static int revision = 1;

    private NpcManager npcManager;
    private ObjectManager objectManager;
    private ItemManager itemManager;
    private ModelManager modelManager;

    private long startTime = -1L;


    public static void main(String[] args) {
        if (System.getProperty("cache") != null) {
            cachePath = System.getProperty("cache");
        } else if (args.length > 0) {
            cachePath = args[0];
        }

        if (System.getProperty("revision") != null) {
            revision = Integer.parseInt(System.getProperty("revision"));
        } else if (args.length > 1) {
            revision = Integer.parseInt(args[1]);
        }

        new MegaDumper();
    }

    public MegaDumper() {
        startTime = System.currentTimeMillis();
        Cache cache = Cache.openCache(cachePath, revision);

        objectManager = new ObjectManager(cache);
        objectManager.setVerbose(true);
        // objectManager.setVerboseDefinitions(true);
        objectManager.load();

        objectManager.exportAllToToml(new File("dumps/objects/toml/"));
        objectManager.exportAllToJson(new File("dumps/objects/json/"));

        npcManager = new NpcManager(cache);
        npcManager.setVerbose(true);
        // npcManager.setVerboseDefinitions(true);
        npcManager.load();

        npcManager.exportAllToToml(new File("dumps/npcs/toml/"));
        npcManager.exportAllToJson(new File("dumps/npcs/json/"));

        itemManager = new ItemManager(cache);
        itemManager.setVerbose(true);
        // itemManager.setVerboseDefinitions(true);
        itemManager.load();

        itemManager.exportAllToToml(new File("dumps/items/toml/"));
        itemManager.exportAllToJson(new File("dumps/items/json/"));

        modelManager = new ModelManager(cache);
        modelManager.setVerbose(true);
        modelManager.load();

        // Dumps just one model
        // modelManager.dumpModel(1, new File("dumps/models/"), "1");

        // Dumps all models
        System.out.println("Dumping all models to: " + "dumps/models/");
        for (int i = 0; i < modelManager.getModels().length; i++) {
            modelManager.dumpModel(i, new File("dumps/models/"), i + "");
        }

        // Dumps one object model
        // modelManager.dumpObjectModels(objectManager.getObjectDef(1), "dumps/objects/");

        // Dumps all object models
        System.out.println("Dumping Object Models...");
        for (ObjectDefinition def : objectManager.getDefinitions().values()) {
            modelManager.dumpObjectModels(def, "dumps/objects/models/");
        }

        // Dumps all npc models
        System.out.println("Dumping NPC Models...");
        for (NpcDefinition def : npcManager.getDefinitions().values()) {
            modelManager.dumpNpcModels(def, "dumps/npcs/models/");
        }

        // Dumps all item models
        System.out.println("Dumping Item Models...");
        for (ItemDefinition def : itemManager.getDefinitions().values()) {
            modelManager.dumpItemModels(itemManager, def, "dumps/items/models/");
        }
        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;
        System.out.println("Dumping complete! Took: " + String.format("%,.2f", diff / 1000) + " seconds");
    }
}

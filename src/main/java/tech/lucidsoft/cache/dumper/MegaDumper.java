package tech.lucidsoft.cache.dumper;

import tech.lucidsoft.cache.definitions.NpcDefinition;
import tech.lucidsoft.cache.definitions.ObjectDefinition;
import tech.lucidsoft.cache.definitions.managers.ModelManager;
import tech.lucidsoft.cache.definitions.managers.NpcManager;
import tech.lucidsoft.cache.definitions.managers.ObjectManager;
import tech.lucidsoft.cache.filesystem.Cache;

import java.io.File;

public class MegaDumper {

    private static String cachePath = "./data/cache/";
    private static int revision = 1;

    private NpcManager npcManager;
    private ObjectManager objectManager;
    private ModelManager modelManager;

    public static void main(String[] args) {
        if(System.getProperty("cache") != null) {
            cachePath = System.getProperty("cache");
        } else if(args.length > 0) {
            cachePath = args[0];
        }

        if(System.getProperty("revision") != null) {
            revision = Integer.parseInt(System.getProperty("revision"));
        } else if(args.length > 1) {
            revision = Integer.parseInt(args[1]);
        }

        new MegaDumper();
    }

    public MegaDumper() {
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

        modelManager = new ModelManager(cache);
        modelManager.setVerbose(true);
        modelManager.load();

        // Dumps just one model
        // modelManager.dumpModel(1, new File("dumps/models/"), "1");

        // Dumps all models
        for (int i = 0; i < modelManager.getModels().length; i++) {
            modelManager.dumpModel(i, new File("dumps/models/"), i + "");
        }

        // Dumps one object model
        // modelManager.dumpObjectModels(objectManager.getObjectDef(1), "dumps/objects/");

        // Dumps all object models
        for(ObjectDefinition def : objectManager.getDefinitions().values()) {
            System.out.println("Dumping models for object ID: " + def.getId() + " Name: " + def.getName());
            modelManager.dumpObjectModels(def, "dumps/objects/models/");
        }

        for(NpcDefinition def : npcManager.getDefinitions().values()) {
            System.out.println("Dumping models for NPC ID: " + def.getId() + " Name: " + def.getName());
            modelManager.dumpNpcModels(def, "dumps/npcs/models/");
        }
    }
}

# OSRS Cache Library
A cache library and data dumping tool for the Old School RuneScape cache files.
It currently supports reading definitions up to revision 206.

## Code Examples

To dump object configs:

        Cache cache = Cache.openCache(cachePath, revision); // Revision is used for definition exporting purposes
        objectManager = new ObjectManager(cache); 
        objectManager.setVerbose(true); // This will set the program to send some info to the console while dumping
        objectManager.setVerboseDefinitions(true); //Same as above but only pertaining to printing definitions
        objectManager.load(); // Loads the definitions into memory

        objectManager.exportAllToToml(new File("dumps/objects/toml/")); // Dumps object configs to toml format
        objectManager.exportAllToJson(new File("dumps/objects/json/")); // Dumps object configs to json format
        
To dump models:

        Cache cache = Cache.openCache(cachePath, revision); // Revision is used for definition exporting purposes
        modelManager = new ModelManager(cache);
        modelManager.setVerbose(true);
        modelManager.load(); // Loads models into memory
        
        // Dumps just one model
        modelManager.dumpModel(1, new File("dumps/models/"), "1");

        // Dumps all models
        for (int i = 0; i < modelManager.getModels().length; i++) {
            modelManager.dumpModel(i, new File("dumps/models/"), i + "");
        }

        // Dumps one object's models
        modelManager.dumpObjectModels(objectManager.getObjectDef(1), "dumps/objects/");
        
                
## To-Do List:

    - Add other config dumpers
    - Add better CLI support
    - Maybe add a GUI

### Credits
    - Graham (mostly his cache library)
    - Adam from RuneLite (using loader/exporter/manger structure for dumping)

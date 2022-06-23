package tech.lucidsoft.cache.definitions.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.InventoryDefinition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InventoryExporter {

    private final InventoryDefinition def;
    private final Gson gson;

    public InventoryExporter(InventoryDefinition inventoryDefinition) {
        this.def = inventoryDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
    }

    public void exportToJson(File directory, String filename) throws IOException {
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileWriter fw = new FileWriter(new File(directory, filename))) {
            fw.write(gson.toJson(def));
        }
    }
}

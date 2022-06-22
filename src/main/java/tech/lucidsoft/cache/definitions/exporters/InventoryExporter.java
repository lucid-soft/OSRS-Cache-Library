package tech.lucidsoft.cache.definitions.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.InventoryDefinition;
import tech.lucidsoft.cache.definitions.managers.InventoryManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static tech.lucidsoft.cache.util.DefUtil.newline;

public class InventoryExporter {

    private final InventoryDefinition def;
    private final Gson gson;
    private final String toml;

    public InventoryExporter(InventoryDefinition inventoryDefinition) {
        this.def = inventoryDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
        toml = defAsToml();
    }

    private String defAsToml() {
        String out = "";
        out += "[[inventory]]" + newline();
        out += "id = " + def.getId() + newline();
        out += "size = " + def.getSize() + newline();

        if (InventoryManager.isVerboseDefinitions()) {
            System.out.println(out);
        }

        return out;
    }

    public String getTomlString() {
        return toml;
    }

    public void exportToToml(File directory, String filename) throws IOException {
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileWriter fw = new FileWriter(new File(directory, filename))) {
            fw.write(getTomlString());
        }
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

package tech.lucidsoft.cache.definitions.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.OverlayDefinition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static tech.lucidsoft.cache.util.DefUtil.newline;

public class OverlayExporter {

    private final OverlayDefinition def;
    private final Gson gson;
    private final String toml;

    public OverlayExporter(OverlayDefinition overlayDefinition) {
        this.def = overlayDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
        toml = defAsToml();
    }

    public String defAsToml() {
        String out = "";
        out += "[[overlay]]" + newline();
        out += format("id", def.getId());
        out += format("colour", def.getColour());
        out += format("texture", def.getTexture());
        out += format("hideunderlay", def.isHideUnderlay());
        out += format("secondarycolour", def.getSecondaryColour());
        return out;
    }

    private String format(String memberName, Object member) {
        String out = "";
        if (member instanceof Integer) {
            out += memberName + " = " + (int) member + newline();
        } else if (member instanceof Boolean) {
            out += memberName + " = " + ((boolean) member == true ? 1 : 0) + newline();
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

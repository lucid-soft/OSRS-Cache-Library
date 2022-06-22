package tech.lucidsoft.cache.definitions.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.ParamDefinition;
import tech.lucidsoft.cache.definitions.managers.ParamManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static tech.lucidsoft.cache.util.DefUtil.newline;

public class ParamExporter {

    private ParamDefinition def;
    private final Gson gson;
    private final String toml;

    public ParamExporter(ParamDefinition paramDefinition) {
        this.def = paramDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
        toml = defAsToml();
    }

    private String defAsToml() {
        String out = "";
        out += "[[param]]" + newline();
        out += "id = " + def.getId() + newline();
        out += "stacktype = " + def.getStackType() + newline();
        out += "defaultint = " + def.getDefaultInt() + newline();
        out += "defaultstring = " + def.getDefaultString() + newline();
        out += "autodisable = "+  (def.isAutoDisable() ? 1 : 0) + newline();
        if(ParamManager.isVerboseDefinitions()) {
            System.out.println(def);
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

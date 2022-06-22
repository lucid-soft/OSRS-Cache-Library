package tech.lucidsoft.cache.definitions.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.IdentikitDefinition;
import tech.lucidsoft.cache.definitions.managers.IdentikitManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static tech.lucidsoft.cache.util.DefUtil.newline;

public class IdentikitExporter {

    private IdentikitDefinition def;
    private final Gson gson;
    private final String toml;

    public IdentikitExporter(IdentikitDefinition identikitDefinition) {
        this.def = identikitDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
        toml = defAsToml();
    }

    private String defAsToml() {
        String out = "";
        out += "[[identikit]]" + newline();
        out += format("id", def.getId());
        out += format("originalcolours", def.getOriginalColours());
        out += format("replacementcolours", def.getReplacementColours());
        out += format("originaltextures", def.getOriginalTextures());
        out += format("replacementtextures", def.getReplacementTextures());
        out += format("bodypartid", def.getBodyPartId());
        out += format("models", def.getModels());
        out += format("chatheadmodels", def.getChatheadModels());
        out += format("nonselectable", def.isNonSelectable());

        if (IdentikitManager.isVerboseDefinitions()) {
            System.out.println(def);
        }

        return out;
    }

    public String format(String memberName, Object member) {
        String out = "";
        if(member == null) {
            if(memberName.equals("originalcolours") || memberName.equals("replacementcolours") ||
                    memberName.equals("originaltextures") || memberName.equals("replacementtextures")) {
                member = new short[0];
            } else if(memberName.equals("models") || memberName.equals("chatheadModels")) {
                member = new int[0];
            }
        }
        if(member instanceof Integer) {
            out += memberName + " = " + ((int)member + newline());
        } else if (member instanceof int[]) {
            out += memberName + " = [ ";
            if (member != null) {
                int[] casted = (int[]) member;
                for (int i = 0; i < casted.length; i++) {
                    out += casted[i];
                    if (i != casted.length - 1) {
                        out += ", ";
                    }
                }
            }
            out += " ]" + newline();
        } else if (member instanceof short[]) {
            out += memberName + " = [ ";
            if (member != null) {
                short[] casted = (short[]) member;
                for (int i = 0; i < casted.length; i++) {
                    out += casted[i];
                    if (i != casted.length - 1) {
                        out += ", ";
                    }
                }
            }
            out += "]" + newline();
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

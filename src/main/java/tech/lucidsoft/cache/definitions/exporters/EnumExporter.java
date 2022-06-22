package tech.lucidsoft.cache.definitions.exporters;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.EnumDefinition;
import tech.lucidsoft.cache.definitions.managers.EnumManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static tech.lucidsoft.cache.util.DefUtil.newline;

public class EnumExporter {

    public static final Map<Character, String> TYPE_MAP = ImmutableMap.<Character, String>builder().put('A', "seq").put('i', "int")
            .put('1', "boolean").put('s', "string").put('v', "inv").put('z', "char").put('O', "namedobj").put('M', "midi").put('K', "idkit")
            .put('o', "obj").put('n', "npc").put('c', "coordgrid").put('S', "stat").put('m', "model").put('d', "graphic").put('J', "struct")
            .put('f', "fontmetrics").put('I', "component").put('k', "chatchar").put('g', "enum").put('l', "location").put((char)0xB5, "mapelement").build();

    public static final ImmutableMap<String, Character> REVERSE_TYPE_MAP =
            ImmutableMap.<String, Character>builder().putAll(TYPE_MAP.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey))).build();

    private EnumDefinition def;
    private final Gson gson;
    private final String toml;

    public EnumExporter(EnumDefinition enumDefinition) {
        this.def = enumDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
        toml = defAsToml();
    }

    private String defAsToml() {
        String out = "";
        out += "[[enum]]" + newline();
        out += format("id", def.getId());
        out += format("defaultstring", def.getDefaultString());
        out += format("defaultint", def.getDefaultInt());
        out += format("size", def.getSize());
        out += format("keytype", def.getKeyType());
        out += format("valuetype", def.getValueType());
        out += format("values", def.getValues());

        if (EnumManager.isVerboseDefinitions()) {
            System.out.println(out);
        }

        return out;
    }

    private String format(String memberName, Object member) {
        String out = "";
        if (member == null) {
            if (memberName.equals("values")) {
                member = new HashMap<Integer, Object>();
            }
        }
        if (member instanceof Integer) {
            out += memberName + " = " + (int) member + newline();
        } else if (member instanceof String) {
            out += memberName + " = " + "\"" + member + "\"" + newline();
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
            out += " ]" + newline();
        } else if (member instanceof String[]) {
            out += memberName + " = [ ";
            if (member != null) {
                String[] casted = (String[]) member;
                for (int i = 0; i < casted.length; i++) {
                    out += "\"" + (casted[i].equals("null") ? "" : casted) + "\"";
                    if (i != casted.length - 1) {
                        out += ", ";
                    }
                }
            }
            out += " ]" + newline();
        } else if (member instanceof Boolean) {
            out += memberName + " = " + ((boolean) member == true ? 1 : 0) + newline();
        } else if (member instanceof Character) {
            if(memberName.equals("keytype")) {
                out += memberName + " = \"" + TYPE_MAP.get(def.getKeyType()) + "\"" + newline();
            }
            if(memberName.equals("valuetype")) {
                out += memberName + " = \"" + TYPE_MAP.get(def.getValueType()) + "\"" + newline();
            }
        }

        else if (member instanceof Map) {
            HashMap<Integer, Object> parameters = (HashMap<Integer, Object>) member;
            out += "values = { ";
            int count = 0;
            if(parameters.size() > 0) {
                for (Map.Entry<Integer, Object> entry : parameters.entrySet()) {
                    out += entry.getKey().intValue() + " = " + (entry.getValue() instanceof String ? "\"" + entry.getValue() + "\"" : (int) entry.getValue());
                    if(count < parameters.size() - 1) {
                        out += ", ";
                    }
                    count++;
                }
            }
            out += " }" + newline();
        } else {
            System.out.println("Unrecognized type for member: " + memberName + "type=" + member != null ? member.getClass() : "-null object-");
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

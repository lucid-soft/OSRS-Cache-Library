package tech.lucidsoft.cache.definitions.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.managers.NpcManager;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.definitions.NpcDefinition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static tech.lucidsoft.cache.util.DefUtil.newline;

public class NpcExporter {

    private NpcDefinition def;
    private final Gson gson;
    private final String toml;

    public NpcExporter(NpcDefinition npcDefinition) {
        this.def = npcDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
        toml = defAsToml();
    }

    private String defAsToml() {
        String out = "";
        out += "[[npc]]" + newline();
        out += format("id", def.getId());
        out += format("name", def.getName());
        if (Cache.getCacheRevision() > 199) {
            out += format("category", def.getCategory());
        } else {
            System.out.println("Ignoring category due to Cache.cacheRevision being set to" + Cache.getCacheRevision() + ".");
            System.out.println("Category was introduced in revision 199.");
        }
        out += format("options", def.getOptions());
        out += format("varp", def.getVarp());
        out += format("varbit", def.getVarbit());
        out += format("transmogrifiedids", def.getTransmogrifiedIds());
        out += format("models", def.getModels());
        out += format("chatmodels", def.getChatModels());
        out += format("standanimation", def.getStandAnimation());
        out += format("walkanimation", def.getWalkAnimation());
        out += format("rotate90animation", def.getRotate90Animation());
        out += format("rotate180animation", def.getRotate180Animation());
        out += format("rotate270animation", def.getRotate270Animation());
        out += format("size", def.getSize());
        out += format("combatlevel", def.getCombatLevel());
        out += format("minimapvisible", def.isMinimapVisible());
        out += format("visible", def.isVisible());
        out += format("clickable", def.isClickable());
        out += format("clippedmovement", def.isClippedMovement());
        out += format("familiar", def.isFamiliar());
        out += format("resizex", def.getResizeX());
        out += format("resizey", def.getResizeY());
        out += format("direction", def.getDirection());
        out += format("headicon", def.getHeadIcon());
        out += format("ambience", def.getAmbience());
        out += format("contrast", def.getContrast());
        out += format("originalcolours", def.getOriginalColours());
        out += format("replacementcolours", def.getReplacementColours());
        out += format("originaltextures", def.getOriginalTextures());
        out += format("replacementtextures", def.getReplacementTextures());
        out += format("field3568", def.getField3568());
        out += format("field3580", def.getField3580());
        out += format("finaltransmogrification", def.getFinalTransmogrification());
        out += format("parameters", def.getParameters());

        if(NpcManager.isVerboseDefinitions()) {
            System.out.println(out);
        }

        return out;
    }

    private String format(String memberName, Object member) {
        String out = "";
        if (member == null) {
            if (memberName.equals("parameters")) {
                member = new HashMap<Integer, Object>();
            } else if (memberName.equals("models") || memberName.equals("transmogrifiedids") || memberName.equals("chatmodels") ||
                    memberName.equals("originalcolours") || memberName.equals("replacementcolours") || memberName.equals("originaltextures") ||
                    memberName.equals("replacementtextures")) {
                member = new int[0];
            } else if (memberName.equals("options")) {
                member = new String[]{null, null, "Take", null, null};
            }
        }
        if (member instanceof Integer) {
            out += memberName + "=" + (int) member + newline();
        } else if (member instanceof String) {
            out += memberName + "=" + "\"" + member + "\"" + newline();
        } else if (member instanceof int[]) {
            out += memberName + "=[";
            if (member != null) {
                int[] casted = (int[]) member;
                for (int i = 0; i < casted.length; i++) {
                    out += casted[i];
                    if (i != casted.length - 1) {
                        out += ", ";
                    }
                }
            }
            out += "]" + newline();
        } else if (member instanceof short[]) {
            out += memberName + "=[";
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
        } else if (member instanceof String[]) {
            out += memberName + "=[";
            if (member != null) {
                String[] casted = (String[]) member;
                for (int i = 0; i < casted.length; i++) {
                    out += "\"" + casted[i] + "\"";
                    if (i != casted.length - 1) {
                        out += ", ";
                    }
                }
            }
            out += "]" + newline();
        } else if (member instanceof Boolean) {
            out += memberName + "=" + ((boolean) member == true ? 1 : 0) + newline();
        } else if (member instanceof Map) {
            HashMap<Integer, Object> parameters = (HashMap<Integer, Object>) member;
            out += newline();
            out += "[parameters.values]" + newline();
            if(parameters.size() > 0) {
                for (Map.Entry<Integer, Object> entry : parameters.entrySet()) {
                    out += entry.getKey().intValue() + "=" + (entry.getValue() instanceof String ? "\"" + entry.getValue() + "\"" : (int) entry.getValue()) + newline();
                }
            } else {
                out += "clear = true" + newline();
            }
            out += newline();
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

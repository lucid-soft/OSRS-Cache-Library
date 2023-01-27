package tech.lucidsoft.cache.definitions.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.ItemDefinition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static tech.lucidsoft.cache.util.DefUtil.newline;

public class ItemExporter {

    private ItemDefinition def;
    private final Gson gson;

    private String toml;

    public ItemExporter(ItemDefinition itemDefinition) {
        this.def = itemDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
        toml = defAsToml();
    }

    public void exportToJson(File directory, String filename) throws IOException {
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileWriter fw = new FileWriter(new File(directory, filename))) {
            fw.write(gson.toJson(def));
        }
    }

    private String defAsToml() {
        String out = "";
        out += "[[item]]" + newline();
        out += format("id", def.getId());
        out += format("name", def.getName());
        out += format("ops" , def.getInventoryOptions());
        out += format("groundops" , def.getGroundOptions());
        out += format("members" , def.isMembers());
        out += format("grandexchange" , def.isGrandExchange());
        out += format("stackable" , def.getIsStackable());
        out += format("price" , def.getPrice());
        out += format("notedtemplate" , def.getNotedTemplate());
        out += format("notedid" , def.getNotedId());
        out += format("bindtemplateid" , def.getBindTemplateId());
        out += format("bindid" , def.getBindId());
        out += format("placeholdertemplate" , def.getPlaceholderTemplate());
        out += format("placeholderid" , def.getPlaceholderId());
        out += format("maleoffset" , def.getMaleOffset());
        out += format("primarymaleheadmodelid" , def.getPrimaryMaleHeadModelId());
        out += format("secondarymaleheadmodelid" , def.getSecondaryMaleHeadModelId());
        out += format("primarymalemodel" , def.getPrimaryMaleModel());
        out += format("secondarymalemodel" , def.getSecondaryMaleModel());
        out += format("tertiarymalemodel" , def.getTertiaryMaleModel());
        out += format("femaleoffset" , def.getFemaleOffset());
        out += format("primaryfemaleheadmodelid" , def.getPrimaryFemaleHeadModelId());
        out += format("secondaryfemaleheadmodelid" , def.getSecondaryFemaleHeadModelId());
        out += format("primaryfemalemodel" , def.getPrimaryFemaleModel());
        out += format("secondaryfemalemodel" , def.getSecondaryFemaleModel());
        out += format("tertiaryfemalemodel" , def.getTertiaryFemaleModel());
        out += format("invmodel", def.getInventoryModelId());
        out += format("shiftclickindex", def.getShiftClickIndex());
        out += format("teamid", def.getTeamId());
        out += format("zoom", def.getZoom());
        out += format("offsetx", def.getOffsetX());
        out += format("offsety", def.getOffsetY());
        out += format("modelpitch", def.getModelPitch());
        out += format("modelroll", def.getModelRoll());
        out += format("modelyaw", def.getModelYaw());
        out += format("resizex", def.getResizeX());
        out += format("resizey", def.getResizeY());
        out += format("resizez", def.getResizeZ());
        out += format("ambient", def.getAmbient());
        out += format("contrast", def.getContrast());
        out += format("parameters", def.getParameters());
        out += format("weight", def.getWeight());
        out += format("originalcolours", def.getOriginalColours());
        out += format("replacementcolours", def.getReplacementColours());
        out += format("originaltextures", def.getOriginalTextures());
        out += format("replacementtextures", def.getReplacementTextures());
        out += format("category" , def.getCategory());
        out += format("stackids" , def.getStackIds());
        out += format("stackamounts" , def.getStackAmounts());

        return out;
    }

    private String format(String memberName, Object member) {
        String out = "";
        if (member == null) {
            if (memberName.equals("parameters")) {
                member = new HashMap<Integer, Object>();
            } else if (memberName.equals("stackids") || memberName.equals("stackamounts") || memberName.equals("originalcolours")
                    || memberName.equals("replacementcolours") || memberName.equals("originaltextures") || memberName.equals("replacementtextures")) {
                member = new int[0];
            } else if (memberName.equals("groundops")) {
                member = new String[]{null, null, "Take", null, null};
            } else if (memberName.equals("ops")) {
                member = new String[]{null, null, null, null, "Drop"};
            }
        }
        if (member instanceof Integer) {
            out += memberName + "=" + (int) member + newline();
        } else if (member instanceof String) {
            out += memberName + "=" + "\"" + member + "\"" + newline();
        } else if (member instanceof int[]) {
            if(((int[]) member).length > 0) {
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
                out += " ]" + newline();
            }
        } else if (member instanceof short[]) {
            if(((short[]) member).length > 0) {
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
            }
        } else if (member instanceof String[]) {
            out += memberName + "=[";
            if (member != null) {
                String[] casted = (String[]) member;
                for (int i = 0; i < casted.length; i++) {
                    out += "\"" + (casted[i] != null ? casted[i] : "") + "\"";
                    if (i != casted.length - 1) {
                        out += ", ";
                    }
                }
            }
            out += "]" + newline();
        } else if (member instanceof Boolean) {
            out += memberName + "=" + ((boolean) member == true ? "true" : "false") + newline();
        } else if (member instanceof Map) {
            HashMap<Integer, Object> parameters = (HashMap<Integer, Object>) member;
            out += "parameters={";
            int cnt = 0;
            if(parameters.size() > 0) {
                for (Map.Entry<Integer, Object> entry : parameters.entrySet()) {
                    out += entry.getKey().intValue() + " = " + (entry.getValue() instanceof String ? "\"" + entry.getValue() + "\"" : (int) entry.getValue()) + (cnt == parameters.size() - 1 ?  "}" : ", ");
                }
            } else {
                out += "clear = true" + "}";
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
}

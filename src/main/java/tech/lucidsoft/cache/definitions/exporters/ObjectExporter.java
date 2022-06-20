package tech.lucidsoft.cache.definitions.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.managers.ObjectManager;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.definitions.ObjectDefinition;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static tech.lucidsoft.cache.util.DefUtil.newline;

public class ObjectExporter {

    private final ObjectDefinition def;
    private final Gson gson;
    private final String toml;

    public ObjectExporter(ObjectDefinition objectDefinition) {
        this.def = objectDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
        toml = defAsToml();
    }

    private String defAsToml() {
        String out = "";
        out += "[[object]]" + newline();
        out += format("id", def.getId());
        out += format("category", def.getCategory());
        out += format("name", def.getName());
        out += format("varbit", def.getVarbit());
        out += format("optionsinvisible", def.getOptionsInvisible());
        out += format("models", def.getModels());
        out += format("types", def.getTypes());
        out += format("transformedids", def.getTransformedIds());
        out += format("ambientsoundid", def.getAmbientSoundId());
        out += format("varp", def.getVarp());
        out += format("supportitems", def.getSupportItems());
        out += format("anintarray100", def.getAnIntArray100());
        out += format("mapiconid", def.getMapIconId());
        out += format("sizex", def.getSizeX());
        out += format("cliptype", def.getClipType());
        out += format("rotated", def.isRotated());
        out += format("sizey", def.getSizeY());
        out += format("projectileclip", def.isProjectileClip());
        out += format("anint455", def.getAnInt455());
        out += format("nonflatshading", def.isNonFlatShading());
        out += format("contouredground", def.getContouredGround());
        out += format("anint456", def.getAnInt456());
        out += format("modelclipped", def.isModelClipped());
        out += format("ambient", def.getAmbient());
        out += format("options", def.getOptions());
        out += format("contrast", def.getContrast());
        out += format("anint457", def.getAnInt457());
        out += format("hollow", def.isHollow());
        out += format("animationid", def.getAnimationId());
        out += format("modelsizex", def.getModelSizeX());
        out += format("decordisplacement", def.getDecorDisplacement());
        out += format("modelsizeheight", def.getModelSizeHeight());
        out += format("modelsizey", def.getModelSizeY());
        out += format("modelcolours", def.getModelColours());
        out += format("clipped", def.isClipped());
        out += format("modeltexture", def.getModelTexture());
        out += format("mapsceneid", def.getMapSceneId());
        out += format("replacementcolours", def.getReplacementColours());
        out += format("offsetx", def.getOffsetX());
        out += format("replacementtextures", def.getReplacementTextures());
        out += format("offsetheight", def.getOffsetHeight());
        out += format("offsety", def.getOffsetY());
        out += format("obstructsground", def.isObstructsGround());
        out += format("accessblockflag", def.getAccessBlockFlag());
        out += format("finaltransformation", def.getFinalTransformation());
        out += format("randomizedanimstart", def.isRandomizedAnimStart());
        out += format("parameters", def.getParameters());

        if(ObjectManager.isVerboseDefinitions()) {
            System.out.println(out);
        }

        return out;
    }

    private String format(String memberName, Object member) {
        String out = "";
        if (member == null) {
            if (memberName.equals("parameters")) {
                member = new HashMap<Integer, Object>();
            } else if (memberName.equals("models") || memberName.equals("types") || memberName.equals("transformedids") ||
                    memberName.equals("anintarray100") || memberName.equals("modelcolours") || memberName.equals("modeltexture") ||
                    memberName.equals("replacementcolours") || memberName.equals("replacementtextures")) {
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
        }else if (member instanceof String[]) {
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
            System.out.println("Unrecognized type for member: " + memberName + " type=" + (member != null ? member.getClass() : "-null object-"));
        }
        return out;
    }

    public String getTomlString() {
        return toml;
    }

    public void exportToToml(File directory, String filename) throws  IOException {
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
package tech.lucidsoft.cache.definitions.exporters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.lucidsoft.cache.definitions.SequenceDefinition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static tech.lucidsoft.cache.util.DefUtil.newline;

public class SequenceExporter {

    private SequenceDefinition def;
    private final Gson gson;
    private final String toml;

    public SequenceExporter(SequenceDefinition sequenceDefinition) {
        this.def = sequenceDefinition;
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        gson = builder.create();
        toml = defAsToml();
    }

    private String defAsToml() {
        String out = "";
        out += "[[animation]]" + newline();
        out += format("id", def.getId());
        out += format("frameids", def.getFrameIds());
        out += format("framelengths", def.getFrameLengths());
        out += format("extraframeids", def.getChatFrameIds());
        out += format("framesounds", def.getFrameSounds());
        out += format("framestep", def.getFrameStep());
        out += format("mergedbonegroups", def.getInterleaves());
        out += format("stretches", def.isStretches());
        out += format("forcedpriority", def.getForcedPriority());
        out += format("lefthanditem", def.getLeftHandItem());
        out += format("righthanditem", def.getRightHandItem());
        out += format("iterations", def.getMaxLoops());
        out += format("precedenceanimating", def.getPrecedenceAnimating());
        out += format("priority", def.getPriority());
        out += format("replymode", def.getReplyMode());

        return out;
    }

    public String format(String memberName, Object member) {
        String out = "";
        if (member == null) {
            if (memberName.equals("frameids") || memberName.equals("extraframeids") ||
                    memberName.equals("framelengths") || memberName.equals("framesounds") || memberName.equals("mergedbonegroups")) {
                member = new int[0];
            }
        }

        if (member instanceof Integer) {
            out += memberName + "=" + ((int)member + newline());
        } else if (member instanceof int[]) {
            out += memberName + "=[";
            int[] casted = (int[]) member;
            for (int i = 0; i < casted.length; i++) {
                out += casted[i];
                if (i != casted.length - 1) {
                    out += ", ";
                }
            }
            out += "]" + newline();
        } else if (member instanceof Boolean) {
            out += memberName + "=" + ((boolean) member == true ? "true" : "false") + newline();
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

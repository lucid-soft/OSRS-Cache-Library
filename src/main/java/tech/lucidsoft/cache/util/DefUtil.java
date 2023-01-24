package tech.lucidsoft.cache.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class DefUtil {

    public static final String LIST_MAKER_LATEST_ARGS[] = new String[] {"./data/cache2102/", "./data/dumps/2102/ids/"};
    public static final String LIST_MAKER_ZENYTE_ARGS[] = new String[] {"./data/zencache/", "./data/dumps/zen/ids/"};
    public static final String MAP_EXTRACTOR_ZENYTE_ARGS[] = new String[] {"./data/zencache/", "./data/dumps/zen/maps/"};

    public static final String MAP_EXTRACTOR_210_ARGS[] = new String[] {"./data/cache2102/", "./data/dumps/cache2102/maps/"};

    /**
     *
     * @param name Name of the entity
     * @return a file-system-friendly String that removes things like color tags
     *   and turns other undesirable characters into underscores
     */
    public static String cleanseName(String name) {
        String cleansed = name;
        if(name.contains("<col")) {
            cleansed = cleansed.substring(name.indexOf("<col") + 12);
        }
        cleansed = cleansed.replace("</col>", "");
        cleansed = cleansed.replace(" ", "_");
        cleansed = cleansed.replace(",", "");
        cleansed = cleansed.replace(".", "");
        cleansed = cleansed.replace("/", "_");
        cleansed = cleansed.replace("?", "_");
        cleansed = cleansed.toLowerCase();
        return cleansed;
    }

    /**
     *
     * @param name Name of the entity
     * @return a file-system-friendly String that removes things like color tags
     *   and turns other undesirable characters into underscores
     */
    public static String cleanseItemName(String name, String notedName, String placeholderName) {
        String beforeName = name;
        if(name.equals("null") && !placeholderName.equals("null")) {
            beforeName = placeholderName + " (placeholder)";
        }
        if(name.equals("null") && !notedName.equals("null")) {
            beforeName = notedName + " (noted)";
        }
        String cleansed = beforeName;
        if(name.contains("<col")) {
            cleansed = cleansed.substring(name.indexOf("<col") + 12);
        }
        cleansed = cleansed.replace("</col>", "");
        cleansed = cleansed.replace(" ", "_");
        cleansed = cleansed.replace(",", "");
        cleansed = cleansed.replace(".", "");
        cleansed = cleansed.replace("/", "_");
        cleansed = cleansed.replace("?", "_");
        cleansed = cleansed.toLowerCase();
        return cleansed;
    }

    public static String newline() {
        return "\r\n";
    }


    public static String removeTags(String str) {
        StringBuilder builder = new StringBuilder(str.length());
        boolean inTag = false;

        for (int i = 0; i < str.length(); i++)
        {
            char currentChar = str.charAt(i);

            if (currentChar == '<')
            {
                inTag = true;
            }
            else if (currentChar == '>')
            {
                inTag = false;
            }
            else if (!inTag)
            {
                builder.append(currentChar);
            }
        }

        return builder.toString();
    }

    /**
    *   Used to create ID lists for Objects/Npcs. Items requires a custom function to include note/placeholder items.
     */
    public static void createListFile(String fileName, String packageName, String dumpPath, HashMap<Integer, String> idNames) throws IOException {
        File dir = new File(dumpPath);
        dir.mkdirs();
        File file = new File(dumpPath, fileName + ".java");
        FileWriter fw = new FileWriter(file);
        fw.write("package " + packageName + ";");
        fw.write("\n");
        fw.write("\n");
        fw.write("public final class " + fileName + " {\n");
        fw.write("\n");
        List<Integer> intList = new ArrayList<>(idNames.keySet());
        intList.sort(Comparator.comparingInt(o -> o));
        for (int id : intList) {
            fw.write("	public static final int " + idNames.get(id) + " = " + id + ";" + "\n");
        }
        fw.write("}");
        fw.close();
    }

    public static void dumpData(byte[] data, String dir, String filename) {
        System.out.println("Dumping data to: " + dir + filename + ".dat");
        File directory = new File(dir);
        java.io.File datFile = null;
        try {
            if(!directory.exists()) {
                directory.mkdirs();
            }
            datFile = new java.io.File(directory, filename + ".dat");
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(datFile));
            dos.write(data);
        } catch (Exception e) {
            System.err.println("Error while writing file: " + (datFile != null ? datFile.getPath() : "null file"));
            e.printStackTrace();
            return;
        }
    }

}

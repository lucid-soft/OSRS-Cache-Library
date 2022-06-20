package tech.lucidsoft.cache.util;

import tech.lucidsoft.cache.definitions.ItemDefinition;

public class DefUtil {

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
}

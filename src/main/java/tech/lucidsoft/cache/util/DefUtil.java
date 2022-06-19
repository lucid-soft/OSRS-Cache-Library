package tech.lucidsoft.cache.util;

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
        cleansed = cleansed.replace("/", "_");
        cleansed = cleansed.replace("?", "_");
        cleansed = cleansed.toLowerCase();
        return cleansed;
    }
}

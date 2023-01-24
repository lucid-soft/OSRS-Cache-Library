package tech.lucidsoft.cache.util;

public class XTEA {

    private int mapsquare;
    private int[] key;
    private String name;
    private int name_hash;
    private int group;
    private int archive;

    public XTEA(final int region, final int[] keys) {
        this.mapsquare = region;
        this.key = keys;
    }

    public XTEA(int archive, int groupid, int nameHash, String name, final int region, final int[] keys) {
        this.archive = archive;
        this.group = groupid;
        this.name_hash = nameHash;
        this.name = name;
        this.mapsquare = region;
        this.key = keys;
    }

    public int getMapsquare() {
        return this.mapsquare;
    }

    public int[] getKey() {
        return this.key;
    }

    public String getName() {
        return this.name;
    }

    public int getName_hash() {
        return this.name_hash;
    }

    public int getGroup() {
        return this.group;
    }

    public int getArchive() {
        return this.archive;
    }
}

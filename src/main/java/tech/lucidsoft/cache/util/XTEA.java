package tech.lucidsoft.cache.util;

public class XTEA {

    private int region;
    private int[] keys;
    private String name;
    private int nameHash;
    private int groupId;
    private int archive;

    public XTEA(final int region, final int[] keys) {
        this.region = region;
        this.keys = keys;
    }

    public XTEA(int archive, int groupid, int nameHash, String name, final int region, final int[] keys) {
        this.archive = archive;
        this.groupId = groupid;
        this.nameHash = nameHash;
        this.name = name;
        this.region = region;
        this.keys = keys;
    }

    public int getRegion() {
        return this.region;
    }

    public int[] getKeys() {
        return this.keys;
    }

    public String getName() {
        return this.name;
    }

    public int getNameHash() {
        return this.nameHash;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public int getArchive() {
        return this.archive;
    }
}

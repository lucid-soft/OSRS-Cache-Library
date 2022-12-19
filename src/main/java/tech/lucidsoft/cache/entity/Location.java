package tech.lucidsoft.cache.entity;

public class Location implements Position {

    protected int hash;

    public Location(final int x, final int y, final int z) {
        hash = y | x << 14 | z << 28;
    }

    public Location(final int x, final int y) {
        this(x, y, 0);
    }

    @Override
    public Location getPosition() {
        return this;
    }

    @Override
    public String toString() {
        return "Tile: " + getX() + ", " + getY() + ", " + getPlane() + ", "
                + "region[" + getRegionId() + ", " + getRegionX() + ", " + getRegionY() + "], "
                + "chunk[" + getChunkX() + ", " + getChunkY() + "], hash [" + getPositionHash() + "]";
    }

    public int getX() {//y | x << 14 | z << 28
        return (hash >> 14) & 0x3FFF;
    }

    public int getY() {
        return hash & 0x3FFF;
    }

    public int getPlane() {
        return (hash >> 28) & 0x3;
    }

    public int getPositionHash() {
        return hash;
    }

    public int getChunkX() {
        return (getX() >> 3);
    }

    public int getChunkY() {
        return (getY() >> 3);
    }

    public int getRegionX() {
        return (getX() >> 6);
    }

    public int getRegionY() {
        return (getY() >> 6);
    }

    public int getRegionId() {
        return ((getRegionX() << 8) + getRegionY());
    }
}

package tech.lucidsoft.cache.entity.object;

import tech.lucidsoft.cache.entity.Location;

public class WorldObject extends Location {

    private int objectHash;

    public WorldObject(final int id, final int type, final int rotation, final int x, final int y, final int plane) {
        super(x, y, plane);
        objectHash = (id & 0xFFFF) | ((type & 0x1F) << 16) | ((rotation & 0x3) << 21);
    }

    public WorldObject(final int id, final int type, final int rotation, final Location tile) {
        super(tile.getX(), tile.getY(), tile.getPlane());
        objectHash = (id & 0xFFFF) | ((type & 0x1F) << 16) | ((rotation & 0x3) << 21);
    }

    public WorldObject(final WorldObject object) {
        super(object.getX(), object.getY(), object.getPlane());
        objectHash = (object.getId() & 0xFFFF) | ((object.getType() & 0x1F) << 16) | ((object.getRotation() & 0x3) << 21) | (object.isLocked() ? (1 << 24) : 0);
    }

    public int getId() {
        return objectHash & 0xFFFF;
    }

    public final int getType() {
        return (objectHash >> 16) & 0x1F;
    }

    public void setType(final int type) {
        objectHash = (getId() & 0xFFFF) | ((type & 0x1F) << 16) | ((getRotation() & 0x3) << 21);
    }

    public final int getRotation() {
        return (objectHash >> 21) & 0x3;
    }

    public void setId(final int id) {
        objectHash = (id & 0xFFFF) | ((getType() & 0x1F) << 16) | ((getRotation() & 0x3) << 21);
    }

    public void setRotation(final int rotation) {
        objectHash = (getId() & 0xFFFF) | ((getType() & 0x1F) << 16) | ((rotation & 0x3) << 21);
    }

    public boolean isLocked() {
        return ((objectHash >> 24) & 0x1) == 1;
    }

    public void setLocked(final boolean value) {
        objectHash = objectHash & 0xFFFFFF;
        if (value) {
            objectHash |= 1 << 24;
        }
    }
}

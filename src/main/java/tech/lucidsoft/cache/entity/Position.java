package tech.lucidsoft.cache.entity;

public interface Position {

    public Location getPosition();

    public default boolean matches(final Position position) {
        if (position == null) {
            return false;
        }
        return position.getPosition().getPositionHash() == getPosition().getPositionHash();
    }
}

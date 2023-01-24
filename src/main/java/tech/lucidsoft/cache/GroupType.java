package tech.lucidsoft.cache;

public enum GroupType {
    UNDERLAY(1), // Done
    IDENTIKIT(3), // Done
    OVERLAY(4), // Done
    INV(5), // Done
    OBJECT(6), // Done
    ENUM(8), // Done
    NPC(9), // Done
    ITEM(10), // Done
    PARAMS(11), // Done
    SEQUENCE(12), // Done
    SPOTANIM(13), // Done
    VARBIT(14), // Done
    VARCLIENTSTRING(15),
    VARPLAYER(16),
    VARCLIENT(19), // Done
    HITMARK(32), // Done
    HITBAR(33), // Done
    STRUCT(34),
    MAP_LABELS(35);

    GroupType(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return this.id;
    }
}

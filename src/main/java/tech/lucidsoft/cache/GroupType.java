package tech.lucidsoft.cache;

public enum GroupType
{
    UNDERLAY(1),
    IDENTKIT(3),
    OVERLAY(4),
    INV(5),
    OBJECT(6),
    ENUM(8),
    NPC(9),
    ITEM(10),
    PARAMS(11),
    SEQUENCE(12),
    SPOTANIM(13),
    VARBIT(14),
    VARCLIENTSTRING(15),
    VARPLAYER(16),
    VARCLIENT(19),
    HITMARK(32),
    HITBAR(33),
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

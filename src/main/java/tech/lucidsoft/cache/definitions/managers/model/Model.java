package tech.lucidsoft.cache.definitions.managers.model;

public class Model {

    public final int id;
    public final byte[] data;

    public Model(final int id, final byte[] data) {
        this.id = id;
        this.data = data;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getId() {
        return this.id;
    }
}

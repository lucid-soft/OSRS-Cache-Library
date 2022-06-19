package tech.lucidsoft.cache.definitions.managers.model;

public class Model {

    public int id;
    public byte[] data;

    public Model(int id, byte[] data) {
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

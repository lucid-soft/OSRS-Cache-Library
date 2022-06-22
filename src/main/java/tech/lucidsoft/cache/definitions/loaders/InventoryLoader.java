package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.InventoryDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class InventoryLoader {

    public InventoryDefinition load(int id, ByteBuffer buffer) {
        InventoryDefinition def = new InventoryDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(InventoryDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 2:
                def.setSize(buffer.readUnsignedShort());
                return;
        }
    }
}

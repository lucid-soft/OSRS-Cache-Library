package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.UnderlayDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class UnderlayLoader {

    public UnderlayDefinition load(int id, ByteBuffer buffer) {
        UnderlayDefinition def = new UnderlayDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(UnderlayDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                def.setRgb(buffer.readMedium());
                return;
        }
    }
}

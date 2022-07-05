package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.VarclientDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class VarclientLoader {

    public VarclientDefinition load(int id, ByteBuffer buffer) {
        VarclientDefinition def = new VarclientDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(VarclientDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 2:
                def.setPersists(true);
                return;
        }
    }
}

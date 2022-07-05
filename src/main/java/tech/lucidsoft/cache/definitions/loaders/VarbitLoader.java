package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.VarbitDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class VarbitLoader {

    public VarbitDefinition load(int id, ByteBuffer buffer) {
        VarbitDefinition def = new VarbitDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(VarbitDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                def.setIndex(buffer.readUnsignedShort());
                def.setLeastSignificantBit(buffer.readUnsignedByte());
                def.setMostSignificantBit(buffer.readUnsignedByte());
                return;
        }
    }
}

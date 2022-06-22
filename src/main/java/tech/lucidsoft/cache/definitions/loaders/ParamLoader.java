package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.ParamDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class ParamLoader {

    public ParamDefinition load(int id, ByteBuffer buffer) {
        ParamDefinition def = new ParamDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(ParamDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                def.setStackType(buffer.readJagexChar());
                return;
            case 2:
                def.setDefaultInt(buffer.readInt());
                return;
            case 4:
                def.setAutoDisable(false);
                return;
            case 5:
                def.setDefaultString(buffer.readString());
                return;
        }
    }
}

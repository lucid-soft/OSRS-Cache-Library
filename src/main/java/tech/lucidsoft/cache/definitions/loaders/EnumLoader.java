package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.EnumDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

import java.util.HashMap;

public class EnumLoader {

    public EnumDefinition load(int id, ByteBuffer buffer) {
        EnumDefinition def = new EnumDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(EnumDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                def.setKeyType((char) buffer.readUnsignedByte());
                return;
            case 2:
                def.setValueType((char) buffer.readUnsignedByte());
                return;
            case 3:
                def.setDefaultString(buffer.decodeString());
                return;
            case 4:
                def.setDefaultInt(buffer.readInt());
                return;
            case 5: {
                int size = buffer.readUnsignedShort();
                def.setSize(size);
                def.setValues(new HashMap<>());
                for (int index = 0; index < size; ++index) {
                    int key = buffer.readInt();
                    String value = buffer.readString();
                    def.getValues().put(key, value);
                }
                return;
            }
            case 6: {
                int size = buffer.readUnsignedShort();
                def.setSize(size);
                def.setValues(new HashMap<>());
                for (int index = 0; index < size; ++index) {
                    int key = buffer.readInt();
                    int value = buffer.readInt();
                    def.getValues().put(key, value);
                }
                return;
            }
        }
    }
}

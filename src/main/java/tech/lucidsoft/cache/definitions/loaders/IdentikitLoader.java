package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.IdentikitDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class IdentikitLoader {

    public IdentikitDefinition load(int id, ByteBuffer buffer) {
        IdentikitDefinition def = new IdentikitDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(IdentikitDefinition def, final ByteBuffer buffer, final int opcode) {
        switch(opcode) {
            case 1:
                def.setBodyPartId(buffer.readUnsignedByte());
                return;
            case 2:
                int length = buffer.readUnsignedByte();
                def.setModels(new int[length]);
                for (int i = 0; i < length; i++) {
                    def.getModels()[i] = buffer.readUnsignedShort();
                }
                return;
            case 3:
                def.setNonSelectable(true);
                return;
            case 40:
                int length2 = buffer.readUnsignedByte();
                def.setOriginalColours(new short[length2]);
                def.setReplacementColours(new short[length2]);
                for (int i = 0; i < length2; i++) {
                    def.getOriginalColours()[i] = (short) buffer.readShort();
                    def.getReplacementColours()[i] = (short) buffer.readShort();
                }
                return;
            case 41:
                int length3 = buffer.readUnsignedByte();
                def.setOriginalTextures(new short[length3]);
                def.setReplacementTextures(new short[length3]);
                for (int i = 0; i < length3; i++) {
                    def.getOriginalTextures()[i] = (short) buffer.readShort();
                    def.getReplacementTextures()[i] = (short) buffer.readShort();
                }
                return;
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
                def.getChatheadModels()[opcode - 60] = buffer.readUnsignedShort();
                return;
        }
    }
}

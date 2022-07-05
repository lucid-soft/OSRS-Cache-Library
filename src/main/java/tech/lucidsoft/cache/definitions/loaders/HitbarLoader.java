package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.HitbarDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;


public class HitbarLoader {

    public HitbarDefinition load(int id, ByteBuffer buffer) {
        HitbarDefinition def = new HitbarDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(HitbarDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                buffer.readUnsignedShort();
                return;
            case 2:
                def.setUnknownInt1(buffer.readUnsignedByte());
                return;
            case 3:
                def.setUnknownInt2(buffer.readUnsignedByte());
                return;
            case 4:
                def.setCycles(0);
                return;
            case 5:
                def.setUnknownInt3(buffer.readUnsignedShort());
                return;
            case 6:
                buffer.readUnsignedByte();
                return;
            case 7:
                def.setHealthBarFrontSpriteId(buffer.readBigSmart());
                return;
            case 8:
                def.setHealthBarBackSpriteId(buffer.readBigSmart());
                return;
            case 11:
                def.setCycles(buffer.readShort());
                return;
            case 14:
                def.setHealthScale(buffer.readUnsignedByte());
                return;
            case 15:
                def.setHealthBarPadding(buffer.readUnsignedByte());
                return;
        }
    }
}

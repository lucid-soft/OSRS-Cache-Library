package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.HitmarkDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class HitmarkLoader {

    public HitmarkDefinition load(int id, ByteBuffer buffer) {
        HitmarkDefinition def = new HitmarkDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(HitmarkDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                def.setFontType(buffer.readBigSmart());
                break;
            case 2:
                def.setTextColor(buffer.readSignedMedInt());
                break;
            case 3:
                def.setLeftSprite(buffer.readBigSmart());
                break;
            case 4:
                def.setLeftSprite2(buffer.readBigSmart());
                break;
            case 5:
                def.setBackgroundSprite(buffer.readBigSmart());
                break;
            case 6:
                def.setRightSpriteId(buffer.readBigSmart());
                break;
            case 7:
                def.setScrollToOffsetX(buffer.readShort());
                break;
            case 8:
                def.setStringFormat(buffer.readString());
                break;
            case 9:
                def.setDisplayCycles(buffer.readUnsignedShort());
                break;
            case 10:
                def.setScrollToOffsetY(buffer.readShort());
                break;
            case 11:
                def.setFadeStartCycle(0);
                break;
            case 12:
                def.setUseDamage(buffer.readUnsignedByte());
                break;
            case 13:
                def.setTextOffsetY(buffer.readShort());
                break;
            case 14:
                def.setFadeStartCycle(buffer.readUnsignedShort());
                break;
            case 17:
            case 18:
                int varbitId = buffer.readUnsignedShort();

                if (varbitId == 0xFFFF) {
                    varbitId = -1;
                }
                def.setVarbitID(varbitId);

                int varp = buffer.readUnsignedShort();
                if (varp == 0xFFFF) {
                    varp = -1;
                }
                def.setVarpID(varp);

                int id = -1;
                if (opcode == 18) {
                    id = buffer.readUnsignedShort();
                    if (id == 0xFFFF)
                    {
                        id = -1;
                    }
                }

                int length = buffer.readUnsignedByte();
                int[] multihitsplats = new int[length + 2];

                for (int i = 0; i <= length; i++) {
                    multihitsplats[i] = buffer.readUnsignedShort();
                    if (multihitsplats[i] == 0xFFFF) {
                        multihitsplats[i] = -1;
                    }
                }

                multihitsplats[length + 1] = id;

                def.setMultihitsplats(multihitsplats);
                break;
        }
    }
}

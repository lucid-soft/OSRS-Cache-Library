package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.OverlayDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class OverlayLoader {

    public OverlayDefinition load(int id, ByteBuffer buffer) {
        OverlayDefinition def = new OverlayDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(OverlayDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                def.setColour(buffer.readMedium());
                return;
            case 2:
                def.setTexture(buffer.readUnsignedByte());
                return;
            case 5:
                def.setHideUnderlay(false);
                return;
            case 7:
                def.setSecondaryColour(buffer.readMedium());
                return;
        }
    }
}

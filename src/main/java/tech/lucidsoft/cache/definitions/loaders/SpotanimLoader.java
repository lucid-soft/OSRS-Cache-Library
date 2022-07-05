package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.SpotanimDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class SpotanimLoader {

    public SpotanimDefinition load(int id, ByteBuffer buffer) {
        SpotanimDefinition def = new SpotanimDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if(opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(SpotanimDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                def.setModelId(buffer.readUnsignedShort());
                return;
            case 2:
                def.setAnimationId(buffer.readUnsignedShort());
                return;
            case 4:
                def.setResizeX(buffer.readUnsignedShort());
                return;
            case 5:
                def.setResizeY(buffer.readUnsignedShort());
                return;
            case 6:
                def.setRotation(buffer.readUnsignedShort());
                return;
            case 7:
                def.setAmbient(buffer.readUnsignedByte());
                return;
            case 8:
                def.setContrast(buffer.readUnsignedByte());
                return;
            case 40:
                int recolorSize = buffer.readUnsignedByte();
                def.setOriginalColours(new short[recolorSize]);
                def.setReplacementColours(new short[recolorSize]);

                for (int i = 0; i < recolorSize; i++) {
                    def.getOriginalColours()[i] = (short) buffer.readUnsignedShort();
                    def.getReplacementColours()[i] = (short) buffer.readUnsignedShort();
                }
                return;
            case 41:
                int retextureSize = buffer.readUnsignedByte();
                def.setOriginalTextures(new short[retextureSize]);
                def.setReplacementTextures(new short[retextureSize]);

                for (int i = 0; i < retextureSize; i++) {
                    def.getOriginalTextures()[i] = (short) buffer.readUnsignedShort();
                    def.getReplacementTextures()[i] = (short) buffer.readUnsignedShort();
                }
                return;
        }
    }
}

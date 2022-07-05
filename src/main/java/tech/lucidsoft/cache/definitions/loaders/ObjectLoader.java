package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.ObjectDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class ObjectLoader {

    public ObjectDefinition load(int id, ByteBuffer buffer) {
        ObjectDefinition def = new ObjectDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(ObjectDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                int size = buffer.readUnsignedByte();
                if (size > 0) {
                    def.setTypes(new int[size]);
                    def.setModels(new int[size]);
                    for (int index = 0; index < size; index++) {
                        def.getModels()[index] = buffer.readUnsignedShort();
                        def.getTypes()[index] = buffer.readUnsignedByte();
                    }
                }
                return;
            case 2:
                def.setName(buffer.readString());
                return;
            case 5:
                size = buffer.readUnsignedByte();
                if (size > 0) {
                    def.setTypes(null);
                    def.setModels(new int[size]);
                    for (int index = 0; index < size; index++) {
                        def.getModels()[index] = buffer.readUnsignedShort();
                    }
                    return;
                }
                return;
            case 14:
                def.setSizeX(buffer.readUnsignedByte());
                return;
            case 15:
                def.setSizeY(buffer.readUnsignedByte());
                return;
            case 17:
                def.setClipType(0);
                def.setProjectileClip(false);
                return;
            case 18:
                def.setProjectileClip(false);
                return;
            case 19:
                def.setOptionsInvisible(buffer.readUnsignedByte());
                return;
            case 21:
                def.setContouredGround(0);
                return;
            case 22:
                def.setNonFlatShading(true);
                return;
            case 23:
                def.setModelClipped(true);
                return;
            case 24:
                def.setAnimationId(buffer.readUnsignedShort());
                if (def.getAnimationId() == 65535) {
                    def.setAnimationId(-1);
                }
                return;
            case 27:
                def.setClipType(1);
                return;
            case 28:
                def.setDecorDisplacement(buffer.readUnsignedByte());
                return;
            case 29:
                def.setAmbient(buffer.readByte());
                return;
            case 39:
                def.setContrast(buffer.readByte() * 25);
                return;
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
                def.getOptions()[opcode - 30] = buffer.readString();
                if (def.getOptions()[opcode - 30].equalsIgnoreCase("Hidden")) {
                    def.getOptions()[opcode - 30] = null;
                }
                return;
            case 40:
                size = buffer.readUnsignedByte();
                def.setModelColours(new int[size]);
                def.setReplacementColours(new int[size]);
                for (int count = 0; count < size; count++) {
                    def.getModelColours()[count] = (short) buffer.readUnsignedShort();
                    def.getReplacementColours()[count] = (short) buffer.readUnsignedShort();
                }
                return;
            case 41:
                int size2 = buffer.readUnsignedByte();
                def.setModelTexture(new short[size2]);
                def.setReplacementTextures(new short[size2]);
                for (int count = 0; count < size2; count++) {
                    def.getModelTexture()[count] = (short) buffer.readUnsignedShort();
                    def.getReplacementTextures()[count] = (short) buffer.readUnsignedShort();
                }
                return;
            case 60:
                int minimapFunction = buffer.readUnsignedShort();
                // deprecated
                return;
            case 61:
                def.setCategory(buffer.readUnsignedShort());
                return;
            case 62:
                def.setRotated(true);
                return;
            case 64:
                def.setClipped(false);
                return;
            case 65:
                def.setModelSizeX(buffer.readUnsignedShort());// useless.
                return;
            case 66:
                def.setModelSizeHeight(buffer.readUnsignedShort());// useless
                return;
            case 67:
                def.setModelSizeY(buffer.readUnsignedShort());// useless
                return;
            case 68:
                def.setMapSceneId(buffer.readUnsignedShort());
                return;
            case 69:
                def.setAccessBlockFlag(buffer.readUnsignedByte());
                return;
            case 70:
                def.setOffsetX(buffer.readShort());
                return;
            case 71:
                def.setOffsetHeight(buffer.readShort());
                return;
            case 72:
                def.setOffsetY(buffer.readShort());
                return;
            case 73:
                def.setObstructsGround(true);
                return;
            case 74:
                def.setHollow(true);
                return;
            case 75:
                def.setSupportItems(buffer.readUnsignedByte());
                return;
            case 77:
            case 92:
                def.setVarbit(buffer.readUnsignedShort());
                if (def.getVarbit() == 65535) {
                    def.setVarbit(-1);
                }
                def.setVarp(buffer.readUnsignedShort());
                if (def.getVarp() == 65535) {
                    def.setVarp(-1);
                }
                def.setFinalTransformation(-1);
                if (opcode == 92) {
                    def.setFinalTransformation(buffer.readUnsignedShort());
                    if (def.getFinalTransformation() == 65535) {
                        def.setFinalTransformation(-1);
                    }
                }
                int size3 = buffer.readUnsignedByte();
                def.setTransformedIds(new int[size3 + 2]);
                for (int index = 0; index <= size3; index++) {
                    def.getTransformedIds()[index] = buffer.readUnsignedShort();
                    if (def.getTransformedIds()[index] == 65535) {
                        def.getTransformedIds()[index] = -1;
                    }
                }
                def.getTransformedIds()[size3 + 1] = def.getFinalTransformation();
                return;
            case 78:
                def.setAmbientSoundId(buffer.readUnsignedShort());
                def.setAnInt455(buffer.readUnsignedByte());
                return;
            case 79:
                def.setAnInt456(buffer.readUnsignedShort());
                def.setAnInt457(buffer.readUnsignedShort());
                def.setAnInt455(buffer.readUnsignedByte());
                size = buffer.readUnsignedByte();
                def.setAnIntArray100(new int[size]);
                for (int count = 0; count < size; count++) {
                    def.getAnIntArray100()[count] = buffer.readUnsignedShort();
                }
                return;
            case 81:
                def.setContouredGround(buffer.readUnsignedByte() * 256);
                return;
            case 82:
                def.setMapIconId(buffer.readUnsignedShort());
                return;
            case 89:
                def.setRandomizedAnimStart(true);
                return;
            case 249:
                def.setParameters(buffer.readParameters());
                return;
            default:
                System.err.println("UNSUPPORTED OBJECT OPCODE: " + opcode);
                return;
        }
    }
}

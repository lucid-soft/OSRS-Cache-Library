package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.NpcDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class NpcLoader {

    public boolean using210Decoder = false;

    public NpcLoader(boolean using210Decoder) {
        this.using210Decoder = using210Decoder;
    }


    public NpcDefinition load(int id, ByteBuffer buffer) {

        NpcDefinition def = new NpcDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(NpcDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1: {
                int size = buffer.readUnsignedByte();
                def.setModels(new int[size]);
                for (int i = 0; i < size; i++) {
                    def.getModels()[i] = buffer.readUnsignedShort();
                }
                return;
            }
            case 2:
                def.setName(buffer.readString());
                return;
            case 12:
                def.setSize(buffer.readUnsignedByte());
                return;
            case 13:
                def.setStandAnimation(buffer.readUnsignedShort());
                return;
            case 14:
                def.setWalkAnimation(buffer.readUnsignedShort());
                return;
            case 15:
                def.setField3568(buffer.readUnsignedShort());
                return;
            case 16:
                def.setField3580(buffer.readUnsignedShort());
                return;
            case 17:
                def.setWalkAnimation(buffer.readUnsignedShort());
                def.setRotate180Animation(buffer.readUnsignedShort());
                def.setRotate90Animation(buffer.readUnsignedShort());
                def.setRotate270Animation(buffer.readUnsignedShort());
                return;
            case 18:
                def.setCategory(buffer.readUnsignedShort());
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
            case 40: {
                final int size = buffer.readUnsignedByte();
                def.setOriginalColours(new short[size]);
                def.setReplacementColours(new short[size]);
                for (int i = 0; i < size; i++) {
                    def.getOriginalColours()[i] = (short) (buffer.readUnsignedShort());
                    def.getReplacementColours()[i] = (short) (buffer.readUnsignedShort());
                }
                return;
            }
            case 41: {
                int size = buffer.readUnsignedByte();
                def.setOriginalTextures(new short[size]);
                def.setReplacementTextures(new short[size]);
                for (int i = 0; i < size; i++) {
                    def.getOriginalTextures()[i] = (short) (buffer.readUnsignedShort());
                    def.getReplacementTextures()[i] = (short) (buffer.readUnsignedShort());
                }
                return;
            }
            case 60: {
                int size = buffer.readUnsignedByte();
                def.setChatModels(new int[size]);
                for (int i = 0; i < size; i++) {
                    def.getChatModels()[i] = buffer.readUnsignedShort();
                }
                return;
            }
            case 93:
                def.setMinimapVisible(false);
                return;
            case 95:
                def.setCombatLevel(buffer.readUnsignedShort());
                return;
            case 97:
                def.setResizeX(buffer.readUnsignedShort());
                return;
            case 98:
                def.setResizeY(buffer.readUnsignedShort());
                return;
            case 99:
                def.setVisible(true);
                return;
            case 100:
                def.setAmbience(buffer.readByte());
                return;
            case 101:
                def.setContrast(buffer.readByte());
                return;
            case 102:
                if (!using210Decoder)
                {
                    def.setHeadIconArchiveIds(new int[]{-1});
                    def.setHeadIconSpriteIndex(new short[]{(short) buffer.readUnsignedShort()});
                }
                else
                {
                    int bitfield = buffer.readUnsignedByte();
                    int len = 0;
                    for (int var5 = bitfield; var5 != 0; var5 >>= 1) {
                        ++len;
                    }

                    def.setHeadIconArchiveIds(new int[len]);
                    def.setHeadIconSpriteIndex(new short[len]);

                    for (int i = 0; i < len; i++)
                    {
                        if ((bitfield & 1 << i) == 0)
                        {
                            def.getHeadIconArchiveIds()[i] = -1;
                            def.getHeadIconSpriteIndex()[i] = -1;
                        }
                        else
                        {
                            def.getHeadIconArchiveIds()[i] = buffer.readBigSmart();
                            def.getHeadIconSpriteIndex()[i] = (short) buffer.readUnsignedShortSmartMinusOne();
                        }
                    }
                }
                return;
            case 103:
                def.setDirection(buffer.readUnsignedShort());
                return;
            case 106:
            case 118: {
                def.setVarbit(buffer.readUnsignedShort());
                if (def.getVarbit() == 65535) {
                    def.setVarbit(-1);
                }
                def.setVarp(buffer.readUnsignedShort());
                if (def.getVarp() == 65535) {
                    def.setVarp(-1);
                }
                def.setFinalTransmogrification(-1);
                if (opcode == 118) {
                    def.setFinalTransmogrification(buffer.readUnsignedShort());
                    if (def.getFinalTransmogrification() == 65535) {
                        def.setFinalTransmogrification(-1);
                    }
                }
                final int size = buffer.readUnsignedByte();
                def.setTransmogrifiedIds(new int[size + 2]);
                for (int int_3 = 0; int_3 <= size; int_3++) {
                    def.getTransmogrifiedIds()[int_3] = buffer.readUnsignedShort();
                    if (def.getTransmogrifiedIds()[int_3] == 65535) {
                        def.getTransmogrifiedIds()[int_3] = -1;
                    }
                }
                def.getTransmogrifiedIds()[size + 1] = def.getFinalTransmogrification();
                return;
            }
            case 107:
                def.setClippedMovement(false);
                return;
            case 109:
                def.setClickable(false);
                return;
            case 111:
                def.setFamiliar(true);
                return;
            case 114:
                def.setRunAnimation(buffer.readUnsignedShort());
                return;
            case 115:
                def.setRunAnimation(buffer.readUnsignedShort());
                def.setRunRotate180Animation(buffer.readUnsignedShort());
                def.setRunRotateLeftAnimation(buffer.readUnsignedShort());
                def.setRunRotateRightAnimation(buffer.readUnsignedShort());
                return;
            case 116:
                def.setCrawlAnimation(buffer.readUnsignedShort());
                return;
            case 117:
                def.setCrawlAnimation(buffer.readUnsignedShort());
                def.setCrawlRotate180Animation(buffer.readUnsignedShort());
                def.setCrawlRotateLeftAnimation(buffer.readUnsignedShort());
                def.setCrawlRotateRightAnimation(buffer.readUnsignedShort());
                return;
            case 249:
                def.setParameters(buffer.readParameters());
                return;
            default:
                System.out.println("UNUSED OPCODE: " + opcode);
                return;
        }
    }
}

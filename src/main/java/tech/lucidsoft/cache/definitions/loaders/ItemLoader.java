package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.ItemDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class ItemLoader {

    public ItemDefinition load(int id, ByteBuffer buffer) {
        ItemDefinition def = new ItemDefinition(id);
        while (true) {
            int opcode = buffer.readUnsignedByte();
            if (opcode == 0) {
                break;
            }
            decode(def, buffer, opcode);
        }
        return def;
    }

    public void decode(ItemDefinition def, final ByteBuffer buffer, final int opcode) {
        switch (opcode) {
            case 1:
                def.setInventoryModelId(buffer.readUnsignedShort());
                return;
            case 2:
                def.setName(buffer.readString());
                return;
            case 4:
                def.setZoom(buffer.readUnsignedShort());
                return;
            case 5:
                def.setModelPitch(buffer.readUnsignedShort()); //aka xan2d
                return;
            case 6:
                def.setModelRoll(buffer.readUnsignedShort()); //aka yan2d
                return;
            case 7:
                def.setOffsetX(buffer.readUnsignedShort()); //aka xOffset2d
                if (def.getOffsetX() > 32767) {
                    def.setOffsetX(def.getOffsetX() - 65536);
                }
                return;
            case 8:
                def.setOffsetY(buffer.readUnsignedShort()); //aka yOffset2d
                if (def.getOffsetY() > 32767) {
                    def.setOffsetY(def.getOffsetY() - 65536);
                }
                return;
            case 9:
                String unknown = buffer.readString();
                // System.out.println("Item " + def.getId() + " unknown string:" + unknown);
                return;
            case 11:
                def.setIsStackable(1);
                return;
            case 12:
                def.setPrice(buffer.readInt());
                return;
            case 16:
                def.setMembers(true);
                return;
            case 23:
                def.setPrimaryMaleModel(buffer.readUnsignedShort());
                def.setMaleOffset(buffer.readUnsignedByte());
                return;
            case 24:
                def.setSecondaryMaleModel(buffer.readUnsignedShort());
                return;
            case 25:
                def.setPrimaryFemaleModel(buffer.readUnsignedShort());
                def.setFemaleOffset(buffer.readUnsignedByte());
                return;
            case 26:
                def.setSecondaryFemaleModel(buffer.readUnsignedShort());
                return;
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
                def.getGroundOptions()[opcode - 30] = buffer.readString();
                if (def.getGroundOptions()[opcode - 30].equalsIgnoreCase("Hidden")) {
                    def.getGroundOptions()[opcode - 30] = null;
                }
                return;
            case 35: //op0
            case 36: //op1
            case 37:
            case 38:
            case 39:
                def.getInventoryOptions()[opcode - 35] = buffer.readString();
                return;
            case 40: {
                int amount = buffer.readUnsignedByte();
                def.setOriginalColours(new short[amount]);
                def.setReplacementColours(new short[amount]);
                for (int index = 0; index < amount; index++) {
                    def.getOriginalColours()[index] = (short) (buffer.readUnsignedShort());
                    def.getReplacementColours()[index] = (short) (buffer.readUnsignedShort());
                }
                return;
            }
            case 41: {
                int amount = buffer.readUnsignedByte();
                def.setOriginalTextures(new short[amount]);
                def.setReplacementTextures(new short[amount]);
                for (int index = 0; index < amount; index++) {
                    def.getOriginalTextures()[index] = (short) (buffer.readUnsignedShort());
                    def.getReplacementTextures()[index] = (short) (buffer.readUnsignedShort());
                }
                return;
            }
            case 42:
                def.setShiftClickIndex(buffer.readByte());
                return;
            case 65:
                def.setGrandExchange(true);
                return;
            case 78:
                def.setTertiaryMaleModel(buffer.readUnsignedShort());
                return;
            case 79:
                def.setTertiaryFemaleModel(buffer.readUnsignedShort());
                return;
            case 90:
                def.setPrimaryMaleHeadModelId(buffer.readUnsignedShort());
                return;
            case 91:
                def.setPrimaryFemaleHeadModelId(buffer.readUnsignedShort());
                return;
            case 92:
                def.setSecondaryMaleHeadModelId(buffer.readUnsignedShort());
                return;
            case 93:
                def.setSecondaryFemaleHeadModelId(buffer.readUnsignedShort());
                return;
            case 94:
                def.setCategory(buffer.readUnsignedShort());
                return;
            case 95:
                def.setModelYaw(buffer.readUnsignedShort());
                return;
            case 97:
                def.setNotedId(buffer.readUnsignedShort());
                return;
            case 98:
                def.setNotedTemplate(buffer.readUnsignedShort());
                return;
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
                if (def.getStackIds() == null) {
                    def.setStackIds(new int[10]);
                    def.setStackAmounts(new int[10]);
                }
                def.getStackIds()[opcode - 100] = buffer.readUnsignedShort();
                def.getStackAmounts()[opcode - 100] = buffer.readUnsignedShort();
                return;
            case 110:
                def.setResizeX(buffer.readUnsignedShort());
                return;
            case 111:
                def.setResizeY(buffer.readUnsignedShort());
                return;
            case 112:
                def.setResizeZ(buffer.readUnsignedShort());
                return;
            case 113:
                def.setAmbient(buffer.readByte());
                return;
            case 114:
                def.setContrast(buffer.readByte());
                return;
            case 115:
                def.setTeamId(buffer.readUnsignedByte());
                return;
            case 139:
                def.setBindId(buffer.readUnsignedShort());
                return;
            case 140:
                def.setBindTemplateId(buffer.readUnsignedShort());
                return;
            case 148:
                def.setPlaceholderId(buffer.readUnsignedShort());
                return;
            case 149:
                def.setPlaceholderTemplate(buffer.readUnsignedShort());
                return;
            case 249:
                def.setParameters(buffer.readParameters());
                return;
        }
    }
}

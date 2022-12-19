package tech.lucidsoft.sampletools;

import tech.lucidsoft.cache.definitions.managers.ModelManager;
import tech.lucidsoft.cache.definitions.managers.model.Model;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.io.ByteBuffer;
import tech.lucidsoft.cache.util.DefUtil;
import java.util.ArrayList;
import java.util.List;

public class ModelConverter {

    private static String cachePath = "./data/cache210/";
    private static String dumpPath = "./data/dumps/210/models/";

    private Cache cache;
    private ModelManager modelManager;

    private int[] modelsToConvert = {-1};

    public static void main(String args[]) {
        new ModelConverter();
    }

    public ModelConverter() {
        cache = Cache.openCache(cachePath);

        modelManager = new ModelManager(cache);
        modelManager.setVerbose(true);
        modelManager.load();

        for (int id : modelsToConvert) {
            try {
                convertModel(id);
            } catch (RuntimeException e) {
                //e.printStackTrace();
            }
        }

        /**
        * This is to check models to see if they utilize the new animaya data, to see if they can be converted or not.
        checkAllForAnimayaUse();

        System.out.println("Models that use animaya: " + modelsThatUseAnimaya.size());
        for (int i : modelsThatUseAnimaya) {
            System.out.println("ID: " + i);
        }
        */
    }

    List<Integer> modelsThatUseAnimaya = new ArrayList<>();

    public void checkAllForAnimayaUse() {
        for (Model model : modelManager.getModels()) {
            if (model == null) {
                continue;
            }
            byte[] modelData = model.getData();
            if (modelData[modelData.length - 1] == -3 && modelData[modelData.length - 2] == -1) {
                try {
                    this.convertOSRSFooter(modelData);
                } catch (Exception e) {
                    modelsThatUseAnimaya.add(model.getId());
                }

            } else if (modelData[modelData.length - 1] == -2 && modelData[modelData.length - 2] == -1) {
                try {
                    this.convertRS2Footer(modelData);
                } catch (Exception e) {
                    modelsThatUseAnimaya.add(model.getId());
                }
            }
        }
    }

    private void convertModel(int id) {
        Model model = modelManager.getModels()[id];
        byte[] modelData = model.getData();
        byte[] newFooter;
        byte[] mainBuffer;
        if (modelData[modelData.length - 1] == -3 && modelData[modelData.length - 2] == -1) {
            newFooter = this.convertOSRSFooter(modelData);
            mainBuffer = new byte[modelData.length - 3];

            System.arraycopy(modelData, 0, mainBuffer, 0, modelData.length - 26);
            System.arraycopy(newFooter, 0, mainBuffer, mainBuffer.length - newFooter.length, newFooter.length);

        } else if (modelData[modelData.length - 1] == -2 && modelData[modelData.length - 2] == -1) {
            newFooter = this.convertRS2Footer(modelData);
            mainBuffer = new byte[modelData.length - 5];

            System.arraycopy(modelData, 0, mainBuffer, 0, modelData.length - 23);
            System.arraycopy(newFooter, 0, mainBuffer, mainBuffer.length - newFooter.length, newFooter.length);

        } else if (modelData[modelData.length - 1] == -1 && modelData[modelData.length - 2] == -1) {
            System.out.println("Model is already in old OSRS format");
            return;
        } else {
            System.out.println("Model is already in old RS2 format");
            return;
        }

        System.out.println("Dumping model # " + id);
        DefUtil.dumpData(mainBuffer, dumpPath, id + "");
        System.out.println("Dumping complete!");
    }

    private byte[] convertOSRSFooter(byte[] buffer) {
        ByteBuffer originalBuffer = new ByteBuffer(buffer);
        originalBuffer.setPosition(originalBuffer.limit() - 26);
        ByteBuffer newBuffer = new ByteBuffer(23);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        int b = originalBuffer.readUnsignedByte();
        if (b == 1) {
            throw new RuntimeException("Model uses animaya vals. This converter does not yet support removal of animaya data so model will not export properly.");
        }
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        originalBuffer.readUnsignedShort();
        newBuffer.writeByte(0xFF);
        newBuffer.writeByte(0xFF);
        return newBuffer.getBuffer();
    }

    private byte[] convertRS2Footer(byte[] buffer) {
        ByteBuffer originalBuffer = new ByteBuffer(buffer);
        originalBuffer.setPosition(originalBuffer.limit() - 23);
        ByteBuffer newBuffer = new ByteBuffer(18);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        newBuffer.writeByte(originalBuffer.readUnsignedByte() & 0xFF);
        int b = originalBuffer.readUnsignedByte();
        if (b == 1) {
            throw new RuntimeException("Model uses animaya vals. This converter does not yet support removal of animaya data.");
        }
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        newBuffer.writeShort(originalBuffer.readUnsignedShort() & 0xFFFF);
        originalBuffer.readUnsignedShort();
        originalBuffer.readByte();
        originalBuffer.readByte();
        return newBuffer.getBuffer();
    }

}
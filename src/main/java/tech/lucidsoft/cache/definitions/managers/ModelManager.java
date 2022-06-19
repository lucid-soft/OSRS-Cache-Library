package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.definitions.managers.model.Model;
import tech.lucidsoft.cache.filesystem.Archive;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.Group;
import tech.lucidsoft.cache.definitions.NpcDefinition;
import tech.lucidsoft.cache.definitions.ObjectDefinition;
import tech.lucidsoft.cache.util.DefUtil;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ModelManager {

    private final Cache cache;
    private Model[] models;
    private static boolean verbose = false;

    public ModelManager(final Cache cache) {
        this.cache = cache;
    }

    public void load() {
        Archive modelArchive = cache.getArchive(ArchiveType.MODELS);
        models = new Model[modelArchive.getHighestGroupId()];
        for(int i = 0; i < modelArchive.getHighestGroupId(); i++) {
            Group model = modelArchive.findGroupByID(i);
            if(model != null) {
                models[i] = new Model(i, model.findFileByID(0).getData().getBuffer());
            }
        }
    }

    public void dumpObjectModels(ObjectDefinition def, String directory) {
        if(def == null) {
            return;
        }
        if(def.getModels() != null && def.getModels().length > 0) {
            for(int i = 0; i < def.getModels().length; i++) {
                    dumpModel(def.getModels()[i], new File(directory + def.getId() + "_" + DefUtil.cleanseName(def.getName()) + System.getProperty("file.separator")), def.getModels()[i] + "");
            }
        }
    }

    public void dumpNpcModels(NpcDefinition def, String directory) {
        if(def == null) {
            return;
        }
        if(def.getModels() != null && def.getModels().length > 0) {
            for(int i = 0; i < def.getModels().length; i++) {
                dumpModel(def.getModels()[i], new File(directory + def.getId() + "_" + DefUtil.cleanseName(def.getName()) + System.getProperty("file.separator")), def.getModels()[i] + "");
            }
        }
        if(def.getChatModels() != null && def.getChatModels().length > 0) {
            for(int i = 0; i < def.getChatModels().length; i++) {
                dumpModel(def.getChatModels()[i], new File(directory + def.getId() + "_" + DefUtil.cleanseName(def.getName()) + System.getProperty("file.separator")), def.getChatModels()[i] + "-chatmodel");
            }
        }
    }

    public void dumpModel(int id, File directory, String filename) {
        java.io.File datFile = null;
        try {
            if(!directory.exists()){
                directory.mkdirs();
            }
            datFile = new java.io.File(directory, filename + ".dat");
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(datFile));
            dos.write(models[id].getData());
        } catch (Exception e) {
            System.err.println("Error while writing file: " + (datFile != null ? datFile.getPath() : "null file"));
            e.printStackTrace();
            return;
        }
    }

    public static boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        ModelManager.verbose = verbose;
    }

    public Model[] getModels() {
        return this.models;
    }
}

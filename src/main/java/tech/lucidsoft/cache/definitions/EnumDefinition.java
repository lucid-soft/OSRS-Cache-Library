package tech.lucidsoft.cache.definitions;

import java.util.HashMap;

public class EnumDefinition {

    private int id;
    private char keyType;
    private char valueType;
    private String defaultString;
    private int defaultInt;
    private int size;
    private HashMap<Integer, Object> values;

    public EnumDefinition() {
        setDefaults();
    }

    public EnumDefinition(int id) {
        setDefaults();
        this.setId(id);
    }

    public void setDefaults() {
        setDefaultString("null");
        values = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getKeyType() {
        return keyType;
    }

    public void setKeyType(char keyType) {
        this.keyType = keyType;
    }

    public char getValueType() {
        return valueType;
    }

    public void setValueType(char valueType) {
        this.valueType = valueType;
    }

    public String getDefaultString() {
        return defaultString;
    }

    public void setDefaultString(String defaultString) {
        this.defaultString = defaultString;
    }

    public int getDefaultInt() {
        return defaultInt;
    }

    public void setDefaultInt(int defaultInt) {
        this.defaultInt = defaultInt;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public HashMap<Integer, Object> getValues() {
        return values;
    }

    public void setValues(HashMap<Integer, Object> values) {
        this.values = values;
    }
}

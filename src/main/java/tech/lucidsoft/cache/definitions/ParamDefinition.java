package tech.lucidsoft.cache.definitions;

public class ParamDefinition {

    private int id;
    private char stackType;
    private int defaultInt;
    private String defaultString;
    private boolean autoDisable = true;

    public ParamDefinition() {
    }

    public ParamDefinition(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getStackType() {
        return stackType;
    }

    public void setStackType(char stackType) {
        this.stackType = stackType;
    }

    public int getDefaultInt() {
        return defaultInt;
    }

    public void setDefaultInt(int defaultInt) {
        this.defaultInt = defaultInt;
    }

    public String getDefaultString() {
        return defaultString;
    }

    public void setDefaultString(String defaultString) {
        this.defaultString = defaultString;
    }

    public boolean isAutoDisable() {
        return autoDisable;
    }

    public void setAutoDisable(boolean autoDisable) {
        this.autoDisable = autoDisable;
    }
}

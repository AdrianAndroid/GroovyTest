package com.translate.bean;

public class DefaultValueKey {
    final int index;
    final String key;
    final String defValue;

    public DefaultValueKey(int index, String key, String defValue) {
        this.index = index;
        this.key = key;
        this.defValue = defValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefValue() {
        return defValue;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "DefaultValueKey{" +
                "index=" + index +
                ", key='" + key + '\'' +
                ", defValue='" + defValue + '\'' +
                '}';
    }
}

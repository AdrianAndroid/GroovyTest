package com.translate.bean;

public class ValueIndexDir {
    final String valuedir;
    final int index;

    public ValueIndexDir(int index, String valuedir) {
        this.valuedir = valuedir;
        this.index = index;
    }

    public String getValuedir() {
        return valuedir;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "ValueIndexDir{" +
                "valuedir='" + valuedir + '\'' +
                ", index=" + index +
                '}';
    }
}

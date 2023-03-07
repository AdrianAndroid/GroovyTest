package com.translate;

public interface Config {
    //values-de	values-es	values-fr	values-in	values-it	values-ja	values-ko	values-ms	values-pt	values-th
    public static final String VALUES = "values";//默认语言
    public static final String VALUES_EN = "values-en";//默认语言
    public static final String VALUES_AR = "values-ar"; // 阿拉伯语
    public static final String VALUES_DE = "values-de"; // 德语
    public static final String VALUES_ES = "values-es"; // 西班牙语
    public static final String VALUES_FR = "values-fr"; // 法语
    public static final String VALUES_IN = "values-in"; // 印尼语
    public static final String VALUES_IT = "values-it"; // 意大利语
    public static final String VALUES_JA = "values-ja"; // 日语
    public static final String VALUES_KO = "values-ko"; // 韩语
    public static final String VALUES_MS = "values-ms"; // 马来语
    public static final String VALUES_PT = "values-pt"; // 葡萄牙语
    public static final String VALUES_TH = "values-th"; // 泰语


    public String getTransApi(String from);
}

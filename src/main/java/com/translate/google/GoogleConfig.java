package com.translate.google;

import com.translate.Config;

import java.util.HashMap;
import java.util.Map;

public class GoogleConfig implements Config {
    private static final Map<String, String> TRANS_MAP = new HashMap<>();

    // https://cloud.google.com/translate/docs/languages?hl=zh-cn
    static {
        TRANS_MAP.put(VALUES_EN, "en");
        TRANS_MAP.put(VALUES_AR, "ar");
        TRANS_MAP.put(VALUES_DE, "de");
        TRANS_MAP.put(VALUES_ES, "es");
        TRANS_MAP.put(VALUES_FR, "fr");
        TRANS_MAP.put(VALUES_IN, "id");
        TRANS_MAP.put(VALUES_IT, "it");
        TRANS_MAP.put(VALUES_JA, "ja");
        TRANS_MAP.put(VALUES_KO, "ko");
        TRANS_MAP.put(VALUES_MS, "ms");
        TRANS_MAP.put(VALUES_PT, "pt");
        TRANS_MAP.put(VALUES_TH, "th");
    }

    @Override
    public String getTransApi(String from) {
        return TRANS_MAP.getOrDefault(from, "");
    }
}

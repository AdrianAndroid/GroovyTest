package com.translate.google;

import com.translate.TransApi;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class TestGoogle {
    public static void main(String[] args) {
        TransApi api = new GoogleTransApi();
        String res = api.getTransResultString("hero", "en", "ms");
        System.out.println(URLDecoder.decode(res, Charset.defaultCharset()));
    }
}

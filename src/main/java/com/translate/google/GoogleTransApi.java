package com.translate.google;

import com.google.common.base.Charsets;
import com.translate.HttpGet;
import com.translate.MD5;
import com.translate.TransApi;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class GoogleTransApi implements TransApi {

    //https://translate.google.com/translate_a/single\?client\=gtx\&sl\=en\&tl\=ms\&dt\=t\&q\=%48%65%6c%6c%6f%20%57%6f%72%6c%64
    private static final String TRANS_API_HOST = "https://translate.google.com/translate_a/single";

    @Override
    public String getTransResultString(String query, String from, String to) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // [[["Hai dunia","Hello World",null,null,1]],null,"en",null,null,null,null,[]]%
        Map<String, String> params = buildParams(query, from, to);
        String json = HttpGet.get(TRANS_API_HOST, params);
        System.out.println("getTransResultString -> " + json);
        return dealResponse(URLDecoder.decode(json, Charsets.UTF_8));
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    //[[["Hai dunia","Hello World",null,null,1]],null,"en",null,null,null,null,[]]%
    public String dealResponse(String json) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            JSONArray ja2 = (JSONArray) jsonArray.get(0);
            JSONArray ja3 = (JSONArray) ja2.get(0);
            String resultStr = (String) ja3.get(0);
            return resultStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        // client\=gtx\&sl\=en\&tl\=ms\&dt\=t\&q\=%48%65%6c%6c%6f%20%57%6f%72%6c%64
        Map<String, String> params = new HashMap<String, String>();
        params.put("client", "gtx");
        params.put("sl", from);
        params.put("tl", to);
        params.put("dt", "t");
        params.put("q", URLEncoder.encode(query, Charsets.UTF_8));
        return params;
    }
}

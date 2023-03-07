package com.translate.baidu;

import com.translate.HttpGet;
import com.translate.MD5;
import com.translate.TransApi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class BaiduTransApi implements TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    public BaiduTransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    //葡萄牙语{"from":"en","to":"pt","trans_result":[{"src":"Hello world!","dst":"Ol\u00e1 mundo!"}]}
    @Override
    public String getTransResultString(String query, String from, String to) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String json = getTransResult(query, from, to);
        System.out.println(json);
        JSONParser parser = new JSONParser();
        String result = "";
        try {
            Object obj = parser.parse(json);
            if (!(obj instanceof JSONObject)) {
                return result;
            }
            JSONObject jo = (JSONObject) obj;
            obj = jo.get("trans_result");
            if (!(obj instanceof JSONArray)) {
                return result;
            }
            JSONArray ja = (JSONArray) obj;
            if (ja.size() == 0) {
                return result;
            }
            obj = ((JSONArray) obj).get(0);
            if (!(obj instanceof JSONObject)) {
                return result;
            }
            obj = ((JSONObject) obj).get("dst");
            if (obj instanceof String) {
                result = (String) obj;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}

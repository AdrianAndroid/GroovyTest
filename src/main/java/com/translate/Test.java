package com.translate;

import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Test {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20230306001588876";
    private static final String SECURITY_KEY = "U4DEeQwgEE7rDIACDBi7";

    //http://api.fanyi.baidu.com/product/113
    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String query = "Hello world!";
        new Thread(new Runnable() {
            private void waitMe() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void run() {
                String from = "en";
                // 英语
                System.out.println("英语" + api.getTransResultString(query, from, "en"));
                waitMe();
                // 阿拉伯语
                System.out.println("阿拉伯语" + api.getTransResultString(query, from, "ara"));
                waitMe();
                // 德语
                System.out.println("德语" + api.getTransResultString(query, from, "de"));
                waitMe();
                // 西班牙语
                System.out.println("西班牙语" + api.getTransResultString(query, from, "spa"));
                waitMe();
                // 法语
                System.out.println("法语" + api.getTransResultString(query, from, "fra"));
                waitMe();
                // 印尼语
                System.out.println("印尼语" + api.getTransResultString(query, from, "id"));
                waitMe();
                // 意大利语
                System.out.println("意大利语" + api.getTransResultString(query, from, "it"));
                waitMe();
                // 日语
                System.out.println("日语" + api.getTransResultString(query, from, "jp"));
                waitMe();
                // 韩语
                System.out.println("韩语" + api.getTransResultString(query, from, "kor"));
                waitMe();
                // 马来语
                System.out.println("马来语" + api.getTransResultString(query, from, "en"));
                waitMe();
                // 葡萄牙语
                System.out.println("葡萄牙语" + api.getTransResultString(query, from, "pt"));
                waitMe();
                // 泰语
                System.out.println("泰语" + api.getTransResultString(query, from, "th"));
            }
        }).start();

    }


    void test() {
    }
}

import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

class CheckContainsNM {
    static void main(String[] args) {
        println "hello world!"
        iteratorFiles(new File("/Users/apus/apus/foxbooster/app"))
    }

    static def iteratorFiles(File file) {
        if (file.isDirectory()) {
            file.listFiles().each {f ->
                iteratorFiles(f)
            }
        } else if(file.isFile()) {
            String fileName = file.getName()
            if (fileName.endsWith("data.json")) {
                //println file.getAbsolutePath()
                dealWithJson(file)
            }
        }
    }


    private static void dealWithJson(File jsonFile) {
        BufferedReader br = new BufferedReader(new FileReader(jsonFile))
        String str = null
        while((str = br.readLine()) != null) {
            if(isContainsNM(str)) {
                println("${jsonFile.getAbsolutePath()}")
                println("$str")
                replaceKey("nm", jsonFile.getAbsolutePath())
                //System.exit(0)
            }
        }
        br.close()
    }

    public static boolean isDataJsonFile(String fileName) {
        return fileName.equals("data.json")
    }

    public static boolean isContainsNM(String str) {
        return str != null && str.contains("nm")
    }


    private static void replaceKey(String key, String absoluteFileName) {
        // 读取原始 JSON 数据
        JSONParser parser = new JSONParser()
        try {
            JSONObject obj = (JSONObject)parser.parse(new FileReader(absoluteFileName))
            replaceIterator(obj, key)
            // 将替换后的 JSON 数据保存到新的文件中
            FileWriter fileWriter = new FileWriter(absoluteFileName)

            String resultString = obj.toJSONString()
            fileWriter.write(resultString)
            fileWriter.flush()
            fileWriter.close()
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    private static void replaceIterator(Object obj, String key) {
        if (obj instanceof JSONObject) {
            obj.keySet().each { k1->
                if (key.equals(k1)) {
                    obj.put(key, "")
                } else if(obj.get(k1) instanceof JSONArray) {
                    replaceIterator(obj.get(k1), key)
                }
            }
        } else if (obj instanceof JSONArray) {
            JSONArray data = (JSONArray) obj
            // 遍历 JSON 数据，替换目标 key 的 value
            for (Object item : data) {
                JSONObject jsonObject = (JSONObject) item
                replaceIterator(jsonObject, key)
            }
        }
    }


//    /**
//     * 字符串是否包含中文
//     *
//     * @param str 待校验字符串
//     * @return true 包含中文字符  false 不包含中文字符
//     */
//    public static boolean isContainChinese(String str) {
////        if (StringUtils.isEmpty(str)) {
////            throw new Exception("sms context is empty!");
////        }
//        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
//        Matcher m = p.matcher(str);
//        if (m.find()) {
//            return true;
//        }
//        return false;
//    }
}

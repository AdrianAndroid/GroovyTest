import java.util.regex.Matcher
import java.util.regex.Pattern

class CheckContainsChinees {
    static void main(String[] args) {
        println "hello world!"
        iteratorFiles(new File("/Users/apus/apus/TargetCleaner"))
    }

    static def iteratorFiles(File file) {
        if (file.isDirectory()) {
            file.listFiles().each {f ->
                iteratorFiles(f)
            }
        } else if(file.isFile()) {
            String fileName = file.getName()
            if (fileName.endsWith("data.json")) {
                println ">>>>>>>>>>>loading>>>>>>>>" + file.getAbsolutePath()
                dealWithJson(file)
            }
        }
    }


    private static void dealWithJson(File jsonFile) {
        BufferedReader br = new BufferedReader(new FileReader(jsonFile))
        String str = null
        while((str = br.readLine()) != null) {
            if(isContainChinese(str)) {
                println("${jsonFile.getAbsolutePath()}")
                println("$str")
                //System.exit(0)
            }
        }
        br.close()
    }

    /**
     * 字符串是否包含中文
     *
     * @param str 待校验字符串
     * @return true 包含中文字符  false 不包含中文字符
     */
    public static boolean isContainChinese(String str) {
//        if (StringUtils.isEmpty(str)) {
//            throw new Exception("sms context is empty!")
//        }
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]")
        Matcher m = p.matcher(str)
        if (m.find()) {
            return true
        }
        return false
    }
}

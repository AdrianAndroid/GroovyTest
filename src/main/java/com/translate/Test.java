package com.translate;

import com.translate.baidu.BaiduTransApi;
import com.translate.baidu.BaiduConfig;
import com.translate.bean.DefaultValueKey;
import com.translate.bean.ValueIndexDir;
import com.translate.google.GoogleConfig;
import com.translate.google.GoogleTransApi;
import org.apache.http.util.TextUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20230306001588876";
    private static final String SECURITY_KEY = "U4DEeQwgEE7rDIACDBi7";

    private static final TransApi api = new GoogleTransApi();//= new BaiduTransApi(APP_ID, SECURITY_KEY);
    private static final Config config = new GoogleConfig(); // BaiduConfig

    //http://api.fanyi.baidu.com/product/113
    public static void main(String[] args) {
        try {
            translate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void translate() throws IOException {
        String outputPath = "/Users/apus/AndroidStudioProjects/LanguageX/excel/translate.xlsx";
        FileInputStream inputStream = new FileInputStream(outputPath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        int sheetCount = workbook.getNumberOfSheets();
        System.out.println("sheetCount -> " + sheetCount);
        Sheet sheet = workbook.getSheetAt(0);

        // 读取key defaultvalue 还有索引
        //List<DefaultValueKey> list = new ArrayList<>();
        Map<Integer, DefaultValueKey> map = new HashMap<>();
        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            Cell cellKey = row.getCell(0);
            Cell cellVal = row.getCell(1);
            String key = cellKey.getStringCellValue();
            String value = cellVal.getStringCellValue();
            if (!TextUtils.isBlank(key) && !TextUtils.isBlank(value) && rowNum != 0) {
                map.put(rowNum, new DefaultValueKey(rowNum, key, value));
            }
        }

        // 记录多语言文件夹
        List<ValueIndexDir> listValueIndexDirs = new ArrayList<>();
        for (Cell cell : sheet.getRow(0)) {
            int columnIndex = cell.getColumnIndex();
            if (columnIndex > 1) { // 第一列 key 第二列 默认值
                listValueIndexDirs.add(new ValueIndexDir(columnIndex, cell.getStringCellValue()));
            }
        }

        // 开始翻译
        for (ValueIndexDir indexDir : listValueIndexDirs) {
            final int cellIndex = indexDir.getIndex();
            final String valueDir = indexDir.getValuedir();
            for (Integer mapIndex : map.keySet()) {
                DefaultValueKey defaultValueKey = map.get(mapIndex);
                final int rowIndex = defaultValueKey.getIndex();
                // 翻译
                Row row = sheet.getRow(rowIndex);
                Cell cell = row.getCell(cellIndex);
                if (cell == null) {
                    cell = row.createCell(cellIndex);
                }
                translateCell(defaultValueKey, indexDir, cell);
                //translateCell(valueDir, cell);
            }
        }

//        String outputPath2 = "/Users/apus/AndroidStudioProjects/LanguageX/excel/translate.xlsx";
        FileOutputStream fos = new FileOutputStream(outputPath);
        workbook.write(fos);
        fos.close();

        workbook.close();
        inputStream.close();
    }

    private static void translateCell(DefaultValueKey defaultValueKey, ValueIndexDir indexDir, Cell cell) {
        if (cell == null) {
            System.out.println("cell is null. 不能翻译");
            return;
        }
        String defValue = defaultValueKey.getDefValue(); // 要翻译的字符串
        String valueDir = indexDir.getValuedir(); // 要翻译的语言
        String trans = config.getTransApi(valueDir);
        if (TextUtils.isBlank(defValue) || TextUtils.isBlank(valueDir) || TextUtils.isBlank(trans)) {
            System.out.println("没有要翻译的语言defValue:" + defValue + " ,valueDir:" + valueDir + " ,trans:" + trans);
            return; // 不翻译
        }
        String value = cell.getStringCellValue();
        if (!TextUtils.isBlank(value)) {
            System.out.println("当前cell value: 不为空,就不翻译了." + value);
            return; //当前cell不为空,就不翻译了
        }
        String query = defValue;
        String from = "en";
        String to = trans;
        String transString = api.getTransResultString(query, from, to);
        System.out.println("当前信息 from:" + from + ",to:" + trans + ",orgStr:" + query + ",toStr:" + transString);
        if (!TextUtils.isBlank(transString)) {
            System.out.println("修改成功!!");
            cell.setCellValue(transString);
        } else {
            System.out.println("未修改!!");
        }
    }
}

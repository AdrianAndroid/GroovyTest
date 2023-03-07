package com.translate;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.util.List;

public class TestOptions {

    // java MyCmdLineTool -Duser.name=alice --input=input.txt --output=output.txt
    public static void main(String[] args) {
        OptionParser parser = new OptionParser();

        // 定义命令行选项和参数
        parser.accepts("h", "显示帮助信息");
        parser.accepts("v", "显示版本号");
        parser.accepts("D", "定义系统属性").withRequiredArg().ofType(String.class);
        parser.accepts("input", "输入文件").withRequiredArg().ofType(String.class);
        parser.accepts("output", "输出文件").withRequiredArg().ofType(String.class);

        // 解析命令行参数
        OptionSet options = parser.parse(args);

        // 处理选项
        if (options.has("h")) {
            System.out.println("帮助信息...");
            System.exit(0);
        }

        if (options.has("v")) {
            System.out.println("版本号...");
            System.exit(0);
        }

        if (options.has("D")) {
            List<String> props = (List<String>) options.valuesOf("D");
            for (String prop : props) {
                String[] parts = prop.split("=", 2);
                System.setProperty(parts[0], parts[1]);
            }
        }

        // 处理参数
        if (options.has("input")) {
            String inputFilename = (String) options.valueOf("input");
            System.out.println("输入文件: " + inputFilename);
        }

        if (options.has("output")) {
            String outputFilename = (String) options.valueOf("output");
            System.out.println("输出文件: " + outputFilename);
        }
    }

}

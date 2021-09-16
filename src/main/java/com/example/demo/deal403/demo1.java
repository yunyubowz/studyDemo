package com.example.demo.deal403;

import java.io.*;

/**
 * 使用方法：
 * java -jar 的形式执行jar包
 * 默认扫描jar包所在路径，不会递归扫描
 * 可以追加参数 path=xxx 指定扫描xxx目录
 */
public class demo1 {

        public static void main(String[] args) throws IOException {
            String path = "D:\\学习\\图灵三期JAVA\\资料\\资料\\4.分布式框架专题-更多it视频 IT视频学习网-www.itspxx.com\\01.Zookeeper特性与节点详解-鲁班-更多it视频 IT视频学习网-www.itspxx.com";
//            for (int i = 0; i < args.length; i++) {
//                String arg = args[i];
//                if (arg.startsWith("path=")){
//                    path = arg.substring(5);
//                    break;
//                }
//            }
//            if (path==null){
//                path = System.getProperty("user.dir");
//            }
            System.out.println("--开始扫描目录："+path);
            File file = new File(path);
            File[] list = file.listFiles();
            if (list == null) {
                System.out.println("--空目录");
                return;
            }
            //创建输出目录
            String out = path + "/out/";
            new File(out).mkdir();
            for (int i = 0; i < list.length; i++) {
                String name = list[i].getName();
                if (!(list[i].isFile() && (name.endsWith(".md") || name.endsWith(".MD")))) {
                    continue;
                }
                System.out.println("--"+list[i].getName());
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(list[i])));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(out+name))))
                ) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String src = src(line);
                        writer.write(src +"\n");
                    }
                    writer.flush();
                }
            }
            System.out.println("--结束");
        }

        /**
         * 提取img的src
         */
        private static String src(String s){
            String[] split = s.split("!");
            for (int i = 0; i < split.length; i++) {
                if (split[i].length()>4&&split[i].contains("[")&&split[i].contains("]")&&split[i].contains("(")&&split[i].contains(")")){
                    int start = split[i].lastIndexOf("(");
                    int end = split[i].lastIndexOf(")");
                    String s1 = split[i].substring(start + 1, end);
                    s = s.replace("!" + split[i], "<img src=\"" + s1 + "\" referrerPolicy=\"no-referrer\"/>");
                }
            }
            return s;
        }
}

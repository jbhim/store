package com.easy.store.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jbhim
 * @date 2018/11/17/017.
 */
public class FileUtil {

    public static String readTxt(String uri) throws Exception {
        Path path = Paths.get(uri);
        File file = path.toFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, resolveCode(file.getPath()));
        BufferedReader bf = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = bf.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }

        bf.close();
        inputStreamReader.close();
        fileInputStream.close();
        return stringBuilder.toString();
    }

    /**
     * 判断文本文件编码类型
     *
     * @param path 文件路径
     * @return 文件编码类型
     * @throws Exception e
     */
    public static String resolveCode(String path) throws Exception {
        // [-76, -85, -71]  ANSI
        // [-2, -1, 79] unicode big endian
        // [-1, -2, 32]  unicode
        // [-17, -69, -65] UTF-8
        InputStream inputStream = new FileInputStream(path);
        byte[] head = new byte[3];
        inputStream.read(head);
        String code = "gb2312";
        if (head[0] == -1 && head[1] == -2) {
            code = "UTF-16";
        } else if (head[0] == -2 && head[1] == -1) {
            code = "Unicode";
        } else if (head[0] == -17 && head[1] == -69 && head[2] == -65) {
            code = "UTF-8";
        }
        inputStream.close();
        System.out.println(code);
        return code;
    }

    public static void main(String[] args) throws Exception {
        String uri = "E:\\JavaAndMore\\projectspace\\jbhim\\public\\files\\kill.txt";
        String st = FileUtil.readTxt(uri);
        //System.out.println(st);
        String pattern = "　　\n(.+)\n　　\n";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(st);
        int i = 0;
        List<String> title = new ArrayList<>();
        while (m.find()) {
            //取出所有标题
            //System.out.println(m.group(1));
            title.add(m.group(1));
        }
        //取出每一章的内容
        String[] split = st.split(pattern);
        List<String> content = Arrays.asList(split);
    }
}

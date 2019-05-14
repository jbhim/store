package com.easy.store.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ClassPathResourceReader {

    /**
     * 读取jar包中classpath的文件
     */
    public static String readJarFile(String path) {
        String content;
        try {
            ClassPathResource resource = new ClassPathResource(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            content = reader.lines().collect(Collectors.joining(""));
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return content;
    }

}

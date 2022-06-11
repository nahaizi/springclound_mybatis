package com.lili.provider.util.csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvUtils {
    public static void main(String[] args) {
        String url = "D:\\test\\test.csv";
        String[] str = CsvUtils.readCsv(url);
        for (String s : str) {
            if(s.indexOf("\"") != -1){
                s = s.replace("\"","");
            }
        }
        //将s写入

    }

    // read csv file
    public static String[] readCsv(String url) {
        String[] str = null;
        try {
            str = new String(Files.readAllBytes(Paths.get(url))).split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}

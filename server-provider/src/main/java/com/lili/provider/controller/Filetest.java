package com.lili.provider.controller;

import java.io.*;

/**
 * @param
 * @author lijunyu
 * @Description: //TODO
 * @return
 * @throws
 * @date ===========================================
 * 修改人：lijunyu，    修改时间：      修改版本：
 * 修改备注：
 * ===========================================
 */
public class Filetest {
    public static void main(String[] args) {
        Filetest Filetest = new Filetest();
        try {
            Filetest.readfile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readfile() throws IOException {
        File file = new File("C:\\Users\\lijunyu\\Desktop\\abs-info.2022-01-12.1.log");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString = "";

        //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
        File copyfile = new File("C:\\Users\\lijunyu\\Desktop\\abs-info.2022-01-12.copy.log");
        if(copyfile.exists()){
            copyfile.delete();
        }
        FileWriter writer = new FileWriter(copyfile, true);
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            if(tempString.indexOf("DEBUG updateHandlingTime") > -1){
                   continue;
            }
            // 显示行号
            System.out.println(  tempString);
            writer.write(tempString + "\r");

        }
        reader.close();
        writer.close();
    }


}
